package com.nsicyber.travelandshare.repositories

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BottomSheetRepository @Inject constructor() {

    var mediaPlayer = MediaPlayer()

    private var isBuffering = false


     fun resume(){
        mediaPlayer.start()
    }


     fun setUrl(uri: String?) {
        if(uri == null) return;
        mediaPlayer.setDataSource(uri);
    }

}