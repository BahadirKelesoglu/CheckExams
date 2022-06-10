package com.mobil.odev1;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;


    private static Context mCtx;
    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_EMAIL = "useremail";
    private static final String KEY_USER_ID = "userid";
    private SharedPrefManager(Context context) {
        mCtx = context;


    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
    public boolean userLogin(int id, String username, String email){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID,id);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USERNAME,username);
        editor.apply();

        return true;

    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //(key,null) => key sharedpreferences içinde var mı yoksa null mı sorusu
        //yukarıda herşeyi sharedpreferencesa kaydettik
        if(sharedPreferences.getString(KEY_USERNAME,null) != null){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
    //user name i göstermek için fonksiyon
    public String getUserName(){
        //bunu her türlü aynı yazıyoruz galiba
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //username i getir yoksa null döndür
        return sharedPreferences.getString(KEY_USERNAME,null);
    }

    public int getKeyUserId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //(key,null) => key sharedpreferences içinde var mı yoksa null mı sorusu
        //yukarıda herşeyi sharedpreferencesa kaydettik
        return sharedPreferences.getInt(KEY_USER_ID,5);

    }



    public String getUserEmail(){
        //bunu her türlü aynı yazıyoruz galiba
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //username i getir yoksa null döndür
        return sharedPreferences.getString(KEY_USER_EMAIL,null);
    }






}

