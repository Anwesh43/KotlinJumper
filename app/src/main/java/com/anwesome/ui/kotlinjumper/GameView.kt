package com.anwesome.ui.kotlinjumper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

/**
 * Created by anweshmishra on 20/06/17.
 */
class GameView(context:Context):View(context) {
    private val paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG);
    override fun onDraw(canvas:Canvas) {
        canvas.drawColor(Color.BLACK);
    }
}
