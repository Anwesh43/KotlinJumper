package com.anwesome.ui.kotlinjumper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent
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
    private var animationHandler:AnimationHandler = AnimationHandler(this,ball)
    override fun onDraw(canvas:Canvas) {
        if(time == 0) {
            w = canvas.width
            h = canvas.height
            ball = Ball((2*h/3)*1.0f)
            animationHandler = AnimationHandler(this,ball)
        }
        canvas.drawColor(Color.BLACK)
        ball.draw(canvas,paint,w*1.0f)
        time++
        animationHandler.animate()
    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                animationHandler.startAnimating()
            }
        }
        return true
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
            y+=dir*20
            rot+=dir*20;
            dir = when(rot) {
                -180.0f -> 1.0f
                0.0f -> 0.0f
                else -> dir
            }
        }
        fun startJumping() {
            if(dir == 0.0f) {
                dir = -1.0f
            }
        }
    }
}
class AnimationHandler(var view:GameView,var ball:GameView.Ball) {
    private var isAnimated:Boolean = false
    fun animate() {
        when(isAnimated) {
             true -> {
                    ball.jump()
                    when(ball.dir) {
                        0.0f->{
                            isAnimated = false
                        }
                    }
                    try {
                        Thread.sleep(50);
                        view.invalidate()
                    }
                    catch (ex:Exception) {

                    }
             }
        }
    }
    fun startAnimating() {
        when(isAnimated) {
            false -> {
                isAnimated = true
                ball.startJumping()
                view.postInvalidate()
            }
        }
    }
}
