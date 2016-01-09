package com.elasticbeanstalk.weatherforecast_env7410.www.weatherforecast;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Nikhil on 12/8/2015.
 */
public class nxt24 extends AppCompatActivity {
    String result = "";
    String city = "";
    String state = "";
    String unit = "";
    JSONObject reader;
    JSONObject hourly;
    JSONArray d1;
    String timez;
    TableLayout tl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            //NEXT 24!!!!!!!!!!!!!
            result = getIntent().getStringExtra("result");
            city = getIntent().getStringExtra("city");
            state = getIntent().getStringExtra("state");
            unit = getIntent().getStringExtra("unit");
            reader = new JSONObject(result);
            JSONObject current = reader.getJSONObject("currently");
            hourly = reader.getJSONObject("hourly");
            // JSONObject data = daily.getJSONObject("data");
            d1 = hourly.getJSONArray("data");
            timez = reader.getString("timezone");

            super.onCreate(savedInstanceState);

            tl = new TableLayout(this);
            TableRow row1 = new TableRow(this);
            TableRow.LayoutParams lp=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

            row1.setLayoutParams(lp);
            TextView h1 = new TextView(this);
            h1.setPadding(5, 10, 40, 0);
            h1.setTextSize(18);
            h1.setText("Time");

            row1.setLayoutParams(lp);
            TextView h2 = new TextView(this);
            h2.setPadding(5, 10, 40, 0);
            h2.setTextSize(18);
            h2.setText("Summary");

            row1.setLayoutParams(lp);
            TextView h3 = new TextView(this);
            h3.setPadding(5, 10, 40, 0);
            h3.setTextSize(18);
            if (unit.equalsIgnoreCase("us"))
                h3.setText("Temp(°F)");
            else
                h3.setText("Temp(°C)");
            //Third Column
            row1.addView(h1);
            row1.addView(h2);
            row1.addView(h3);
            row1.setBackgroundColor(getResources().getColor(R.color.heading));
            row1.setPadding(0, 0, 0, 10);
            row1.setMinimumWidth(50);
            row1.setBottom(10);
            tl.addView(row1, 0);

            for (int i = 1; i < 25; i++) {
                TableRow row = new TableRow(this);

                JSONObject d = d1.getJSONObject(i);


                //image
                ImageView img = new ImageView(this);
                TableRow.LayoutParams img_lp = new TableRow.LayoutParams(100, 100);
                img.setLayoutParams(img_lp);
                img.setAdjustViewBounds(true);
                img.setPadding(20, 0, 20, 0);
                String icon = d.getString("icon");
                String x = get_icon(icon, 1);
                int z = getFlagResource(nxt24.this, x);
                //Log.d("icon", String.valueOf(z));
                img.setImageResource(z);


                //First Column
                //time
                long hourly_time = d.getLong("time");
                String hour = getDate(hourly_time, timez);
                row.setLayoutParams(lp);
                TextView col1 = new TextView(this);
                col1.setPadding(5, 10, 40, 0);
                col1.setTextSize(15);
                col1.setGravity(Gravity.CENTER);
                col1.setText(hour);

                //Third Column
                int temp = d.getInt("temperature");
                row.setLayoutParams(lp);
                TextView col3 = new TextView(this);
                col3.setPadding(60, 10, 5, 0);
                col3.setTextSize(15);
                col1.setGravity(Gravity.CENTER);
                col3.setText(String.valueOf(temp));


                row.addView(col1);
                row.addView(img);
                row.addView(col3);
                if (i % 2 == 0)
                    row.setBackgroundColor(getResources().getColor(R.color.bgwhite));
                else
                    row.setBackgroundColor(getResources().getColor(R.color.bgtab));
                tl.addView(row, i);
            }
            final TableRow rowgroupPlusSign = new TableRow(this);
            rowgroupPlusSign.setBackgroundColor(Color.parseColor("#8b8383"));

            TextView cell1 = new TextView(this);
            cell1.setWidth(150);
            //cell1.setHeight(150);

            cell1.setVisibility(View.VISIBLE);
            cell1.setGravity(Gravity.CENTER_HORIZONTAL);
            cell1.setTextSize(16);

            rowgroupPlusSign.addView(cell1);


            //col 2

            Button cell2 = new Button(this);
            cell2.setWidth(40);
            //cell2.setHeight(100);
            cell2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

