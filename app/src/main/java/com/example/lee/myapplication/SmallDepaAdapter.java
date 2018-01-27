package com.example.lee.myapplication;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SmallDepaAdapter extends RecyclerView.Adapter<SmallDepaAdapter.ViewHolder> {
    private List<SmallDepartments> mSmallDepaList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View SmallDepaView;
        TextView small_depart;
        public ViewHolder(View view){
            super(view);
            SmallDepaView = view;
            small_depart=(TextView) view.findViewById(R.id.small_depart);
        }
    }
    public SmallDepaAdapter(List<SmallDepartments> apartmentList){
        mSmallDepaList=apartmentList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.small_departments, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.SmallDepaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SmallDepartments smallDepartments = mSmallDepaList.get(position);
                //Toast.makeText(v.getContext(),largerDepartments.getDepart_name(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ApartmentItem2.class);
                intent.putExtra("str", smallDepartments.getDepart_name());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        SmallDepartments smallDepartments=mSmallDepaList.get(position);
        holder.small_depart.setText(smallDepartments.getDepart_name());
        }
    @Override
    public int getItemCount(){
        return mSmallDepaList.size();
        }
}
