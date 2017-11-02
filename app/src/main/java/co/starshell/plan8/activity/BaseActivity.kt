package co.starshell.plan8.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

/**
 * Created by SSozi on 2017. 11. 2..
 */
abstract class BaseActivity : AppCompatActivity() {
    var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Log.d("lifeCycle", "onCreate :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifeCycle", "onStart :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifeCycle", "onResume :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifeCycle", "onPause :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifeCycle", "onStop :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifeCycle", "onDestroy :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("lifeCycle", "onNewIntent :: " + javaClass.simpleName + "  ::  " + hashCode())
    }

    fun onBackPressed(canDoubleClickFinish: Boolean) {
        if (canDoubleClickFinish) {
            val tempTime = System.currentTimeMillis()
            val intervalTime = tempTime - backPressedTime

            if (intervalTime in 0..2000) {
                finish()
            } else {
                backPressedTime = tempTime
                val toast = Toast.makeText(baseContext, "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}