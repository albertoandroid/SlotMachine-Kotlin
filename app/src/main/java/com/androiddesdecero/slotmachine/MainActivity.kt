package com.androiddesdecero.slotmachine

import android.graphics.drawable.AnimationDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var random = Random()
    var slot1: Int = 0
    var slot2: Int = 0
    var slot3: Int = 0
    var ganancias: Int = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityBtJugar.setOnClickListener({

            mainActivityTvGanancias.text = ganancias.toString()

            var toast = Toast.makeText(this, "Has lanzado, descontado 1000 euros", Toast.LENGTH_SHORT)
            toast.show()

            mainActivitySlot1.setImageResource(R.drawable.animation)
            val slot1Anin: AnimationDrawable = mainActivitySlot1.drawable as AnimationDrawable
            slot1Anin.start()

            mainActivitySlot2.setImageResource(R.drawable.animation)
            val slot2Anin: AnimationDrawable = mainActivitySlot2.drawable as AnimationDrawable
            slot2Anin.start()

            mainActivitySlot3.setImageResource(R.drawable.animation)
            val slot3Anin: AnimationDrawable = mainActivitySlot3.drawable as AnimationDrawable
            slot3Anin.start()

            var handler = Handler()
            handler.postDelayed({
                slot1Anin.stop()
                slot2Anin.stop()
                slot3Anin.stop()
                toast.cancel()

                setImagenes()
                obtenerPutuacion()

            }, 1000)

        })
    }

    private fun setImagenes(){
        slot1 = random.nextInt(5)
        slot2 = random.nextInt(5)
        slot3 = random.nextInt(5)


        when(slot1){
            0 -> mainActivitySlot1.setImageResource(R.drawable.ic_1)
            1 -> mainActivitySlot1.setImageResource(R.drawable.ic_2)
            2 -> mainActivitySlot1.setImageResource(R.drawable.ic_3)
            3 -> mainActivitySlot1.setImageResource(R.drawable.ic_4)
            4 -> mainActivitySlot1.setImageResource(R.drawable.ic_5)
        }

        when(slot2){
            0 -> mainActivitySlot2.setImageResource(R.drawable.ic_1)
            1 -> mainActivitySlot2.setImageResource(R.drawable.ic_2)
            2 -> mainActivitySlot2.setImageResource(R.drawable.ic_3)
            3 -> mainActivitySlot2.setImageResource(R.drawable.ic_4)
            4 -> mainActivitySlot2.setImageResource(R.drawable.ic_5)
        }

        when(slot3){
            0 -> mainActivitySlot3.setImageResource(R.drawable.ic_1)
            1 -> mainActivitySlot3.setImageResource(R.drawable.ic_2)
            2 -> mainActivitySlot3.setImageResource(R.drawable.ic_3)
            3 -> mainActivitySlot3.setImageResource(R.drawable.ic_4)
            4 -> mainActivitySlot3.setImageResource(R.drawable.ic_5)
        }
    }

    private fun obtenerPutuacion(){
        if((slot1 == slot2)&&(slot2 == slot3)){
            var snackBar = Snackbar.make(mainActivityRl, "Has ganado 100000", Snackbar.LENGTH_SHORT)
            var snackBarView = snackBar.view
            snackBarView.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            snackBar.show()
            ganancias = ganancias + 100000
        }else if((slot1 == slot2)||(slot2 == slot3)||(slot1 == slot3)){
            var snackBar = Snackbar.make(mainActivityRl, "Has ganado 5000", Snackbar.LENGTH_SHORT)
            var snackBarView = snackBar.view
            snackBarView.setBackgroundColor(resources.getColor(R.color.colorAccent))
            snackBar.show()
            ganancias = ganancias + 5000
        }

        ganancias = ganancias - 1000
        mainActivityTvGanancias.text = ganancias.toString()
    }
}
