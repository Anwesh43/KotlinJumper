package com.anwesome.ui.kotlinjumper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var gameView:GameView = GameView(this)
        setContentView(gameView)
    }
}
