package com.itis.template.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.itis.template.R
import com.itis.template.recycler.ClickCallback
import com.itis.template.recycler.Data
import com.itis.template.recycler.Song
import com.itis.template.recycler.SongAdapter
import com.itis.template.service.MusicPlayerService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ClickCallback {

    var mps: MusicPlayerService? = null
    var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SongAdapter(Data.songs) {
            songClick(it)
        }
        rv_songs.adapter = adapter
    }

    override fun songClick(song: Song) {
        val index = Data.songs.indexOf(song)
        if (isBound) {
            val intent = Intent(BROADCAST)
            intent.putExtra(INDEX, index)
            sendBroadcast(intent)
        } else {
            val intent = Intent(this, MusicPlayerService::class.java)
            intent.putExtra(INDEX, index)
            intent.putExtra(RES, song.res)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            } else {
                startService(intent)
            }
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        startActivity((Intent(this, PlayerActivity::class.java).apply {
            putExtra(INDEX, index)
            putExtra(NAME, song.name)
            putExtra(RES, song.res)
            putExtra(COVER, song.cover)
            putExtra(AUTHOR, song.author)
            putExtra(ALBUM, song.album)
        }))
    }

    private val connection: ServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as MusicPlayerService.MBinder
            mps = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(connection)
        }
    }

    companion object {
        const val BROADCAST = "com.itis.template.broadcast_music"
        private const val INDEX = "index"
        private const val NAME = "name"
        private const val RES = "res"
        private const val COVER = "cover"
        private const val AUTHOR = "author"
        private const val ALBUM = "album"
    }
}
