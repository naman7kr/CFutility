package com.hackathon.cfutility.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class PresentFragment extends Fragment {
    PresentFragmentAdapter adapter;
    ArrayList<Contestobj> list = new ArrayList<>();
    RecyclerView recyclerView;
    URL url=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.presentfragment,container,false);
        recyclerView = view.findViewById(R.id.present_rview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter =new PresentFragmentAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        list.clear();
        try {
            url=new URL("http://codeforces.com/api/contest.list?gym=false");
            CFresponse cFresponse=new CFresponse(this);
            cFresponse.execute(url);
            String fal=list.size()+"";
            //Log.e("ss",fal);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return view;
    }

    public void handleData(String s) {
        Log.e("LOL",s);
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
                    if(temp.phase.compareTo("BEFORE")!=0&&temp.phase.compareTo("FINISHED")!=0) {
                        list.add(temp);
                    }
                    adapter.notifyDataSetChanged();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    }

