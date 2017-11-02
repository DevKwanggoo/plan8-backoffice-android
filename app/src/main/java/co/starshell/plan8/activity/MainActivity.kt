package co.starshell.plan8.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.starshell.plan8.BR
import co.starshell.plan8.R
import co.starshell.plan8.databinding.ActivityMainBinding
import co.starshell.plan8.vm.MainActivityVM

class MainActivity : BaseActivity() {
    var binding: ActivityMainBinding? = null
    var vm: MainActivityVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        vm = MainActivityVM(this, savedInstanceState)
        binding!!.setVariable(BR.vm, vm)
        binding!!.executePendingBindings()
    }
}
