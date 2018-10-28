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

public class FutureFragmentAdapter extends RecyclerView.Adapter<FutureFragmentAdapter.FutureViewHolder> {
    LayoutInflater inflater;
    ArrayList<Contestobj> list;
    public FutureFragmentAdapter(Context context, ArrayList<Contestobj> list){
        this.list = list;
        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public FutureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.future_item,parent,false);
        return new FutureFragmentAdapter.FutureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FutureViewHolder holder, int position) {
        Contestobj obj = list.get(position);
        holder.future_name.setText(obj.getName());
        holder.future_duration.setText(obj.getDuration());
        holder.future_phase.setText(obj.getPhase());
        holder.future_start_time.setText(obj.getStarttime());
        holder.future_type.setText(obj.getType());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    class FutureViewHolder extends RecyclerView.ViewHolder{
        TextView future_name,future_type,future_phase,future_start_time,future_duration;

        public FutureViewHolder(View itemView) {
            super(itemView);
            future_name = itemView.findViewById(R.id.future_name);
            future_type = itemView.findViewById(R.id.future_type);
            future_phase = itemView.findViewById(R.id.future_phase);
            future_start_time = itemView.findViewById(R.id.future_start_time);
            future_duration = itemView.findViewById(R.id.future_duration);

        }
    }
}
