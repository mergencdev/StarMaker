package com.mergenc.starmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mergenc.starmaker.databinding.ActivityMainBinding
import com.mergenc.starmakerframework.StarMakerFramework
import com.mergenc.starmakerframework.data.misc.Size

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val starMakerFramework = StarMakerFramework(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.wvStarMaker.loadUrl("https://img.etimg.com/thumb/msid-72948091,width-650,imgsize-95069,,resizemode-4,quality-100/star_istock.jpg")

        binding.mcvSmallStar.setOnClickListener {
            // Call the method to add a small star from your framework
            starMakerFramework.createStar(Size.S)
        }

        binding.mcvBigStar.setOnClickListener {
            // Call the method to add a big star from your framework
            starMakerFramework.createStar(Size.B)
        }

        binding.mcvReset.setOnClickListener {
            // Call the method to reset the stars from your framework
            starMakerFramework.resetStars()
        }
    }
}