package com.elasticbeanstalk.weatherforecast_env7410.www.weatherforecast;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;





import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Nikhil on 12/8/2015.
 */
public class nxt7  extends AppCompatActivity
{
    String result = "";
    String city = "";
    String state = "";
    String unit = "";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.test);
            //NEXT 7777!!!!!!!!!!!!!
            result = getIntent().getStringExtra("result");
            city = getIntent().getStringExtra("city");
            state = getIntent().getStringExtra("state");
            unit = getIntent().getStringExtra("unit");
            JSONObject reader = new JSONObject(result);
            JSONObject current = reader.getJSONObject("currently");
            JSONObject daily = reader.getJSONObject("daily");
            // JSONObject data = daily.getJSONObject("data");
            JSONArray d1 = daily.getJSONArray("data");
            String timez = reader.getString("timezone");
            String tx[]=new String[7];
            String ty[]=new String[7];
            int imgx[]=new int[7];
            for(int i=1;i<8;i++)
            {
                JSONObject d = d1.getJSONObject(i);
                //image array
                String icon = d.getString("icon");
                String x = get_icon(icon, 1);
                int z = getFlagResource(nxt7.this, x);
                imgx[i-1]=z;

                //Text 1
                long hourly_time = d.getLong("time");
                String hour = getDate(hourly_time, timez);
                tx[i-1]=hour;
                //Log.d("hour",tx[i-1]);

                //Text 2
                int low = d.getInt("temperatureMin");
                int high = d.getInt("temperatureMax");
                String highlow;
                if(unit.equalsIgnoreCase("us")) {
                    highlow="Min:"+String.valueOf(low)+"\u00b0 F | Max:"+String.valueOf(high)+"\u00b0 F";
                }
                else
                {
                    highlow="Min:"+String.valueOf(low)+"\u00b0 C | Max:"+String.valueOf(high)+"\u00b0 C";
                }
                ty[i-1]=highlow;
            }


            TextView t_1=(TextView)(findViewById(R.id.tr1));
            t_1.setText(tx[0]);
            TextView tx_1=(TextView)(findViewById(R.id.tr11));
            tx_1.setText(ty[0]);
            ImageView r_img1=(ImageView)(findViewById(R.id.ir1));
            r_img1.setImageResource(imgx[0]);

            TextView t_2=(TextView)(findViewById(R.id.tr2));
            t_2.setText(tx[1]);
            TextView tx_2=(TextView)(findViewById(R.id.tr12));
            tx_2.setText(ty[1]);
            ImageView r_img2=(ImageView)(findViewById(R.id.ir2));
            r_img2.setImageResource(imgx[1]);

            TextView t_3=(TextView)(findViewById(R.id.tr3));
            t_3.setText(tx[2]);
            TextView tx_3=(TextView)(findViewById(R.id.tr13));
            tx_3.setText(ty[2]);
            ImageView r_img3=(ImageView)(findViewById(R.id.ir3));
            r_img3.setImageResource(imgx[2]);

            TextView t_4=(TextView)(findViewById(R.id.tr4));
            t_4.setText(tx[3]);
            TextView tx_4=(TextView)(findViewById(R.id.tr14));
            tx_4.setText(ty[3]);
            ImageView r_img4=(ImageView)(findViewById(R.id.ir4));
            r_img4.setImageResource(imgx[3]);

            TextView t_5=(TextView)(findViewById(R.id.tr5));
            t_5.setText(tx[4]);
            TextView tx_5=(TextView)(findViewById(R.id.tr15));
            tx_5.setText(ty[4]);
            ImageView r_img5=(ImageView)(findViewById(R.id.ir5));
            r_img5.setImageResource(imgx[4]);

            TextView t_6=(TextView)(findViewById(R.id.tr6));
            t_6.setText(tx[5]);
            TextView tx_6=(TextView)(findViewById(R.id.tr16));
            tx_6.setText(ty[5]);
            ImageView r_img6=(ImageView)(findViewById(R.id.ir6));
            r_img6.setImageResource(imgx[5]);

            TextView t_7=(TextView)(findViewById(R.id.tr7));
            t_7.setText(tx[6]);
            TextView tx_7=(TextView)(findViewById(R.id.tr17));
            tx_7.setText(ty[6]);
            ImageView r_img7=(ImageView)(findViewById(R.id.ir7));
            r_img7.setImageResource(imgx[6]);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getDate(long time, String tz) {

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE , MMM d");
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

