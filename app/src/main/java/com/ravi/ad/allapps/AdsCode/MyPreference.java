package com.ravi.ad.allapps.AdsCode;

import android.content.Context;
import android.content.SharedPreferences;


public class MyPreference {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    Context context;

    public MyPreference(Context context) {
        this.context = com.appwood.mylibrarys.MyHelpers.getInstanceHelp();
        sharedPreferences = com.appwood.mylibrarys.MyHelpers.getInstanceHelp().getSharedPreferences("datastore", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


//    public static void setscore_list(ArrayList<ScoreList> listItems) {
//        Gson gson = new Gson();
//        editor.putString("score_list", gson.toJson(listItems)).commit();
//        editor.apply();
//    }
//
//    public static ArrayList<ScoreList> getscore_list() {
//        Gson gson = new Gson();
//        ArrayList<ScoreList> list = gson.fromJson(sharedPreferences.getString("score_list", null), new TypeToken<List<ScoreList>>() {
//        }.getType());
//        return list;
//    }

}