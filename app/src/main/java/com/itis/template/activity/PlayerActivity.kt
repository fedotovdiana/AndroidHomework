package com.itis.template.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.itis.template.R
import com.itis.template.recycler.Callback
import com.itis.template.recycler.Data
import com.itis.template.recycler.Song
import com.itis.template.service.MusicPlayerService
import kotlinx.android.synthetic.main.activity_player.*


class PlayerActivity : AppCompatActivity(), ServiceConnection, Callback {

    var mps: MusicPlayerService? = null
    private val songs: List<Song> = Data.songs
    private var current: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        btn_pre.setOnClickListener {
            current--
            if (current == -1) current = songs.size - 1
            mps?.pre()
        }
        btn_next.setOnClickListener {
            current++
            if (current == songs.size) current = 0
            mps?.next()
        }
        btn_play.setOnClickListener {
            mps?.play()
        }
        updateUI()
        val intent = Intent(this, MusicPlayerService::class.java)
        bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        mps?.stop()
        mps = null
        super.onDestroy()
    }

    override fun onServiceConnected(name: ComponentName, service: IBinder) {
        val binder = service as MusicPlayerService.MBinder
        mps = binder.getService()
        mps?.init(this, current)
        Log.d("tag", "onServiceCONNECTION")
    }

    override fun onServiceDisconnected(name: ComponentName) {
        unbindService(this)
    }

    override fun songClick(song: Int) {
        current = song
        updateUI()
    }

    private fun updateUI() {
        tv_name.text = songs[current].name
        tv_singer.text = songs[current].author
        iv_cover.setImageResource(songs[current].cover)
    }
}
