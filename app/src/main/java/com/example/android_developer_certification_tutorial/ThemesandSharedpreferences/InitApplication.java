package com.example.android_developer_certification_tutorial.ThemesandSharedpreferences;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.android_developer_certification_tutorial.R;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;

public class InitApplication extends Application {

    //For Night Mode
//    public static final String NIGHT_MODE = "NIGHT_MODE";
//    private boolean isNightModeEnabled = false;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        singleton = this;
//        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//        this.isNightModeEnabled = mPrefs.getBoolean(NIGHT_MODE, false);
//    }
//
//    public boolean isNightModeEnabled() {
//        return isNightModeEnabled;
//    }
//
//    public void setIsNightModeEnabled(boolean isNightModeEnabled) {
//        this.isNightModeEnabled = isNightModeEnabled;
//
//        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = mPrefs.edit();
//        editor.putBoolean(NIGHT_MODE, isNightModeEnabled);
//        editor.apply();
//    }

    private static int mTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_WHITE = 1;
    public final static int THEME_BLUE = 2;
    public final static int THEME_UNKNOWN = 3;

    private static InitApplication singleton = null;

    public static InitApplication getInstance() { //Lazy Initialization

        if(singleton == null) //Not thread safe
        {
            singleton = new InitApplication();
            singleton.mTheme =  THEME_UNKNOWN;

        }
        return singleton;
    }

    public void changeToTheme(Activity activity, int theme)
    {
        mTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public void onActivityCreateSetTheme(Activity activity)
    {
        SharedPreferences pref = activity.getApplicationContext().getSharedPreferences( "MyPref",Activity.MODE_PRIVATE );
//        MODE_PRIVATE: the default mode, where the created file can only be accessed by the calling application
//        MODE_WORLD_READABLE: Creating world-readable files is very dangerous, and likely to cause security holes in applications
//        MODE_WORLD_WRITEABLE: Creating world-writable files is very dangerous, and likely to cause security holes in applications
//        MODE_MULTI_PROCESS: This method will check for modification of preferences even if the Shared Preference instance has already been loaded
//        MODE_APPEND: This will append the new preferences with the already existing preferences
//        MODE_ENABLE_WRITE_AHEAD_LOGGING: Database open flag. When it is set, it would enable write ahead logging by default
        SharedPreferences.Editor editor = pref.edit();

        if(mTheme==THEME_UNKNOWN) {
            mTheme = pref.getInt("PreferedTheme", THEME_DEFAULT); // getting Prefered theme if it exists
        }

        editor.putInt("PreferedTheme", mTheme); // Storing integer
        editor.commit();

        switch (mTheme)
        {
            default:
            case THEME_DEFAULT:
                activity.setTheme( R.style.FirstTheme);
                break;
            case THEME_WHITE:
                activity.setTheme(R.style.SecondTheme);
                break;
            case THEME_BLUE:
                activity.setTheme(R.style.ThirdTheme);
                break;
        }
    }

}
