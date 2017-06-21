package com.example.lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lee on 2017/5/14.
 */

public class FragmentPage2 extends Fragment implements View.OnClickListener {
    private MainActivity mActivity;
    private View mView;
    private SearchView mSearchView;
    private ListView mListView;
    private ArrayAdapter adapter;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private List<SearchPerson> searchList = new ArrayList<>();
    private Handler handler;
    final static int MESSAGE_OK = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
          }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_page2, container,
                false);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button bt2 = (Button) mView.findViewById(R.id.COM);
        Button bt3 = (Button) mView.findViewById(R.id.RSO);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);

        pref = PreferenceManager.getDefaultSharedPreferences(mActivity);
        mSearchView = (SearchView) mView.findViewById(R.id.searchView);
        int id = mSearchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) mSearchView.findViewById(id);
        textView.setTextColor(Color.WHITE);//字体颜色
        //textView.setTextSize(20);//字体、提示字体大小
        textView.setHintTextColor(Color.WHITE);//提示字体颜色**
        mListView = (ListView) mView.findViewById(R.id.listView);
        mListView.bringToFront();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(mActivity,PhoneListdetial.class);
                intent.putExtra("str",query);
                startActivity(intent);
                //清除焦点
                mSearchView.clearFocus();
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    mListView.setVisibility(mListView.GONE);
                } else {
                    sendRequestWithHttpURLConnection(newText);
                    handler = new Handler(){
                        public void handleMessage(Message msg) {
                            switch (msg.what) {
                                case MESSAGE_OK:
                                    mListView.setAdapter(null);
                                    String res = (String)msg.obj;
                                    try {
                                        JSONObject js = new JSONObject(res);
                                        String rowcount = js.getString("rowcount");
                                        int count = 0;
                                        count = Integer.parseInt(rowcount);
                                        searchList.clear();
                                        initSearch(count,js);
                                        adapter = new SearchAdapter(mActivity, R.layout.search_item,searchList);
                                        mListView.setVisibility(mListView.VISIBLE);
                                        mListView.setAdapter(adapter);
                                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent,View view, int position, long id) {
                                                SearchPerson searchPerson = searchList.get(position);
                                                Intent intent = new Intent(mActivity,PhoneListdetial.class);
                                                intent.putExtra("str", searchPerson.getName());
                                                startActivity(intent);
                                            }
                                        });
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    };
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.COM:
                Intent intent1 = new Intent(mActivity, ApartmentItem.class);
                startActivity(intent1);
                break;*/
            case R.id.RSO:
                Intent intent2 = new Intent(mActivity, ApartmentItem1.class);
                intent2.putExtra("str","RSO");
                startActivity(intent2);
                break;
        }
    }

    private void sendRequestWithHttpURLConnection(final String str) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map dataMap = new HashMap();
                    dataMap.put("str",str);
                    dataMap.put("id",pref.getString("id",""));
                    dataMap.put("token",pref.getString("token", ""));
                    HttpRequestor httpRequestor = new HttpRequestor();
                    String res = httpRequestor.doPost("http://172.16.201.17:8080/HuanuoServer/SearchResp", dataMap);
                    Message message = new Message();
                    message.obj=res;
                    message.what=MESSAGE_OK;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(mActivity,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }

    private void initSearch(int count,JSONObject json) {
        for (int i = 1; i <= count; i++){
            String name=null;
            try {
                name = json.getString(""+i);
            }catch (Exception e){
                e.printStackTrace();
            }
            SearchPerson search = new SearchPerson(name);
            searchList.add(search);
        }
    }

}
