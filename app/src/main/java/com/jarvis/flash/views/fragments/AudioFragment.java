package com.jarvis.flash.views.fragments;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jarvis.flash.R;
import com.jarvis.flash.models.Audio;
import com.jarvis.flash.views.activities.MainActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AudioFragment extends Fragment {


    public AudioFragment() {
        // Required empty public constructor
    }

    //Current Audio Object
    private Audio mAudio;
    /*Views*/
    @BindView(R.id.tv_audio_description)
    TextView mAudioDescriptionTextView;
    @BindView(R.id.tv_item_id)
    TextView mAudioItemId;


    public static Fragment newInstance(Audio audio) {
        AudioFragment audioFragment = new AudioFragment();
        Bundle args = new Bundle();
        args.putSerializable("audio_object", audio);
        audioFragment.setArguments(args);
        return audioFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        mAudio = (Audio) bundle.getSerializable("audio_object");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audio, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mAudio != null) {
            if (!TextUtils.isEmpty(mAudio.getDesc())) {
                mAudioDescriptionTextView.setText(mAudio.getDesc());
            }
            if (!TextUtils.isEmpty(mAudio.getItemId())) {
                mAudioItemId.setText("Audio Item ID - " + mAudio.getItemId());
            }
        }
    }

}
