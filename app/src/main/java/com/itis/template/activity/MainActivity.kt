package com.itis.template.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itis.template.R
import com.itis.template.recycler.Callback
import com.itis.template.recycler.Data
import com.itis.template.recycler.SongAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = SongAdapter(Data.songs) {
            songClick(it.res)
        }
        rv_songs.adapter = adapter
    }

    override fun songClick(song: Int) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra("song", song)
        startActivity(intent)
    }
}
