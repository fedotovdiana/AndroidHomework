package com.itis.template.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.itis.template.recycler.Callback
import com.itis.template.recycler.Data

class MusicPlayerService : Service() {

    lateinit var mp: MediaPlayer
    lateinit var callback: Callback
    private val binder = MBinder()
    private var current: Int = 0
    val songs = Data.songs

    private fun start() {
        mp = MediaPlayer.create(this, Data.songs.get(current).res)
        mp.setOnCompletionListener {
            next()
        }
        mp.start()
    }

    fun init(cb: Callback, curr: Int) {
        current = curr
        callback = cb
        start()
    }

    fun next() {
        current++
        if (current == songs.size) current = 0
        callback.songClick(current)
        mp.release()
        start()
        Log.d("tag", "NEXT")
    }

    fun play() {
        if (mp.isPlaying) {
            mp.pause()
        } else {
            mp.start()
        }
    }

    fun pre() {
        current--
        if (current == -1) current = songs.size - 1
        callback.songClick(current)
        mp.release()
        start()
    }

    fun stop() {
        mp.release()
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.release()
    }

    inner class MBinder : Binder() {
        fun getService() = this@MusicPlayerService
    }
}