package com.example.ismayil.ortalamahesabla

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        imageView.animation = AnimationUtils.loadAnimation(this,R.anim.gelenlogo)
        button.animation = AnimationUtils.loadAnimation(this,R.anim.gelenbutton)


        button.setOnClickListener {
            imageView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.gedenlogo))
            button.startAnimation(AnimationUtils.loadAnimation(this,R.anim.gedenbutton))

            Handler().postDelayed({
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            },1200.toLong())

        }
    }
}
