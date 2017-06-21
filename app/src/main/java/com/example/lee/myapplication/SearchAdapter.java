package com.example.lee.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Escapist on 2017/6/21.
 */

public class SearchAdapter extends ArrayAdapter<SearchPerson> {
    private int resourceId;
    public SearchAdapter(Context context, int textViewResourceId, List<SearchPerson> object){
        super(context,textViewResourceId,object);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        SearchPerson search= getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.searchList = (TextView) view.findViewById(R.id.search_list);
            view.setTag(viewHolder);//将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取ViewHolder
        }
        //View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        //TextView searchList = (TextView) view.findViewById(R.id.search_list);
        viewHolder.searchList.setText(search.getName());
        return view;
    }
    class ViewHolder {
        TextView searchList;
    }
}

