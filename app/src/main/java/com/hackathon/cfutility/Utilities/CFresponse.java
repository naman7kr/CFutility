package com.hackathon.cfutility.Utilities;

import android.os.AsyncTask;
import android.view.View;

import com.hackathon.cfutility.Fragments.Contests;
import com.hackathon.cfutility.Fragments.Discussions;
import com.hackathon.cfutility.Fragments.HandleInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CFresponse extends AsyncTask<URL,Void,String> {
    URL url;
    HandleInfo handleInfo;
    Contests contests;
    Discussions discussions;
    public CFresponse(HandleInfo handleInfo) {
        this.handleInfo = handleInfo;
    }


    public CFresponse(Discussions discussions) {
        this.discussions = discussions;
    }

    @Override
    protected String doInBackground(URL... urls) {
        try {
            url = new URL(urls[0].toString());
            URLConnection con=url.openConnection();
            String out="";
            String input = null;

            BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
            while((input=br.readLine())!=null){
                out+=input;
            }
            return out;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(handleInfo!=null){
            handleInfo.handleData(s);
            return;
        }
        if(discussions!=null){
            discussions.handleData(s);
        }
    }
}
