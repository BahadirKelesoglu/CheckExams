package com.mobil.odev1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.display.DeviceProductInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class profileActivity extends AppCompatActivity {
    private int user_id =SharedPrefManager.getInstance(this).getKeyUserId();
    private TextView tvusername1, tvemail;
    private ListView lv;
    MyAdapter adapter;
    private Button btnrefresh;
    public static  ArrayList<exams> Examlist = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        tvusername1 = (TextView) findViewById(R.id.tvusername1);
        lv = findViewById(R.id.lv);

        adapter = new MyAdapter(this, Examlist);
        btnrefresh = (Button) findViewById(R.id.btnrefresh);
        lv.setAdapter(adapter);
        // sharedprefmanager dan fonksiyonu çektik
        tvusername1.setText(SharedPrefManager.getInstance(this).getUserName());


        getExams();



    }

    private void getExams() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LIST_EXAM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            Examlist.clear();
                        try {

                            JSONArray obj = new JSONArray(response);
                            for(int i = 0; i<obj.length(); i++){
                                JSONObject examobj = obj.getJSONObject(i);

                                String ID =String.valueOf(i+1);
                                String True = examobj.getString("dogru");
                                String False = examobj.getString("yanlis");
                                String Net = examobj.getString("net");
                                String Code = examobj.getString("exam_code");
                                String Total = examobj.getString("soru_sayisi");
                                exams exams = new exams(ID, True, False, Net, Code, Total);
                                Examlist.add(exams);
                                adapter.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(
                        getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_id",String.valueOf(user_id) );
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.add:
                startActivity(new Intent(this, AddActivity.class));
                break;

            case R.id.rank:
            startActivity(new Intent(this, RankActivity.class));
            break;
        }

        return true;
    }

    public void resume(View view) {
        if(view == btnrefresh){
            Examlist.clear();
            adapter.notifyDataSetChanged();
            getExams();
        }

    }

    public void refreshrank(View view) {
    }
}