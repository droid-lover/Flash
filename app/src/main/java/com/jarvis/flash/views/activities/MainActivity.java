package com.jarvis.flash.views.activities;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.jarvis.flash.R;
import com.jarvis.flash.adapter.AudioAdapter;
import com.jarvis.flash.interfaces.get_audio.GetAudioPresenterImpl;
import com.jarvis.flash.interfaces.get_audio.IGetAudioPresenter;
import com.jarvis.flash.interfaces.get_audio.IGetAudioView;
import com.jarvis.flash.models.AudioList;
import com.jarvis.flash.views.fragments.AudioFragment;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements IGetAudioView, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {

    private final String TAG = MainActivity.class.getName();
    private CompositeDisposable disposable = new CompositeDisposable();
    private IGetAudioPresenter mIGetAudioPresenter;
    private AudioAdapter mAudioAdapter;
    private AudioList mAudioList;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_pager)
    ViewPager audioViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        gettingAllAudios();
    }

    private void initialization() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mediaPlayer = new MediaPlayer();
    }

    private void gettingAllAudios() {
        mIGetAudioPresenter = new GetAudioPresenterImpl(MainActivity.this, disposable);
        mIGetAudioPresenter.getAudioList();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void getAudioSuccess(AudioList audioList) {
        mAudioList = audioList;
        setUpAudioLayout(audioList);
    }

    @Override
    public void getAudioError(String error) {
        Log.d(TAG, "data=" + error);
    }

    private void setUpAudioLayout(AudioList audioList) {
        if (audioList != null) {
            Log.d(TAG, "data=" + new Gson().toJson(audioList));
            settingUI(audioList);
        }
    }

    private void settingUI(AudioList audioList) {
        mAudioAdapter = new AudioAdapter(getSupportFragmentManager(), audioList);
        mTabLayout.setupWithViewPager(audioViewPager);
        audioViewPager.setAdapter(mAudioAdapter);
        audioViewPager.setOffscreenPageLimit(0);

    }

    public void playAudio(int pos) throws IOException {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            mediaPlayer = new MediaPlayer();
        }
        Log.d("playAudio", "audio=" + pos);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(mAudioList.getData().get(pos).getAudio());
            mediaPlayer.setOnErrorListener(MainActivity.this);
            mediaPlayer.setOnPreparedListener(MainActivity.this);
            mediaPlayer.prepareAsync();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        audioViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                try {
                    playAudio(position);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.seekTo(0);
        mp.start();
        mp.setLooping(true);
    }
}

