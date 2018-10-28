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
    TextView handle,handle_rating,handle_max_rating,handle_name,handle_email,handle_city,handle_country,handle_rank,handle_max_rank;
    CircleImageView handle_image;
    LinearLayout editLayout;
    EditText handle_edit_name;
    Button handle_submit_btn;
    ProgressBar pBar;
    RelativeLayout rLayout;
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
        handle_name = view.findViewById(R.id.handle_name);
        handle_email = view.findViewById(R.id.handle_email);
        handle_city = view.findViewById(R.id.handle_city);
        handle_country = view.findViewById(R.id.handle_country);
        handle_rank = view.findViewById(R.id.handle_rank);
        handle_max_rank = view.findViewById(R.id.handle_max_rank);
        handle_image = view.findViewById(R.id.handle_image);

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
                    info.setHandle_name(job2.getString("handle"));
                  //  info.setCity(job2.getString("city"));
                   // info.setCountry(job2.getString("country"));
                    info.setContribution(job2.getInt("contribution"));
                 //   info.setEmail(job2.getString("email"));
                //    info.setFirst_name(job2.getString("firstName"));
                 //   info.setLast_name(job2.getString("lastName"));
                    info.setRank(job2.getString("rank"));
                    info.setMax_rank(job2.getString("maxRank"));
                    info.setRating(job2.getInt("rating"));
                    info.setMax_rating(job2.getInt("maxRating"));
                    info.setLastOnlineTimeSecond(job2.getLong("lastOnlineTimeSeconds"));
                    info.setImg_path("https:"+job2.getString("avatar"));
                    setDataInUI(info);


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setDataInUI(HandleInformation info) {
        pBar.setVisibility(View.GONE);
        handle.setText(info.getHandle_name());
        Log.e("LOOOOL", String.valueOf(info.getRating()));
        handle_rating.setText(String.valueOf(info.getRating()));
        handle_max_rating.setText(String.valueOf(info.getMax_rating()));
        handle_max_rank.setText(info.getMax_rank());
        handle_rank.setText(info.getRank());
      //  handle_country.setText(info.getCountry());
        handle_email.setText(info.getEmail());
      //  handle_city.setText(info.getCity());
   //     handle_email.setText(info.getEmail());
    //    handle_name.setText(info.getFirst_name()+" "+info.getLast_name());
        Log.e("LUL",info.getImg_path());
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"Error in fetching Data",Toast.LENGTH_SHORT).show();
            pBar.setVisibility(View.GONE);
        }
    }
}
