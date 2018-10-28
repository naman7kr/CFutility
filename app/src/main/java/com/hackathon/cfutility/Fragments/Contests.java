package com.hackathon.cfutility.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackathon.cfutility.Adapters.ContestTabAdapter;
import com.hackathon.cfutility.Models.Contestobj;
import com.hackathon.cfutility.R;
import com.hackathon.cfutility.Utilities.CFresponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Contests extends Fragment {
    private ContestTabAdapter adapter;
    private ViewPager mPager;
    public TabLayout mTab;

   public ArrayList<Contestobj> pastList=new ArrayList<>();
   public ArrayList<Contestobj> presentList=new ArrayList<>();
   public ArrayList<Contestobj> futureList=new ArrayList<>();

    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contests,container,false);
        mPager=view.findViewById(R.id.pagertab);
        mTab=view.findViewById(R.id.contest_tabs);

        setTabs();
        return view;
    }

    private void setTabs() {
        Log.i("hh",pastList.size()+"");
        Log.i("hh",presentList.size()+"");
        Log.i("hh",futureList.size()+"");
        adapter=new ContestTabAdapter(this,getChildFragmentManager());

        addTabs();
        mPager.setAdapter(adapter);
        mTab.setupWithViewPager(mPager);
    }
    private void addTabs() {
        adapter.addFragment(new FutureFragment(), "Upcoming");
        adapter.addFragment(new PastFragment(), "Past");
        adapter.addFragment(new PresentFragment(), "Present");
    }

    public void handleData(String s){

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
                    if(temp.phase.compareTo("BEFORE")==0){
                        futureList.add(temp);
                    }
                    else if(temp.phase.compareTo("FINISHED")==0){
                        pastList.add(temp);
                    }
                    else
                        presentList.add(temp);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
