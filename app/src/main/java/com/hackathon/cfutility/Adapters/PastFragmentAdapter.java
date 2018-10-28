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

public class PastFragmentAdapter extends RecyclerView.Adapter<com.hackathon.cfutility.Adapters.PastFragmentAdapter.PastViewHolder> {
    LayoutInflater inflater;
    ArrayList<Contestobj> list;
    public PastFragmentAdapter(Context context, ArrayList<Contestobj> list){
        this.list = list;
         inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public com.hackathon.cfutility.Adapters.PastFragmentAdapter.PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.past_item,parent,false);
        return new com.hackathon.cfutility.Adapters.PastFragmentAdapter.PastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.hackathon.cfutility.Adapters.PastFragmentAdapter.PastViewHolder holder, int position) {
        Contestobj obj = list.get(position);
        holder.past_name.setText(obj.getName());
        holder.past_duration.setText(obj.getDuration());
        holder.past_phase.setText(obj.getPhase());
        holder.past_start_time.setText(obj.getStarttime());
        holder.past_type.setText(obj.getType());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    class PastViewHolder extends RecyclerView.ViewHolder{
        TextView past_name,past_type,past_phase,past_start_time,past_duration;

        public PastViewHolder(View itemView) {
            super(itemView);
            past_name = itemView.findViewById(R.id.past_name);
            past_type = itemView.findViewById(R.id.past_type);
            past_phase = itemView.findViewById(R.id.past_phase);
            past_start_time = itemView.findViewById(R.id.past_start_time);
            past_duration = itemView.findViewById(R.id.past_duration);
        }
    }
}
