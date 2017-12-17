package com.example.akshayjinde.livetrainstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class result extends AppCompatActivity {

    TextView result,train_no,sta_name,start_date,sch_arr,act_arr,sch_dep,act_dep,sta_code;
    String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = (TextView)findViewById(R.id.result_text);
        train_no = (TextView)findViewById(R.id.trno_id);
        sta_name = (TextView)findViewById(R.id.sta_id);
        sta_code = (TextView)findViewById(R.id.code_id);
        start_date = (TextView)findViewById(R.id.date_id);
        sch_arr = (TextView)findViewById(R.id.scharr_id);
        act_arr = (TextView)findViewById(R.id.actarr_id);
        sch_dep = (TextView)findViewById(R.id.schdep_id);
        act_dep = (TextView)findViewById(R.id.actdep_id);

        response = getIntent().getStringExtra("jsonresponce");

            try {
                JSONObject parent = new JSONObject(response);
                String code = parent.getString("response_code");
                if (code.equals("210")) {
                    Toast.makeText(result.this, "Train doesn't Run on selected Day !", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    result.setText(parent.getString("position"));
                    train_no.setText(parent.getString("train_number"));
                    JSONObject parent1 = parent.getJSONObject("current_station");
                    JSONObject parent2 = parent1.getJSONObject("station");
                    sta_name.setText(parent2.getString("name"));
                    sta_code.setText(parent2.getString("code"));
                    start_date.setText(parent.getString("start_date"));
                    sch_arr.setText(parent1.getString("scharr"));
                    act_arr.setText(parent1.getString("actarr"));
                    sch_dep.setText(parent1.getString("schdep"));
                    act_dep.setText(parent1.getString("actdep"));
                }

            } catch (Exception e) {

            }


    }
}
