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

import java.net.MalformedURLException;
import java.net.URL;

public class HandleInfo extends Fragment{
    URL url;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handle_info,container,false);

        try {
            //url to be changed
            url = new URL("http://codeforces.com/api/user.info?handles=utkarshh12");
            CFresponse cFresponse = new CFresponse(this);
            cFresponse.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return view;
    }
    public void handleData(String data){
        Log.e("LOL",data);
    }
}
