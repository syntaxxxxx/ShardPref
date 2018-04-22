package fiqri.com.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class Orang {

    private String NAME_KEY = "name";
    private String EMAIL_KEY = "email";
    private String PHONE_KEY = "phone";
    private String UMUR_KEY = "umur";
    private String LOVE_KEY = "love";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    Orang(Context context) {
        String PREFS_NAME = "OrangPref";
        preferences = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
    }

    public void setName(String name) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME_KEY, name);
        editor.apply();
    }

    public String getName() {
        return preferences.getString(NAME_KEY, null);
    }

    void setEmail(String email) {
        editor = preferences.edit();
        editor.putString(EMAIL_KEY, email);
        editor.apply();
    }

    String getEmail() {
        return preferences.getString(EMAIL_KEY, null);
    }

    void setLove(boolean status) {
        editor = preferences.edit();
        editor.putBoolean(LOVE_KEY, status);
        editor.apply();
    }

    boolean isLove() {
        return preferences.getBoolean(LOVE_KEY, false);
    }

    void setPhone(String phone) {
        editor = preferences.edit();
        editor.putString(PHONE_KEY, phone);
        editor.apply();
    }

    String getPhone() {
        return preferences.getString(PHONE_KEY, null);
    }

    void setUmur(int umur) {
        editor = preferences.edit();
        editor.putInt(UMUR_KEY, umur);
        editor.apply();
    }

    int getUmur() {
        return preferences.getInt(UMUR_KEY, 0);
    }
}
