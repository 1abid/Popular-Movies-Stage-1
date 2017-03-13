package degree.nano.udacity.abidhasan.com.popularmoviesstageone.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

import degree.nano.udacity.abidhasan.com.popularmoviesstageone.R;

/**
 * Created by VutkaBilai on 3/13/17.
 * mail : la4508@gmail.com
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    public  final String TAG = getClass().getSimpleName();


    SharedPreferences preferences ;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        //add xml
        addPreferencesFromResource(R.xml.settings);

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        onSharedPreferenceChanged(preferences , getString(R.string.sort_key));
    }


    @Override
    public void onResume() {
        super.onResume();
        //unregister the preferenceChange listener
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Preference preference = findPreference(s);
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(sharedPreferences.getString(s, ""));
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            preference.setSummary(sharedPreferences.getString(s, ""));

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //unregister the preference change listener
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

}
