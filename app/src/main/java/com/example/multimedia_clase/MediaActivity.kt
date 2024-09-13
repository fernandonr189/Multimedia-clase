package com.example.multimedia_clase

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Audio
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException

class MediaActivity : AppCompatActivity() {

    private lateinit var playButton: Button
    private lateinit var stopButton: Button
    private var mediaPlayer: MediaPlayer? = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_media)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        playButton = findViewById(R.id.play_button)
        stopButton = findViewById(R.id.stop_button)

        playButton.setOnClickListener {
            
            if(mediaPlayer == null) {
                mediaPlayer = MediaPlayer()
            }
            
            mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
            val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.chicnstu)
            try {
                mediaPlayer!!.setDataSource(this, uri)
                mediaPlayer!!.prepare();
                mediaPlayer!!.start()
                Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show()
            }
            catch (e: IOException) {
                Toast.makeText(this, "Error al reproducir", Toast.LENGTH_SHORT).show()
            }
        }
        
        stopButton.setOnClickListener { 
            if(mediaPlayer != null && mediaPlayer!!.isPlaying) {
                mediaPlayer!!.stop()
                mediaPlayer = null
            }
            Toast.makeText(this, "Deteniendo", Toast.LENGTH_SHORT).show()
        }
    }
}