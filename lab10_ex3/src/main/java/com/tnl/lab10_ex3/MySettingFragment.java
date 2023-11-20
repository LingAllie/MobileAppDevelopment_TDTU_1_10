package com.tnl.lab10_ex3;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class MySettingFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener{

    private EditTextPreference emailPref;
    private ListPreference songPref;
    private Preference pref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);

        emailPref = findPreference("email");
        songPref = findPreference("song");
        pref = findPreference("view");

        if(emailPref.getText() != null){
            emailPref.setSummary(emailPref.getText());
        }
        if(songPref.getValue() != null){
            songPref.setSummary(songPref.getEntry());
        }

        emailPref.setOnPreferenceChangeListener(this);
        songPref.setOnPreferenceChangeListener(this);

        pref.setOnPreferenceClickListener((v) -> {
            Log.e("TAG", "Cắt đôi nỗi sầu");
            return true;
        });
    }

    @Override
    public boolean onPreferenceChange(@NonNull Preference f, Object newValue) {
        if(f.getKey().equals("email")){
            String email = (String) newValue;
            if(email.contains("@")){
                emailPref.setSummary(email);
                return true;
            }else {
                return false;
            }


        } else if (f.getKey().equals("song")) {
            int v = Integer.parseInt((String) newValue);
            songPref.setSummary(songPref.getEntries()[v]);
        }

        return false;
    }
}
