package com.hackathon.cfutility.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackathon.cfutility.Adapters.ContestTabAdapter;
import com.hackathon.cfutility.R;

public class Contests extends Fragment {
    private ContestTabAdapter adapter;
    private ViewPager mPager;
    public TabLayout mTab;
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contests,container,false);
        mPager=view.findViewById(R.id.pagertab);
        mTab=view.findViewById(R.id.contest_tabs);
        setTabs();
        return view;
    }

    private void setTabs() {
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
}
