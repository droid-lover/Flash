package com.jarvis.flash.interfaces.get_audio;

import com.jarvis.flash.views.activities.MainActivity;
import com.jarvis.flash.armoury.Armoury;
import com.jarvis.flash.models.AudioList;
import com.jarvis.flash.networking.APIs;
import com.jarvis.flash.networking.ApiClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GetAudioPresenterImpl implements IGetAudioPresenter {

    private IGetAudioView mIGetAudioView;
    private MainActivity mainActivity;
    private CompositeDisposable disposable;


    public GetAudioPresenterImpl(IGetAudioView mIGetAudioView, CompositeDisposable disposable) {
        this.mIGetAudioView = mIGetAudioView;
        this.mainActivity = (MainActivity) mIGetAudioView;
        this.disposable = disposable;

    }

    @Override
    public void getAudioList() {
        Armoury.showProgressDialog(mainActivity);
        disposable.add(ApiClient.getClient().create(APIs.class).getAllAudios()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<AudioList>() {
                    @Override
                    public void onSuccess(AudioList value) {
                        Armoury.hideProgressDialog();
                        if (value != null)
                            mainActivity.getAudioSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Armoury.hideProgressDialog();
                        if (e != null) {
                            mainActivity.getAudioError(e.getLocalizedMessage().toString());
                        } else {
                            mainActivity.getAudioError(Armoury.ERROR_MESSAGE);
                        }
                    }
                }));
    }


}
