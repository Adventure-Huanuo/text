package com.example.lee.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 蔡如男 on 2017/4/17.
 */

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {
    private List<Huanuo> mFirstPaper;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView paperImage;
        TextView paperName;
        View documentView;
        public ViewHolder(View view){
            super(view);
            documentView = view;
            paperImage = (ImageView) view.findViewById(R.id.paper_image);
            paperName = (TextView) view.findViewById(R.id.paper_name);
        }
    }
    public DocumentAdapter(List<Huanuo>FirstPaper){
        mFirstPaper =FirstPaper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.documentView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Huanuo document = mFirstPaper.get(position);
                String str = document.getName().replace(" ","");
                sendRequestWithHttpURLConnection(str,v.getContext());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Huanuo frist = mFirstPaper.get(position);
        holder.paperImage.setImageResource(frist.getImageId());
        holder.paperName.setText(frist.getName());
    }
    @Override
    public int getItemCount() {
        return mFirstPaper.size();
    }


    private void sendRequestWithHttpURLConnection(final String str, final Context context) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
                    Map dataMap = new HashMap();
                    dataMap.put("doctitle",str);
                    dataMap.put("id",pref.getString("id",""));
                    dataMap.put("token",pref.getString("token", ""));
                    HttpRequestor httpRequestor = new HttpRequestor();
                    String res = httpRequestor.doPost("http://172.16.201.17:8080/HuanuoServer/DocInfor", dataMap);
                    JSONObject json = new JSONObject(res);
                    if (json.getString("code").equals("1")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW); //为Intent设置Action属性
                        intent.setData(Uri.parse(json.getString("docurl"))); //为Intent设置DATA属性
                        context.startActivity(intent);
                    }
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(context,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }
}

