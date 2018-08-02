package com.jarvis.flash.networking;


import com.jarvis.flash.models.AudioList;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface APIs {

    @GET("bins/mxcsl")
    Single<AudioList> getAllAudios();

}


