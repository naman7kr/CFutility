package com.hackathon.cfutility.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackathon.cfutility.Adapters.FutureFragmentAdapter;
import com.hackathon.cfutility.Adapters.PresentFragmentAdapter;
import com.hackathon.cfutility.Models.Contestobj;
import com.hackathon.cfutility.R;
import com.hackathon.cfutility.Utilities.CFresponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FutureFragment extends Fragment {
    FutureFragmentAdapter adapter;
    ArrayList<Contestobj> list = new ArrayList<>();
    RecyclerView recyclerView;
    URL url=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.futurefragment,container,false);
        recyclerView = view.findViewById(R.id.future_rview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter =new FutureFragmentAdapter(getContext(),list);
        list.clear();
        recyclerView.setAdapter(adapter);
        try {
            url=new URL("http://codeforces.com/api/contest.list?gym=false");
            CFresponse cFresponse=new CFresponse(this);
            cFresponse.execute(url);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return view;
    }

    public void handleData(String s) {

        try {
            JSONObject job1 = new JSONObject(s);
            if(job1.getString("status").compareTo("OK")==0) {
                JSONArray jsonArray= job1.getJSONArray("result");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject job2=jsonArray.getJSONObject(i);
                    Contestobj temp=new Contestobj();
                    temp.name=job2.getString("name");
                    temp.type=job2.getString("type");
                    temp.phase=job2.getString("phase");
                    temp.frozen=job2.getBoolean("frozen");
                    long var=job2.getLong("startTimeSeconds");

                    temp.starttime=new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (var*1000));
                    var=job2.getLong("durationSeconds");
                    float var1=(float)var/3600;
                    temp.duration=String.valueOf(var1);
                    if(temp.phase.compareTo("BEFORE")==0) {
                        list.add(temp);
                    }
                    adapter.notifyDataSetChanged();
                }
                adapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
