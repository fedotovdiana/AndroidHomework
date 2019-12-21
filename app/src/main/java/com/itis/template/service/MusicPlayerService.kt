package com.itis.template.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.media.app.NotificationCompat
import com.itis.template.R
import com.itis.template.activity.PlayerActivity
import com.itis.template.recycler.Data
import com.itis.template.recycler.Song
import androidx.core.app.NotificationCompat as MediaNotificationCompat

class MusicPlayerService : Service() {

    private lateinit var mp: MediaPlayer
    private val binder = MBinder()
    private var current: Int = 0
    private var songs: ArrayList<Song> = Data.songs
    private var curIndex: Int = -1
    lateinit var curSong: Song
    private var curRes = -1

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        val intent = IntentFilter(BROADCAST)
        registerReceiver(newSongReceiver, intent)
        val intentControls = IntentFilter()
        intentControls.addAction(BROADCAST_PAUSE)
        intentControls.addAction(BROADCAST_PLAY)
        intentControls.addAction(BROADCAST_NEXT)
        intentControls.addAction(BROADCAST_PRE)
        registerReceiver(controlNotificationActions, intentControls)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channel"
            val descriptionText = "music_channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private val newSongReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            curIndex = intent?.extras?.getInt(INDEX) ?: -1
            if (curIndex != -1) {
                curSong = songs[curIndex]
                curRes = curSong.res
            } else {
                stopSelf()
            }
            stop()
            start()
        }
    }

    private val controlNotificationActions: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                BROADCAST_NEXT -> {
                    next()
                    sendBroadcast(Intent(BROADCAST_UI_NEXT))
                }
                BROADCAST_PRE -> {
                    pre()
                    sendBroadcast(Intent(BROADCAST_UI_PREV))
                }
                BROADCAST_PLAY -> {
                    play()
                    sendBroadcast(Intent(BROADCAST_UI_PLAY_PAUSE))
                }
                BROADCAST_PAUSE -> {
                    pause()
                    sendBroadcast(Intent(BROADCAST_UI_PLAY_PAUSE))
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            curRes = it.extras?.getInt(RES) ?: -1
            curIndex = it.extras?.getInt(INDEX) ?: -1
            curSong = songs[curIndex]
            start()
        }
        buildNotifications(BROADCAST_PLAY)
        return START_STICKY
    }

    private fun buildNotifications(action: String) {
        val clickIntent = Intent(this, PlayerActivity::class.java).apply {
            putExtra(INDEX, curIndex)
            putExtra(NAME, curSong.name)
            putExtra(RES, curSong.res)
            putExtra(COVER, curSong.cover)
            putExtra(AUTHOR, curSong.author)
            putExtra(ALBUM, curSong.album)
        }
        val clickPendingIntent = PendingIntent.getActivity(this, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val playPausePendingIntent: PendingIntent?
        val icon: Int
        if (action == BROADCAST_PAUSE) {
            icon = R.drawable.ic_play_arrow_black_24dp
            playPausePendingIntent = PendingIntent.getBroadcast(this, 0, Intent(BROADCAST_PLAY), 0)
        } else {
            icon = R.drawable.ic_stop_black_24dp
            playPausePendingIntent = PendingIntent.getBroadcast(this, 0, Intent(BROADCAST_PAUSE), 0)
        }
        val prePendingIntent = PendingIntent.getBroadcast(this, 0, Intent(BROADCAST_PRE), 0)
        val nextPendingIntent = PendingIntent.getBroadcast(this, 0, Intent(BROADCAST_NEXT), 0)
        val builder = MediaNotificationCompat.Builder(this, CHANNEL_ID).apply {
            setSmallIcon(R.drawable.ic_music_note_black_24dp)
            setContentTitle(curSong.name)
            setContentText(curSong.author)
            setLargeIcon(BitmapFactory.decodeResource(resources, curSong.cover))
            priority = MediaNotificationCompat.PRIORITY_DEFAULT
            setContentIntent(clickPendingIntent)
            addAction(R.drawable.ic_skip_previous_black_24dp, "pre", prePendingIntent)
            addAction(icon, "play", playPausePendingIntent)
            addAction(R.drawable.ic_skip_next_black_24dp, "next", nextPendingIntent)
            setStyle(NotificationCompat.MediaStyle().setShowActionsInCompactView(1)
            )
        }
        startForeground(NOTIFICATION, builder.build())
    }

    override fun onDestroy() {
        super.onDestroy()
        stop()
        unregisterReceiver(newSongReceiver)
        unregisterReceiver(controlNotificationActions)
    }

    private fun start() {
        mp = MediaPlayer.create(this, curRes)
        mp.setOnCompletionListener {
            val intent = Intent(BROADCAST_COMPLETION)
            sendBroadcast(intent)
        }
        play()
    }

    fun play() {
        if (!mp.isPlaying) {
            mp.start()
        }
        buildNotifications(BROADCAST_PLAY)
    }

    fun pause() {
        if (mp.isPlaying) {
            current = mp.currentPosition
            mp.pause()
        }
        buildNotifications(BROADCAST_PAUSE)
    }

    fun resume() {
        if (!mp.isPlaying) {
            mp.seekTo(current)
            mp.start()
        }
        buildNotifications(BROADCAST_PLAY)
    }

    fun stop() {
        if (mp.isPlaying) {
            mp.stop()
        }
        mp.release()
    }

    fun next() {
        curIndex++
        if (curIndex == songs.size) curIndex = 0
        curSong = songs[curIndex]
        curRes = curSong.res
        stop()
        start()
    }

    fun pre() {
        current--
        if (current == -1) current = songs.size - 1
        curSong = songs[curIndex]
        curRes = curSong.res
        stop()
        start()
    }

    inner class MBinder : Binder() {
        fun getService() = this@MusicPlayerService
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    companion object {
        const val NOTIFICATION = 11
        const val CHANNEL_ID = "channel"

        const val BROADCAST_PLAY = "com.itis.template.service_play"
        const val BROADCAST_PAUSE = "com.itis.template.service_pause"
        const val BROADCAST_PRE = "com.itis.template.service_prev"
        const val BROADCAST_NEXT = "com.itis.template.service_next"

        const val BROADCAST = "com.itis.template.broadcast_music"
        const val BROADCAST_COMPLETION = "com.itis.template.service.broadcast_completion"
        const val BROADCAST_UI_NEXT = "com.itis.template.service.broadcast_ui_next"
        const val BROADCAST_UI_PREV = "com.itis.template.service.broadcast_ui_prev"
        const val BROADCAST_UI_PLAY_PAUSE = "com.itis.template.service.broadcast_ui_playpause"

        private const val INDEX = "index"
        private const val NAME = "name"
        private const val RES = "res"
        private const val COVER = "cover"
        private const val AUTHOR = "author"
        private const val ALBUM = "album"
    }
}
