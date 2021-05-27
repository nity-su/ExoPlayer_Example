package com.anlyn.exoplayer_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.ui.PlayerView

class MainActivity : AppCompatActivity() {
    private lateinit var videoView: PlayerView
    private lateinit var exoPlayer:ExoPlayerAttr;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView=findViewById<PlayerView>(R.id.player)

        exoPlayer = ExoPlayerAttr(this).also {
            videoView.player = it.exoPlayer
        }

    }

    override fun onDestroy() {
        exoPlayer.releasePlayer()
        super.onDestroy()
    }
}