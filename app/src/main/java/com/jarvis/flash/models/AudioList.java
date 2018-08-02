package com.jarvis.flash.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AudioList implements Serializable{

    @SerializedName("data")
    private List<Audio> data = null;

    public List<Audio> getData() {
        return data;
    }

    public void setData(List<Audio> data) {
        this.data = data;
    }

}
