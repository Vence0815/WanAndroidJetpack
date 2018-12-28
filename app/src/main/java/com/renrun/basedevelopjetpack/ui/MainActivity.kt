package com.renrun.basedevelopjetpack.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.ext.logI

/**
 * 单Activity应用实践
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logI("MainActivity---------onCreate")
    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.navHostFragment).navigateUp()

    override fun onResume() {
        super.onResume()
        logI(this.localClassName+"onResume")
    }

    override fun onPause() {
        super.onPause()
        logI(this.localClassName+"onPause")
    }

    override fun onRestart() {
        super.onRestart()
        logI(this.localClassName+"onRestart")
    }

    override fun onStart() {
        super.onStart()
        logI(this.localClassName+"onStart")
    }

    override fun onStop() {
        super.onStop()
        logI(this.localClassName+"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logI(this.localClassName+"onDestroy")
    }
}
