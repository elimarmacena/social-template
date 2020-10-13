package com.example.socialtemplate.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Configurations {
    static String MY_PREFS= "myprefs";
    public static String BASE_URL = "http://34.125.85.252/social/";
    public static  String APPID = "1:998288060137:android:64af6f99426ac0dd108a78";

    public static void setToken(Context cntx, String username){
        SharedPreferences myprefs = cntx.getSharedPreferences(MY_PREFS,0);
        SharedPreferences.Editor editor = myprefs.edit();
        editor.putString("token",username);
        editor.apply();
    }

    public static String getToken(Context cntx){
        SharedPreferences myPrefs = cntx.getSharedPreferences(MY_PREFS,0);
        return myPrefs.getString("token","");
    }
}
