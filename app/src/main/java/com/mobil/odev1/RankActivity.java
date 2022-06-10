package com.mobil.odev1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.Map;

public class RankActivity extends AppCompatActivity  {
    ListView lvrank;
    RankAdapter adapter;
    Button btnrefresh;
    EditText etentercode;
    public static ArrayList<rank> rankArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        btnrefresh = findViewById(R.id.btnrefresh1);
        etentercode = findViewById(R.id.etentercode);
        lvrank = findViewById(R.id.lvrank);
        adapter = new RankAdapter(this, rankArrayList);
        lvrank.setAdapter(adapter);



    }

    private void takedata() {
        final String exam_code = etentercode.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LIST_RANK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        rankArrayList.clear();
                        try {

                            JSONArray obj = new JSONArray(response);
                            for(int i = 0; i<obj.length(); i++){
                                JSONObject rankobj = obj.getJSONObject(i);

                                String ID =String.valueOf(i+1);
                                String username = rankobj.getString("username");
                                String True = rankobj.getString("dogru");
                                String False = rankobj.getString("yanlis");
                                String Net = rankobj.getString("net");
                                String Code = rankobj.getString("exam_code");
                                String Total = rankobj.getString("soru_sayisi");
                                rank rank = new rank(ID, username, Net, Code);
                                rankArrayList.add(rank);
                                adapter.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(
                        getApplicationContext(),
                        "ERROR 404",
                        Toast.LENGTH_LONG
                ).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("exam_code",exam_code);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void refreshrank(View view) {
        if(view == btnrefresh) {
            rankArrayList.clear();
            adapter.notifyDataSetChanged();
            takedata();
        }
    }
}