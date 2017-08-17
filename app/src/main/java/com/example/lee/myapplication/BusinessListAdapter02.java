package com.example.lee.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class BusinessListAdapter02 extends RecyclerView.Adapter<BusinessListAdapter02.ViewHolder> {
    private List<BusinessList02> mBusinessList02;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView businesstype;
        TextView businessname;
        TextView businesssegment;
        TextView businesstime;
        TextView businessdealer;
        View businessView;

        public ViewHolder(View view) {
            super(view);
            businessView = view;
            businesstype = (TextView) view.findViewById(R.id.type);
            businessname = (TextView) view.findViewById(R.id.name);
            businesssegment = (TextView) view.findViewById(R.id.segment);
            businesstime = (TextView) view.findViewById(R.id.time);
            businessdealer = (TextView) view.findViewById(R.id.dealer);
        }
    }

    public BusinessListAdapter02(List<BusinessList02> businessList02) {
        mBusinessList02 = businessList02;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_item02, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.businessView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                BusinessList02 businessList02 = mBusinessList02.get(position);
                //Toast.makeText(v.getContext(),businessList01.getBinderDocIDOS(),Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(v.getContext(), PhoneListdetial.class);
                //intent.putExtra("str", businessList.getBinderDocIDOS());
                //v.getContext().startActivity(intent);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BusinessList02 businessList02 = mBusinessList02.get(position);
        holder.businesstype.setText(businessList02.getType());
        holder.businessname.setText(businessList02.getName());
        holder.businesssegment.setText(businessList02.getSegment());
        holder.businesstime.setText(businessList02.getTime());
        holder.businessdealer.setText(businessList02.getDealer());
    }

    @Override
    public int getItemCount() {
        return mBusinessList02.size();
    }
}
