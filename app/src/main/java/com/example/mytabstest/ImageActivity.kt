package com.example.mytabstest

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.squareup.picasso.Picasso

open class ImageActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        imageView = findViewById(R.id.image)
        var url = intent.getStringExtra("url")
        Picasso.get().load(url).into(imageView)

    }

}