package com.jarvis.flash.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Audio implements Serializable{

    @SerializedName("itemId")
    private String itemId;
    @SerializedName("desc")
    private String desc;
    @SerializedName("audio")
    private String audio;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

}

