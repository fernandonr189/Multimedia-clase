package com.example.multimedia_clase

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VideoActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var mediaController: MediaController;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_video)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        videoView = findViewById(R.id.videoView)
        mediaController = MediaController(this)

        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.chicnstuvideo)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()
        Toast.makeText(this, "Reproduciendo video", Toast.LENGTH_SHORT).show()
        videoView.setMediaController(mediaController)
    }
}