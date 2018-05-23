package com.masaibar.exoplayerrotatedemo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    companion object {
        private const val VIDEO_PATH = "assets:///sakura.mp4"

        fun start(context: Context) {
            Intent(context, PlayerActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }.let { context.startActivity(it) }
        }
    }

    private lateinit var player: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        player = SimpleExoPlayerFactory.create(
                this,
                Uri.parse(VIDEO_PATH)
        )
        player_view.player = player
        player.playWhenReady = true
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
