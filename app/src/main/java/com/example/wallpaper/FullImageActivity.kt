package com.example.wallpaper

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class FullImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)

        val imageView: ImageView = findViewById(R.id.fullImageView)
        val imageResId = intent.getIntExtra("IMAGE_RES_ID", 0)

        imageView.setImageResource(imageResId)
    }
}