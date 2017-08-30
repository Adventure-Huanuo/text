package com.example.lee.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lee on 2017/5/14.
 */

public class FragmentPage4 extends Fragment implements View.OnClickListener {

    private MainActivity mActivity;
    private View mView;
    ExpandableListView expandableListView;
    private Handler handler;
    final static int MESSAGE_SHOW_IMG = 0;
    final static int MESSAGE_RESULT_ERR = 1;
    private SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        System.out.println("fragment4");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_page4, container,
                false);
        init();
        return mView;
    }

    //初始化
    /*
    * 设置联系人界面的适配器
    * 下拉选项框
    * */
    private void init(){
        expandableListView = (ExpandableListView) mView.findViewById(R.id.textView3);
        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter();
        expandableListView.setAdapter(adapter);
    }

    class ExpandableListViewAdapter implements ExpandableListAdapter {
        LayoutInflater mInflater;
        ExpandableListViewAdapter(){
            mInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        //注意两个数组一样长
        private String[] armTypes = new String[]{
                "待查信息", "待办工作", "在办工作", "已发工作", "HR待办"
        };
        private String[][] arms = new String[][]{
                {"差旅审批完成", "报销审批完成", "机票申请已完结", ""},
                {"您没有新的待办工作", "", "", ""},
                {"您没有新的在办工作", "", "", ""},
                {"差旅申请", "机票申请", "", ""},
                {"2017PDP更新", "", "", ""}
        };

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            System.out.println("registerDataSetObserver");
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            System.out.println("unregisterDataSetObserver");
        }

        //返回总有多少一级Item
        @Override
        public int getGroupCount() {
            System.out.println("getGroupCount");
            return armTypes.length;
        }

        /*
        * @return 每个Item有多少子item
        * */
        @Override
        public int getChildrenCount(int groupPosition) {
            System.out.println("getChildrenCount");
            return arms[groupPosition].length;
        }

        /*
        * @return 返回一个一级item
        * */
        @Override
        public Object getGroup(int groupPosition) {
            System.out.println("getGroup");
            return armTypes[groupPosition];
        }

        /*
        * @return 返回二级item
        * */
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            System.out.println("getChild");
            return arms[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            System.out.println("getGroupId");
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            System.out.println("getChildId");
            return childPosition;
        }

        /*
        * @ return ?
        * */
        @Override
        public boolean hasStableIds() {
            System.out.println("hasStableIds");
            return false;
        }

        /*
        * @ return 返回一级item组
        * */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            System.out.println("getGroupView");
            GroupViewHolder groupViewHolder;
            if(convertView == null){
                groupViewHolder = new GroupViewHolder();
                convertView = mInflater.inflate(R.layout.group,null);
                groupViewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name_group);
                convertView.setTag(groupViewHolder);
            }else{
                groupViewHolder = (GroupViewHolder) convertView.getTag();
            }
            groupViewHolder.tvName.setText(getGroup(groupPosition).toString());
            return convertView;
        }

        /*
        * @return 返回二级item
        * */
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            System.out.println("getChildView");
            ItemViewHolder itemViewHolder;
            if(convertView == null){
                itemViewHolder = new ItemViewHolder();
                convertView = mInflater.inflate(R.layout.child,null);
                itemViewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name_item);
                convertView.setTag(itemViewHolder);
            }else{
                itemViewHolder = (ItemViewHolder) convertView.getTag();
            }
            itemViewHolder.tvName.setText(getChild(groupPosition,childPosition).toString());
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            System.out.println("isChildSelectable");
            return false;
        }

        @Override
        public boolean areAllItemsEnabled() {
            System.out.println("areAllItemsEnabled");
            return false;
        }

        @Override
        public boolean isEmpty() {
            System.out.println("isEmpty");
            return false;
        }

        @Override
        public void onGroupExpanded(int groupPosition) {
            System.out.println("onGroupExpanded");
        }

        @Override
        public void onGroupCollapsed(int groupPosition) {
            System.out.println("onGroupCollapsed");
        }

        @Override
        public long getCombinedChildId(long groupId, long childId) {
            System.out.println("getCombinedChildId");
            return 0;
        }

        @Override
        public long getCombinedGroupId(long groupId) {
            System.out.println("getCombinedGroupId");
            return 0;
        }

    }

        class GroupViewHolder{
            TextView tvName;
        TextView tvCount;
    }

    class ItemViewHolder{
        TextView tvName;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button bt3 = (Button) mView.findViewById(R.id.exit);
        bt3.setOnClickListener(this);
        ImageView bt4 = (ImageView) mView.findViewById(R.id.imageView);
        bt4.setOnClickListener(this);
        pref = PreferenceManager.getDefaultSharedPreferences(mActivity);
        sendRequestWithHttpURLConnection(pref.getString("iconurl",""));
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                // super.handleMessage(msg);
                switch (msg.what){
                    case MESSAGE_SHOW_IMG:
                        ((ImageView)mActivity.findViewById(R.id.imageView)).setImageBitmap((Bitmap)msg.obj);
                        break;
                    case MESSAGE_RESULT_ERR:
                        Toast.makeText(mActivity,"照片获取失败",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
        TextView name = (TextView) mView.findViewById(R.id.textView4);
        name.setText(pref.getString("name",""));
        TextView tel = (TextView) mView.findViewById(R.id.textView11);
        tel.setText(pref.getString("tel",""));
        TextView email = (TextView) mView.findViewById(R.id.textView13);
        email.setText(pref.getString("email",""));
    }

    @Override
    public void onStart() {
        super.onStart();
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
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                Intent intent = new Intent(mActivity,MyInformationEdit.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                break;
            case R.id.exit:
                new android.support.v7.app.AlertDialog.Builder(getActivity()).setTitle("提示")
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setMessage("确定要退出登录吗?")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(mActivity,LoginActivity.class));
                                mActivity.finish();//活动注销
                                //mActivity.overridePendingTransition(R.anim.out_to_left,0);
                            }})
                        .setNegativeButton("取消", null)
                        .create().show();


                break;
        }
    }

    private void sendRequestWithHttpURLConnection (final String path) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpRequestor httpRequestor = new HttpRequestor();
                    httpRequestor.requestNet(path,handler);
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(mActivity,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }
}

