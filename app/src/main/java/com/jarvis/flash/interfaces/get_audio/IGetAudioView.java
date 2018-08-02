package com.jarvis.flash.interfaces.get_audio;

import com.jarvis.flash.models.AudioList;

public interface IGetAudioView {

    void getAudioSuccess(AudioList audioList);

    void getAudioError(String error);

}
