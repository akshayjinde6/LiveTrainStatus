package com.example.akshayjinde.livetrainstatus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class data extends AppCompatActivity {

    EditText e1;
    Button b1;
    DatePicker dp;
    String train_no,api_url;
    int day,month,year;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        e1 = (EditText)findViewById(R.id.editText);
        b1 = (Button)findViewById(R.id.buttonmain);
        dp = (DatePicker)findViewById(R.id.date);
        pd = new ProgressDialog(this);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Please Wait...");
                pd.setCancelable(false);
                pd.show();
                train_no = e1.getText().toString();
                day = dp.getDayOfMonth();
                month = dp.getMonth();
                month = month +1;
                year = dp.getYear();
                //6568bmdjac
                //lbdc1dea35
                //g2e4eifeka
                api_url = "http://api.railwayapi.com/v2/live/train/"+train_no+"/date/"+day+"-"+month+"-"+year+"/apikey/lbdc1dea35/";
                new APICall().execute(api_url);

            }
        });
    }

    class APICall extends AsyncTask<String, String, String>{


        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;

            try {


                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuffer sb = new StringBuffer();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                String responce1;

                responce1 = sb.toString();

                return responce1;
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            pd.dismiss();
            try {
                if(result.isEmpty()){
                    Toast.makeText(data.this,"Connection Problem !! Try Again ",Toast.LENGTH_LONG).show();
                }
                else{
                Intent i = new Intent(data.this, result.class);
                i.putExtra("jsonresponce", result);
                startActivity(i);
                }
            }catch (Exception e){
                Toast.makeText(data.this,"Unknown Error Occured !", Toast.LENGTH_SHORT).show();
                return;
            }

        }
    }

}


