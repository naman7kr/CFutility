package com.hackathon.cfutility.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackathon.cfutility.R;
import com.hackathon.cfutility.Utilities.CFresponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class Discussions extends Fragment {
    URL url=null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discussions,container,false);
        try {
            //url to be changed
            url = new URL("https://codeforces.com/api/user.status?handle=utkarshh14&from=1&count=2000");
            CFresponse cFresponse = new CFresponse(this);
            cFresponse.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return view;
    }

    public void handleData(String s) {
        try {
            JSONObject job1=new JSONObject(s);
            if(job1.getString("status").compareTo("OK")==0){
                JSONArray jar1 = job1.getJSONArray("result");
                Log.e("hell",""+jar1.length());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("LOL",s);
    }
}
