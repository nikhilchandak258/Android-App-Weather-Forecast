package com.elasticbeanstalk.weatherforecast_env7410.www.weatherforecast;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.TimeZone;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;


public class ResultActivity extends AppCompatActivity {
    String result = "";
    String city = "";
    String state = "";
    String unit = "";
    String lat = "";
    String lng = "";
    String icon="";
    String summary="";
    int temp;
    Context c;
    private CallbackManager callbackManager;
    ImageView fbicon;
    ShareDialog shareDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        TextView sum = (TextView) findViewById(R.id.summary);

        //Button detail=(Button) findViewById(R.id.more);
        try {

            result = getIntent().getStringExtra("result");
            city = getIntent().getStringExtra("city");
            state = getIntent().getStringExtra("state");
            unit = getIntent().getStringExtra("unit");
            JSONObject reader = new JSONObject(result);
            JSONObject current = reader.getJSONObject("currently");
            JSONObject daily = reader.getJSONObject("daily");
            // JSONObject data = daily.getJSONObject("data");
            JSONArray d1 = daily.getJSONArray("data");
            JSONObject d = d1.getJSONObject(0);
            lat = reader.getString("latitude");
            lng = reader.getString("longitude");

            String d_dew = "";
            String d_humid = "";
            String d_wind = "";
            String d_visi = "";
            String d_rain = "";
            String d_preci = "";
            String d_unit = "";

            //String lon=reader.getString("latitude");
            icon = current.getString("icon");
            summary = current.getString("summary");
            String timez = reader.getString("timezone");
            temp = current.getInt("temperature");
            double preci = current.getDouble("precipIntensity");
            int dew = current.getInt("dewPoint");
            Double humid = 100 * current.getDouble("humidity");
            Double wind = current.getDouble("windSpeed");
            int visi = current.getInt("visibility");
            Double rain = 100 * current.getDouble("precipProbability");

            if (unit.equalsIgnoreCase("us")) {
                d_unit = "\u00b0 F";
                d_dew = String.valueOf(dew) + "\u00b0 F";
                d_humid = String.valueOf(humid) + " %";
                d_wind = String.valueOf(wind) + " mph";
                d_visi = String.valueOf(visi) + " mi";
                d_rain = String.valueOf(rain) + " %";
                d_preci = get_preci(preci, unit);
            }
            if (unit.equalsIgnoreCase("si")) {
                d_unit = "\u00b0 C";
                d_dew = String.valueOf(dew) + "\u00b0 C";
                d_humid = String.valueOf(humid) + " %";
                d_wind = String.valueOf(wind) + " m/s";
                d_visi = String.valueOf(visi) + " km";
                d_rain = String.valueOf(rain) + " %";
                d_preci = get_preci(preci, unit);
            }


            long sunrise = d.getLong("sunriseTime");
            String rise = getDate(sunrise, timez);
            long sunset = d.getLong("sunsetTime");
            String set = getDate(sunset, timez);
            int low = d.getInt("temperatureMin");
            int high = d.getInt("temperatureMax");
            String highlow = "L:" + String.valueOf(low) + "\u00b0 | H:" + String.valueOf(high) + "\u00b0";


            fbicon = (ImageView)findViewById(R.id.fb);
            shareDialog = new ShareDialog(this);
            c=this;
            fbicon.setOnClickListener(new View.OnClickListener(){
                String description = null;
                String json_icon = null;
                String img_name = "";

                @Override
                public void onClick(View v){
                    if(ShareDialog.canShow(ShareLinkContent.class)) {
                        Intent intent1 = getIntent();
                        Bundle bd1 = intent1.getExtras();

                        if(unit.equalsIgnoreCase("us"))
                        description = summary + ", " + temp+"° F";
                        else
                        description = summary + ", " + temp+"° C";
                        img_name = "http://cs-server.usc.edu:38023/hw8621573/images/" + get_icon(icon, 1) + ".png";
                        Log.d("imageurl",img_name);

                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentTitle("Current Weather in " + city+", "+state)
                                .setContentDescription(description)
                                .setImageUrl(Uri.parse(img_name))
                                .setContentUrl(Uri.parse("http://forecast.io"))
                                .build();
                        Log.d("hhh","gghh");
                        shareDialog = new ShareDialog((Activity) c);
                        shareDialog.show(linkContent);

                        if(bd1==null)
                            Toast.makeText(c, "Post Unsuccessful", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(c, "Post successful", Toast.LENGTH_LONG).show();
                    }

                    shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                        @Override
                        public void onSuccess(Sharer.Result result) {
                            Toast.makeText(c, "Success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(c, "Post Unsuccessful", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(FacebookException error) {
                            error.printStackTrace();
                        }
                    });


                }
            });


            TextView t_temp = (TextView) findViewById(R.id.temp);
            TextView t_unit = (TextView) findViewById(R.id.tempunit);
            TextView t_highlow = (TextView) findViewById(R.id.highlow);
            TextView t_preci = (TextView) findViewById(R.id.preci);
            TextView t_dew = (TextView) findViewById(R.id.dew);
            TextView t_humid = (TextView) findViewById(R.id.humid);
            TextView t_wind = (TextView) findViewById(R.id.wind);
            TextView t_rain = (TextView) findViewById(R.id.rain);
            TextView t_visible = (TextView) findViewById(R.id.visible);
            TextView t_sunrise = (TextView) findViewById(R.id.sunrise);
            TextView t_sunset = (TextView) findViewById(R.id.sunset);


            //t.setText("Longitude:"+lon);
            //t.setText(result);
            ImageView main_image = (ImageView) findViewById(R.id.main_image);

            String x = get_icon(icon, 1);
            //t.setText("image" + icon);
            //Log.d("image", x);
            int z = getFlagResource(ResultActivity.this, x);
            //Log.d("icon", String.valueOf(z));
            main_image.setImageResource(z);


            sum.setText(summary + " in " + city + ", " + state);
            t_temp.setText(String.valueOf(temp));
            t_unit.setText(d_unit);
            t_highlow.setText(highlow);
            t_preci.setText(d_preci);
            t_dew.setText(d_dew);
            t_wind.setText(d_wind);
            t_humid.setText(d_humid);
            t_visible.setText(d_visi);
            t_rain.setText(d_rain);
            t_sunrise.setText(rise);
            t_sunset.setText(set);


        } catch (Exception e) {
            e.printStackTrace();
        }


       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public int getFlagResource(Context context, String name) {
        int resId = context.getResources().getIdentifier(name, "drawable", "com.elasticbeanstalk.weatherforecast_env7410.www.weatherforecast");
        return resId;
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

    public String get_preci(double p_preci, String p_unit) {
        String p_preci_dis = "";
        if (p_unit.equalsIgnoreCase("si"))
            p_preci = p_preci * 0.03937;
        if (p_preci >= 0 && p_preci < 0.002)
            p_preci_dis = "None";
        else if (p_preci >= 0.002 && p_preci < 0.017)
            p_preci_dis = "Very Light";
        else if (p_preci >= 0.017 && p_preci < 0.1)
            p_preci_dis = "Light";
        else if (p_preci >= 0.1 && p_preci < 0.4)
            p_preci_dis = "Moderate";
        else if (p_preci >= 0.4)
            p_preci_dis = "Heavy";

        return p_preci_dis;

    }

    private String getDate(long time, String tz) {

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        Date netDate = (new Date(time * 1000));
        sdf.setTimeZone(TimeZone.getTimeZone(tz));
        return sdf.format(netDate);

    }

    public void moreDetails(View v) {
        Intent intent2 = new Intent(ResultActivity.this, details.class);
        intent2.putExtra("result", result);
        intent2.putExtra("city", city);
        intent2.putExtra("state", state);
        intent2.putExtra("unit", unit);
        startActivity(intent2);
    }

    public void viewMap(View v) {
        Intent intent2 = new Intent(ResultActivity.this, MapActivity.class);
        intent2.putExtra("lat", lat);
        intent2.putExtra("lng", lng);
        startActivity(intent2);
    }




}