            cell2.setText("+");
            cell2.setBackgroundColor((Color.parseColor("#2058f0")));
            cell2.setVisibility(View.VISIBLE);
            cell2.setGravity(Gravity.CENTER_HORIZONTAL);
            cell2.setTextSize(16);

            rowgroupPlusSign.addView(cell2);


            //col 3
            TextView cell3 = new TextView(this);

            cell3.setWidth(150);
            //cell3.setHeight(150);

            cell3.setVisibility(View.VISIBLE);
            cell3.setGravity(Gravity.CENTER_HORIZONTAL);
            cell3.setTextSize(16);

            rowgroupPlusSign.addView(cell3);

            tl.addView(rowgroupPlusSign);
            setContentView(tl);

            cell2.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    TableRow.LayoutParams lp=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    for (int i = 25; i < 49; i++) {
                        TableRow row = new TableRow(v.getContext());
                        try {

                            JSONObject d = d1.getJSONObject(i);


                            //image
                            ImageView img = new ImageView(v.getContext());
                            TableRow.LayoutParams img_lp = new TableRow.LayoutParams(100, 100);
                            img.setLayoutParams(img_lp);
                            img.setAdjustViewBounds(true);
                            img.setPadding(20, 0, 20, 0);
                            String icon = d.getString("icon");
                            String x = get_icon(icon, 1);
                            int z = getFlagResource(nxt24.this, x);
                            //Log.d("icon", String.valueOf(z));
                            img.setImageResource(z);


                            //First Column
                            //time
                            long hourly_time = d.getLong("time");
                            String hour = getDate(hourly_time, timez);
                            row.setLayoutParams(lp);
                            TextView col1 = new TextView(v.getContext());
                            col1.setPadding(5, 10, 40, 0);
                            col1.setTextSize(15);
                            col1.setGravity(Gravity.CENTER);
                            col1.setText(hour);

                            //Third Column
                            int temp = d.getInt("temperature");
                            row.setLayoutParams(lp);
                            TextView col3 = new TextView(v.getContext());
                            col3.setPadding(60, 10, 5, 0);
                            col3.setTextSize(15);
                            col1.setGravity(Gravity.CENTER);
                            col3.setText(String.valueOf(temp));


                            row.addView(col1);
                            row.addView(img);
                            row.addView(col3);
                            if (i % 2 == 0)
                                row.setBackgroundColor(getResources().getColor(R.color.bgwhite));
                            else
                                row.setBackgroundColor(getResources().getColor(R.color.bgtab));
                            tl.addView(row, i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    tl.removeView(rowgroupPlusSign);

                }



            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDate(long time, String tz) {

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        Date netDate = (new Date(time * 1000));
        sdf.setTimeZone(TimeZone.getTimeZone(tz));
        return sdf.format(netDate);

    }

    public String get_icon(String p_icon, int d) {
        String p_image = "";
        String img_tip = "";
        if (p_icon.equalsIgnoreCase("clear-day")) {
            p_image = "clear";
            img_tip = "Clear Day";
        } else if (p_icon.equalsIgnoreCase("clear-night")) {
            p_image = "clear_night";
            img_tip = "Clear Night";
        } else if (p_icon.equalsIgnoreCase("rain")) {
            p_image = "rain";
            img_tip = "Rain";
        } else if (p_icon.equalsIgnoreCase("snow")) {
            p_image = "snow";
            img_tip = "Snow";
        } else if (p_icon.equalsIgnoreCase("sleet")) {
            p_image = "sleet";
            img_tip = "Sleet";
        } else if (p_icon.equalsIgnoreCase("wind")) {
            p_image = "wind";
            img_tip = "Wind";
        } else if (p_icon.equalsIgnoreCase("fog")) {
            p_image = "fog";
            img_tip = "Fog";
        } else if (p_icon.equalsIgnoreCase("cloudy")) {
            p_image = "cloudy";
            img_tip = "Cloudy";
        } else if (p_icon.equalsIgnoreCase("partly-cloudy-day")) {
            p_image = "cloud_day";
            img_tip = "Cloudy Day";
        } else if (p_icon.equalsIgnoreCase("partly-cloudy-night")) {
            p_image = "cloud_night";
            img_tip = "Cloudy night";
        }
        if (d == 1)
            return p_image;
        else
            return img_tip;
    }

    public int getFlagResource(Context context, String name) {
        int resId = context.getResources().getIdentifier(name, "drawable", "com.elasticbeanstalk.weatherforecast_env7410.www.weatherforecast");
        return resId;
    }
}

