package com.tnl.lab10_ex3;

import android.os.Bundle;

import androidx.preference.Preference;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

public class MySettingFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    private EditTextPreference emailPref;
    private ListPreference songPref;
    private Preference pref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);

        emailPref = findPreference("email");
        songPref =  findPreference("song");
        pref = findPreference("view");

        if (emailPref.getText() != null) {
            emailPref.setSummary(emailPref.getText());
        }
        if (songPref.getValue() != null) {
            songPref.setSummary(songPref.getEntry());
        }

        emailPref.setOnPreferenceChangeListener(this);
        songPref.setOnPreferenceChangeListener(this);

        pref.setOnPreferenceChangeListener(this);
    }


    @Override
    public boolean onPreferenceChange(Preference f, Object newValue) {
        if (f.getKey().equals("email")) {
            String email = (String) newValue;
            if (email.contains("@")) {
                emailPref.setSummary(email);
                return true;
            } else {
                return false;
            }
        } else if (f.getKey().equals("song")) {
            int v = Integer.parseInt(((String) newValue));
            songPref.setSummary(songPref.getEntries()[v]);
        }
        return false;
    }
}
