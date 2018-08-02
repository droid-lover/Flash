package com.jarvis.flash.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jarvis.flash.models.Audio;
import com.jarvis.flash.models.AudioList;
import com.jarvis.flash.views.fragments.AudioFragment;

public class AudioAdapter extends FragmentStatePagerAdapter {
    AudioList audioList;
    Fragment fragment = null;

    public AudioAdapter(FragmentManager fm, AudioList audioList) {
        super(fm);
        this.audioList = audioList;
    }

    @Override
    public Fragment getItem(int position) {

        for (int i = 0; i < audioList.getData().size(); i++) {
            if (i == position) {
                Audio audio = audioList.getData().get(i);
                fragment= AudioFragment.newInstance(audio);
                break;
            }
        }
        return fragment;

    }

    @Override
    public int getCount() {
        return audioList.getData().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position);
    }
}
