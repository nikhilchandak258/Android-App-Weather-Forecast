package com.elasticbeanstalk.weatherforecast_env7410.www.weatherforecast;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class details extends TabActivity {
    String result="";
    String city="";
    String state="";
    String unit="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //setSupportActionBar(toolbar);
        try {
        result = getIntent().getStringExtra("result");
        city = getIntent().getStringExtra("city");
        state = getIntent().getStringExtra("state");
        unit = getIntent().getStringExtra("unit");
        JSONObject reader = new JSONObject(result);
        JSONObject current = reader.getJSONObject("currently");
//        JSONObject daily = reader.getJSONObject("daily");
//        // JSONObject data = daily.getJSONObject("data");
//        JSONArray d1 = daily.getJSONArray("data");
//        JSONObject d = d1.getJSONObject(0);
        String summary=current.getString("summary");
            TextView sum=(TextView)findViewById(R.id.heading);
            sum.setText(summary + " in " + city + ", " + state);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");

        tab1.setIndicator("Next 24 Hours");
            Intent intent1=new Intent(this,nxt24.class);
            intent1.putExtra("result", result);
            intent1.putExtra("city", city);
            intent1.putExtra("state", state);
            intent1.putExtra("unit", unit);
        tab1.setContent(intent1);

        tab2.setIndicator("Next 7 Days");
            Intent intent2=new Intent(this,nxt7.class);
            intent2.putExtra("result", result);
            intent2.putExtra("city", city);
            intent2.putExtra("state", state);
            intent2.putExtra("unit",unit);
        tab2.setContent(intent2);

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);


       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch(Exception e){e.printStackTrace();}
    }


}
