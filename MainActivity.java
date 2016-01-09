package com.elasticbeanstalk.weatherforecast_env7410.www.weatherforecast;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private static String urlString;
    String[] stateCodes;
    String[] stateNames;
    EditText astreet;
    EditText acity;
    Spinner astate;
    TextView errormsg;
    String selectedState;
    RadioGroup unit;
    String pass = "true";
    String unit1 = "";
    RadioButton button;
    RadioGroup group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        group = (RadioGroup) findViewById(R.id.unit);
        button = new RadioButton(this);
        RadioButton button2 = new RadioButton(this);
        button.setText("Fahrenheit");
        group.addView(button);
        button2.setText("Celsius");
        group.addView(button2);
        group.check(button.getId());

        //State List
        stateCodes = new String[52];  // 50 states + DC + blank
        stateNames = new String[52];

        // set first item to blank
        int stateCount = 0;
        stateCodes[stateCount] = "";
        stateNames[stateCount++] = "Select";

        // now add all US states
        stateCodes[stateCount] = "AL";
        stateNames[stateCount++] = "Alabama";
        stateCodes[stateCount] = "AK";
        stateNames[stateCount++] = "Alaska";
        stateCodes[stateCount] = "AZ";
        stateNames[stateCount++] = "Arizona";
        stateCodes[stateCount] = "AR";
        stateNames[stateCount++] = "Arkansas";
        stateCodes[stateCount] = "CA";
        stateNames[stateCount++] = "California";
        stateCodes[stateCount] = "CO";
        stateNames[stateCount++] = "Colorado";
        stateCodes[stateCount] = "CT";
        stateNames[stateCount++] = "Connecticut";
        stateCodes[stateCount] = "DE";
        stateNames[stateCount++] = "Delaware";
        stateCodes[stateCount] = "FL";
        stateNames[stateCount++] = "Florida";
        stateCodes[stateCount] = "GA";
        stateNames[stateCount++] = "Georgia";
        stateCodes[stateCount] = "HI";
        stateNames[stateCount++] = "Hawaii";
        stateCodes[stateCount] = "ID";
        stateNames[stateCount++] = "Idaho";
        stateCodes[stateCount] = "IA";
        stateNames[stateCount++] = "Iowa";
        stateCodes[stateCount] = "IL";
        stateNames[stateCount++] = "Illinois";
        stateCodes[stateCount] = "IN";
        stateNames[stateCount++] = "Indiana";
        stateCodes[stateCount] = "KS";
        stateNames[stateCount++] = "Kansas";
        stateCodes[stateCount] = "KY";
        stateNames[stateCount++] = "Kentucky";
        stateCodes[stateCount] = "LA";
        stateNames[stateCount++] = "Louisiana";
        stateCodes[stateCount] = "ME";
        stateNames[stateCount++] = "Maine";
        stateCodes[stateCount] = "MD";
        stateNames[stateCount++] = "Maryland";
        stateCodes[stateCount] = "MA";
        stateNames[stateCount++] = "Massachusetts";
        stateCodes[stateCount] = "MI";
        stateNames[stateCount++] = "Michigan";
        stateCodes[stateCount] = "MN";
        stateNames[stateCount++] = "Minnesota";
        stateCodes[stateCount] = "MS";
        stateNames[stateCount++] = "Mississippi";
        stateCodes[stateCount] = "MO";
        stateNames[stateCount++] = "Missouri";
        stateCodes[stateCount] = "MT";
        stateNames[stateCount++] = "Montana";
        stateCodes[stateCount] = "NE";
        stateNames[stateCount++] = "Nebraska";
        stateCodes[stateCount] = "NV";
        stateNames[stateCount++] = "Nevada";
        stateCodes[stateCount] = "NH";
        stateNames[stateCount++] = "New Hampshire";
        stateCodes[stateCount] = "NJ";
        stateNames[stateCount++] = "New Jersey";
        stateCodes[stateCount] = "NM";
        stateNames[stateCount++] = "New Mexico";
        stateCodes[stateCount] = "NY";
        stateNames[stateCount++] = "New York";
        stateCodes[stateCount] = "NC";
        stateNames[stateCount++] = "North Carolina";
        stateCodes[stateCount] = "ND";
        stateNames[stateCount++] = "North Dakota";
        stateCodes[stateCount] = "OH";
        stateNames[stateCount++] = "Ohio";
        stateCodes[stateCount] = "OK";
        stateNames[stateCount++] = "Oklahoma";
        stateCodes[stateCount] = "OR";
        stateNames[stateCount++] = "Oregon";
        stateCodes[stateCount] = "PA";
        stateNames[stateCount++] = "Pennsylvania";
        stateCodes[stateCount] = "RI";
        stateNames[stateCount++] = "Rhode Island";
        stateCodes[stateCount] = "SC";
        stateNames[stateCount++] = "South Carolina";
        stateCodes[stateCount] = "SD";
        stateNames[stateCount++] = "South Dakota";
        stateCodes[stateCount] = "TN";
        stateNames[stateCount++] = "Tennessee";
        stateCodes[stateCount] = "TX";
        stateNames[stateCount++] = "Texas";
        stateCodes[stateCount] = "UT";
        stateNames[stateCount++] = "Utah";
        stateCodes[stateCount] = "VT";
        stateNames[stateCount++] = "Vermont";
        stateCodes[stateCount] = "VA";
        stateNames[stateCount++] = "Virginia";
        stateCodes[stateCount] = "WA";
        stateNames[stateCount++] = "Washington";
        stateCodes[stateCount] = "DC";
        stateNames[stateCount++] = "Washington DC";
        stateCodes[stateCount] = "WV";
        stateNames[stateCount++] = "West Virgina";
        stateCodes[stateCount] = "WI";
        stateNames[stateCount++] = "Wisconsin";
        stateCodes[stateCount] = "WY";
        stateNames[stateCount++] = "Wyoming";

        Spinner dropdown = (Spinner) findViewById(R.id.stateList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, stateNames);
        dropdown.setAdapter(adapter);
        astreet = (EditText) findViewById(R.id.street);
        acity = (EditText) findViewById(R.id.city);
        astate = (Spinner) findViewById(R.id.stateList);
        unit = (RadioGroup) findViewById(R.id.unit);
        ImageView img = (ImageView)findViewById(R.id.imageButton);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://forecast.io/"));
                startActivity(intent);
            }
        });


    }

    public void showDetails(View view) {
        Intent intent = new Intent(MainActivity.this, Description.class);
        startActivity(intent);
    }

    public void onClickClear(View v)
    {
        astreet.setText("");
        acity.setText("");
        astate.setSelection(0);
        errormsg.setText("");
        group.check(button.getId());
    }

    public void validateForm(View view) {
        pass = "true";
        errormsg=(TextView)findViewById(R.id.errormsg);
        errormsg.setText("");
        selectedState = stateCodes[astate.getSelectedItemPosition()];
        if (astreet.getText().toString().trim().equals("")) {
            //Toast.makeText(MainActivity.this, "Please Enter Street Address", Toast.LENGTH_LONG).show();
            pass = "false";
            errormsg.setText("Please Enter Street Addrees");
        }
        if (acity.getText().toString().trim().equals("") && (pass.equalsIgnoreCase("true"))) {
            //Toast.makeText(MainActivity.this, "Please Enter City Name", Toast.LENGTH_LONG).show();
            pass = "false";
            errormsg.setText("Please Enter City");
        }
        if (selectedState.trim().equals("")&& (pass.equalsIgnoreCase("true"))) {
            //Toast.makeText(MainActivity.this, "Please Select State", Toast.LENGTH_LONG).show();
            pass = "false";
            errormsg.setText("Please Select State");
        }
        if (pass == "true") {
            int sel_unit = unit.getCheckedRadioButtonId();

            if (sel_unit == 1)
                unit1 = "us";
            else
                unit1 = "si";
            String x = astreet.getText().toString().trim();
            x = x.replace(" ", "%20");
            String y = acity.getText().toString().trim();
            y = y.replace(" ", "%20");
            String z = selectedState;
            //Log.d("Debug", x+","+y+","+z);
            //Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
            urlString = "http://weatherforecast-env7410.elasticbeanstalk.com/aresult.php?text1=" + x + "&text2=" + y + "&text3=" + z + "&text4=" + unit1;
            //urlString = "http://cs-server.usc.edu:38023/retrieve.php?text1=USC&text2=Los+Angeles&text3=CA&text4=us";
            Log.d("URL_gen", urlString);
            new ProcessJSON().execute(urlString);

        }


    }

    private class ProcessJSON extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            // Return the data from specified url
            return stream;
        }

        @Override
        protected void onPostExecute(String stream) {

            if (stream.equalsIgnoreCase("Failed")) {
                Toast.makeText(MainActivity.this, "Wrong Values", Toast.LENGTH_LONG).show();
            }
            else{
                try {
                    // Get the full HTTP Data as JSONObject
                    //JSONObject reader = new JSONObject(stream);
                    //Toast.makeText(MainActivity.this, "changing activity", Toast.LENGTH_LONG).show();
                    //Log.d("Stream!!!!", stream);
                    String y2 = acity.getText().toString().trim();
                    String z2 = selectedState;

                    Intent intent2 = new Intent(MainActivity.this, ResultActivity.class);
                    intent2.putExtra("result", stream);
                    intent2.putExtra("city", y2);
                    intent2.putExtra("state", z2);
                    intent2.putExtra("unit", unit1);
                    startActivity(intent2);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            System.exit(0);
            ;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
