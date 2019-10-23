package com.itis.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        if (intent?.action == Intent.ACTION_SEND && "text/plain" == intent.type) {
            intent?.getStringExtra(Intent.EXTRA_TEXT)?.let {
                tv_data.text = it
            }
        }
    }
}
