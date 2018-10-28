package com.hackathon.cfutility.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hackathon.cfutility.Model.HandleInformation;
import com.hackathon.cfutility.R;
import com.hackathon.cfutility.Utilities.CFresponse;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class HandleInfo extends Fragment implements View.OnClickListener {
    URL url=null;
    TextView total,AC,WA,TLE,MLE,handle,handle_rating,handle_max_rating,handle_name,handle_email,handle_city,handle_country,handle_rank,handle_max_rank;
    CircleImageView handle_image;
    LinearLayout editLayout;
    EditText handle_edit_name;
    Button handle_submit_btn;
    ProgressBar pBar;
    RelativeLayout rLayout;
    int a=0,w=0,m=0,t=0,tot=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handle_info,container,false);
        init(view);

        handle_submit_btn.setOnClickListener(this);
        return view;
    }
    void init(View view){
        handle = view.findViewById(R.id.handle);
        handle_rating = view.findViewById(R.id.handle_rating);
        handle_max_rating = view.findViewById(R.id.handle_max_rating);
        handle_rank = view.findViewById(R.id.handle_rank);
        handle_max_rank = view.findViewById(R.id.handle_max_rank);
        handle_image = view.findViewById(R.id.handle_image);
        AC=view.findViewById(R.id.ac);
        total=view.findViewById(R.id.total_submission);
        MLE=view.findViewById(R.id.mle);
        WA=view.findViewById(R.id.wa);
        TLE=view.findViewById(R.id.tle);

        editLayout = view.findViewById(R.id.handle_layout_edit);
        rLayout = view.findViewById(R.id.handle_layout);
        handle_edit_name = view.findViewById(R.id.handle_info);
        handle_submit_btn = view.findViewById(R.id.handle_submit);
        pBar = view.findViewById(R.id.handle_progress_bar);
        editLayout.setVisibility(View.VISIBLE);
        rLayout.setVisibility(View.GONE);
        handle_submit_btn.setVisibility(View.VISIBLE);
    }
    public void handleData(String data){
        if(data==null){
            Toast.makeText(getContext(),"Error in fetching Data",Toast.LENGTH_SHORT).show();
            rLayout.setVisibility(View.GONE);
            editLayout.setVisibility(View.VISIBLE);
            handle_edit_name.setText("");
            pBar.setVisibility(View.GONE);
            handle_submit_btn.setVisibility(View.VISIBLE);
            return;
        }
        Log.e("LOL",data);
        try {
            rLayout.setVisibility(View.VISIBLE);
            editLayout.setVisibility(View.GONE);
            pBar.setVisibility(View.GONE);
            handle_submit_btn.setVisibility(View.GONE);
            JSONObject job1 = new JSONObject(data);
            if(job1.getString("status").compareTo("OK")==0){
                JSONArray jar1 = job1.getJSONArray("result");
                for(int i=0;i<jar1.length();i++){
                    JSONObject job2 = jar1.getJSONObject(i);
                    HandleInformation info  = new HandleInformation();
                    if(job2.has("handle"))
                    info.setHandle_name(job2.getString("handle"));
                  //  info.setCity(job2.getString("city"));
                   // info.setCountry(job2.getString("country"));
                    if(job2.has("contribution"))
                    info.setContribution(job2.getInt("contribution"));
                 //   info.setEmail(job2.getString("email"));
                //    info.setFirst_name(job2.getString("firstName"));
                 //   info.setLast_name(job2.getString("lastName"));
                    if(job2.has("rank"))
                    info.setRank(job2.getString("rank"));
                    if(job2.has("maxRank"))
                    info.setMax_rank(job2.getString("maxRank"));
                    if(job2.has("rating"))
                    info.setRating(job2.getInt("rating"));
                    if(job2.has("maxRating"))
                    info.setMax_rating(job2.getInt("maxRating"));
                    if(job2.has("lastOnlineTimeSeconds"))
                    info.setLastOnlineTimeSecond(job2.getLong("lastOnlineTimeSeconds"));
                    if(job2.has("avatar"))
                    info.setImg_path("https:"+job2.getString("avatar"));


                    if(job2.has("verdict"))
                    {
                        if(job2.getString("verdict").compareTo("OK")==0){
                            a++;
                            tot=a+w+m+t;
                            info.setAc(a);
                        }
                        else if(job2.getString("verdict").compareTo("WRONG_ANSWER")==0){
                            w++;
                            tot=a+w+m+t;
                            info.setWa(w);
                        }
                        else if(job2.getString("verdict").compareTo("TIME_LIMIT_EXCEEDED")==0){
                            t++;
                            tot=a+w+m+t;
                            info.setTle(t);
                        }
                        else if(job2.getString("verdict").compareTo("MEMORY_LIMIT_EXCEEDED")==0){
                            m++;
                            tot=a+w+m+t;
                            info.setMle(m);
                        }
                    }
                    setDataInUI(info);


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setDataInUI(HandleInformation info) {
        pBar.setVisibility(View.GONE);
        if(info.getHandle_name()!=null)
        handle.setText(info.getHandle_name());
       // Log.e("LOOOOL", String.valueOf(info.getRating()));
        if(info.getRating()!=0)
        handle_rating.setText(String.valueOf(info.getRating()));
        if(info.getMax_rating()!=0)
        handle_max_rating.setText(String.valueOf(info.getMax_rating()));
        if(info.getMax_rank()!=null)
        handle_max_rank.setText(info.getMax_rank());
        if(info.getRank()!=null)
        handle_rank.setText(info.getRank());
      //  handle_country.setText(info.getCountry());


        if(info.getAc()!=0)
            AC.setText(String.valueOf(info.getAc()));

        if(info.getWa()!=0)
            WA.setText(String.valueOf(info.getWa()));

        if(info.getTle()!=0)
            TLE.setText(String.valueOf(info.getTle()));

        if(info.getMle()!=0)
            MLE.setText(String.valueOf(info.getMle()));

        if(info.getAc()!=0)
            total.setText(String.valueOf(tot));

      //  handle_city.setText(info.getCity());
   //     handle_email.setText(info.getEmail());
    //    handle_name.setText(info.getFirst_name()+" "+info.getLast_name());
//        Log.e("LUL",info.getImg_path());
        if(info.getImg_path()!=null)
        Picasso.with(getContext())
                .load(info.getImg_path())
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(handle_image);
    }

    @Override
    public void onClick(View v) {
        pBar.setVisibility(View.VISIBLE);

        try {
            //url to be changed
            url = new URL("http://codeforces.com/api/user.info?handles="+handle_edit_name.getText());
            CFresponse cFresponse = new CFresponse(this);
            cFresponse.execute(url);
            CFresponse cFresponse1=new CFresponse(this);
            url = new URL("http://codeforces.com/api/user.status?handle="+handle_edit_name.getText()+"&from=1&count=2000");
            cFresponse1.execute(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"Error in fetching Data",Toast.LENGTH_SHORT).show();
            pBar.setVisibility(View.GONE);
        }
    }
}
