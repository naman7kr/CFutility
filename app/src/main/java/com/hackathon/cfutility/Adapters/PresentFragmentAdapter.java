package com.hackathon.cfutility.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hackathon.cfutility.Models.Contestobj;
import com.hackathon.cfutility.R;

import java.util.ArrayList;

public class PresentFragmentAdapter extends RecyclerView.Adapter<com.hackathon.cfutility.Adapters.PresentFragmentAdapter.PresentViewHolder> {
    LayoutInflater inflater;
    ArrayList<Contestobj> list;
    public PresentFragmentAdapter(Context context, ArrayList<Contestobj> list){
        this.list = list;
         inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public com.hackathon.cfutility.Adapters.PresentFragmentAdapter.PresentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.present_item,parent,false);
        return new com.hackathon.cfutility.Adapters.PresentFragmentAdapter.PresentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.hackathon.cfutility.Adapters.PresentFragmentAdapter.PresentViewHolder holder, int position) {
        Contestobj obj = list.get(position);
        holder.present_name.setText(obj.getName());
        holder.present_duration.setText(obj.getDuration());
        holder.present_phase.setText(obj.getPhase());
        holder.present_start_time.setText(obj.getStarttime());
        holder.present_type.setText(obj.getType());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    class PresentViewHolder extends RecyclerView.ViewHolder{
        TextView present_name,present_type,present_phase,present_start_time,present_duration;

        public PresentViewHolder(View itemView) {
            super(itemView);
            present_name = itemView.findViewById(R.id.present_name);
            present_type = itemView.findViewById(R.id.present_type);
            present_phase = itemView.findViewById(R.id.present_phase);
            present_start_time = itemView.findViewById(R.id.present_start_time);
            present_duration = itemView.findViewById(R.id.present_duration);

        }
    }
}
