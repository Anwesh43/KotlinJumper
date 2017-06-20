package com.anwesome.ui.kotlinjumper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

/**
 * Created by anweshmishra on 20/06/17.
 */
class GameView(context:Context):View(context) {
    private val paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG);
    private var time:Int = 0
    private var w:Int = 0
    private var h:Int = 0
    private var ball:Ball = Ball(0.0f)
    override fun onDraw(canvas:Canvas) {
        if(time == 0) {
            w = canvas.width
            h = canvas.height
            ball = Ball((2*h/3)*1.0f)
        }
        canvas.drawColor(Color.BLACK)
        ball.draw(canvas,paint,w*1.0f)
        time++
    }
    data class Ball(var y:Float,var rot:Float=0.0f,var dir:Float=0.0f) {
        fun draw(canvas:Canvas,paint:Paint,w:Float) {
            canvas.save()
            canvas.translate(w/2,y)
            canvas.rotate(rot)
            paint.setColor(Color.GREEN)
            canvas.drawArc(RectF(-w/5,-w/5,w/5,w/5),0.0f,180.0f,true,paint)
            paint.setColor(Color.RED)
            canvas.drawArc(RectF(-w/5,-w/5,w/5,w/5),180.0f,180.0f,true,paint)
            canvas.restore()
        }
        fun jump(){
            y+=dir*10
            rot+=dir*10;
            if(rot == -180.0f) {
                dir *= -1
            }
            if(rot == 0.0f) {
                dir = 0.0f
            }
        }
        fun startJumping() {
            if(dir == 0.0f) {
                dir = -1.0f
            }
        }
    }
}

