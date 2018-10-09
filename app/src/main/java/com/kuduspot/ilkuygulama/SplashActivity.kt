package com.kuduspot.ilkuygulama

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_splash.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.bumptech.glide.Glide


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Splash Resim Döndürme
        Glide.with(this)
                .load(R.drawable.bg)
                .into(imgSplash)
        var splashDondur=AnimationUtils.loadAnimation(this,R.anim.splash)
        imgSplash.animation=splashDondur

    }

    override fun onResume() {

        //Yeni activity başlatma onResume bölümünde
        object:CountDownTimer(5000,1000) {
            override fun onFinish() {
                var intent=Intent(this@SplashActivity, MainActivity ::class.java)
                startActivity(intent)
            }
            override fun onTick(millisUntilFinished: Long) {

            }
        }.start()
        super.onResume()
    }
}
