package com.itis.template.activity

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.itis.template.R
import com.itis.template.recycler.Data
import com.itis.template.recycler.Song
import com.itis.template.service.MusicPlayerService
import kotlinx.android.synthetic.main.activity_player.*


class PlayerActivity : AppCompatActivity(), View.OnClickListener {

    private var mps: MusicPlayerService? = null
    private val songs: ArrayList<Song> = Data.songs
    private var current: Int = 0
    var isBound = false
    private var isPlaying = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val intFilter = IntentFilter(MusicPlayerService.BROADCAST_COMPLETION)
        registerReceiver(newSongReceiver, intFilter)
        val intFilterUI = IntentFilter()
        intFilterUI.addAction(MusicPlayerService.BROADCAST_UI_NEXT)
        intFilterUI.addAction(MusicPlayerService.BROADCAST_UI_PREV)
        intFilterUI.addAction(MusicPlayerService.BROADCAST_UI_PLAY_PAUSE)
        registerReceiver(changeUI, intFilterUI)

        btn_play.setOnClickListener(this)
        btn_next.setOnClickListener(this)
        btn_pre.setOnClickListener(this)

        current = intent?.extras?.getInt(INDEX) ?: -1
        val name = intent?.extras?.getString(NAME)
        val cover = intent?.extras?.getInt(COVER) ?: -1
        val author = intent?.extras?.getString(AUTHOR)
        val album = intent?.extras?.getString(ALBUM)
        val singer = "$author $album"
        tv_name.text = name
        tv_singer.text = singer
        iv_cover.setImageResource(cover)
    }

    private val newSongReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            current++
            if (current == songs.size) current = 0
            updateUI()
            mps?.next()
        }
    }

    private val changeUI: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == MusicPlayerService.BROADCAST_UI_NEXT) {
                current++
                if (current == songs.size) current = 0
            } else if (intent?.action == MusicPlayerService.BROADCAST_UI_PREV) {
                current--
                if (current == -1) current = songs.size - 1
            } else {
                if (isPlaying) {
                    isPlaying = false
                    btn_play.setImageResource(R.drawable.ic_play_arrow_black_24dp)
                    mps?.pause()
                } else {
                    isPlaying = true
                    btn_play.setImageResource(R.drawable.ic_stop_black_24dp)
                    mps?.resume()
                }
            }
            updateUI()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_next -> {
                current++
                if (current == songs.size) current = 0
                mps?.next()
                updateUI()
                mps?.next()
            }
            R.id.btn_pre -> {
                current--
                if (current == -1) current = songs.size - 1
                updateUI()
                mps?.pre()
            }
            R.id.btn_play -> {
                if (isPlaying) {
                    btn_play.setImageResource(R.drawable.ic_play_arrow_black_24dp)
                    isPlaying = false
                    mps?.pause()
                } else {
                    btn_play.setImageResource(R.drawable.ic_stop_black_24dp)
                    isPlaying = true
                    mps?.resume()
                }
            }
        }
    }

    private fun updateUI() {
        val curSong = songs[current]
        val singer = curSong.author + " " + curSong.album
        tv_name.text = curSong.name
        tv_singer.text = singer
        iv_cover.setImageResource(curSong.cover)
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MusicPlayerService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as MusicPlayerService.MBinder
            mps = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            isBound = false
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(connection)
        }
        unregisterReceiver(newSongReceiver)
        unregisterReceiver(changeUI)
    }

    companion object {
        private const val INDEX = "index"
        private const val NAME = "name"
        private const val RES = "res"
        private const val COVER = "cover"
        private const val AUTHOR = "author"
        private const val ALBUM = "album"
    }
}
