package com.android.settings.carbon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;

import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class About extends SettingsPreferenceFragment {

    public static final String TAG = "About";

    Preference mGooUrl;
    Preference mIrcUrl;
    Preference mSourceUrl;
    Preference mFacebookUrl;
    Preference mTwitterUrl;
    Preference mGooglePlusUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.about_rom);
        mGooUrl = findPreference("carbonrom_goo");
        mIrcUrl = findPreference("carbonrom_irc");
        mSourceUrl = findPreference("carbonrom_source");
        mFacebookUrl = findPreference("carbonrom_facebook");
        mTwitterUrl = findPreference("carbonrom_twitter");
        mGooglePlusUrl = findPreference("carbonrom_googleplus");

        PreferenceGroup devsGroup = (PreferenceGroup) findPreference("devs");
        ArrayList<Preference> devs = new ArrayList<Preference>();
        for (int i = 0; i < devsGroup.getPreferenceCount(); i++) {
            devs.add(devsGroup.getPreference(i));
        }
        devsGroup.removeAll();
        devsGroup.setOrderingAsAdded(false);
        Collections.shuffle(devs);
        for(int i = 0; i < devs.size(); i++) {
            Preference p = devs.get(i);
            p.setOrder(i);

            devsGroup.addPreference(p);
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mGooUrl) {
            launchUrl("http://goo.im/devs/carbon");
        } else if (preference == mIrcUrl) {
            launchUrl("http://webchat.freenode.net/?channels=teamcarbon");
        } else if (preference == mSourceUrl) {
            launchUrl("https://github.com/carbondev");
        } else if (preference == mFacebookUrl) {
            launchUrl("http://www.facebook.com/carbonrom");
        } else if (preference == mTwitterUrl) {
            launchUrl("https://twitter.com/carbonrom");
        } else if (preference == mGooglePlusUrl) {
            launchUrl("https://plus.google.com/u/1/103315278250477995368/posts");
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private void launchUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent donate = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(donate);
    }
}