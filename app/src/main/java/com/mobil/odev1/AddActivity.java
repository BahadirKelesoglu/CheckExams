package com.mobil.odev1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ettotal, ettrue, etfalse, etcode;
    private int id =SharedPrefManager.getInstance(this).getKeyUserId();
    private Double net;

    private Button btnadd;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ettotal = (EditText) findViewById(R.id.ettotalquestions);
        ettrue = (EditText) findViewById(R.id.ettruequestions);
        etfalse = (EditText) findViewById(R.id.etfalsequestions);

        etcode = (EditText) findViewById(R.id.etexamcode);

        btnadd = (Button) findViewById(R.id.btnaddexam);
        btnadd.setOnClickListener(this);


        progressDialog = new ProgressDialog(this);

    }

    private void AddExam(){
        final String True = ettrue.getText().toString().trim();
        final String Total = ettotal.getText().toString().trim();
        final String False = etfalse.getText().toString().trim();
        final String Code = etcode.getText().toString().trim();
        net = Float.parseFloat(True)  - Float.parseFloat(False) * 0.25;
        progressDialog.setMessage("Adding Exam...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_ADD_EXAM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("dogru", True);
                params.put("yanlis", False);
                params.put("soru_sayisi", Total);
                params.put("exam_code", Code);
                params.put("user_id", String.valueOf(id));
                params.put("net",String.valueOf(net));
                return params;
            }


        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    @Override
    public void onClick(View view) {
        if(view == btnadd)
            AddExam();
    }
}