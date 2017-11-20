package io.plan8.backoffice.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import io.intercom.android.sdk.Intercom
import io.plan8.backoffice.BR
import io.plan8.backoffice.R
import io.plan8.backoffice.databinding.ActivityMainBinding
import io.plan8.backoffice.fragment.BaseFragment
import io.plan8.backoffice.fragment.MoreFragment
import io.plan8.backoffice.fragment.TaskFragment
import io.plan8.backoffice.util.ViewUtil
import io.plan8.backoffice.vm.MainActivityVM
import kotlinx.android.synthetic.main.activity_main.view.*
import java.io.ByteArrayOutputStream
import io.intercom.android.sdk.identity.Registration



class MainActivity : BaseActivity() {
    var binding: ActivityMainBinding? = null
    var vm: MainActivityVM? = null
    var fragmentManager: FragmentManager? = supportFragmentManager
    var currentTabPosition: Int = 0
    var fragments: MutableList<BaseFragment>? = ArrayList()

    companion object {
        fun buildIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        vm = MainActivityVM(this, savedInstanceState)
        binding!!.setVariable(BR.vm, vm)
        binding!!.executePendingBindings()

        initTabAndViewPager()
    }

    override fun onDestroy() {
        binding!!.unbind()
        super.onDestroy()
    }

    fun initTabAndViewPager() {
        for (i in 0..1) {
            val tab = binding!!.bottomNaviContainer.mainTabLayout.newTab()
            binding!!.mainTabLayout.setSelectedTabIndicatorHeight(0)
            tab.setCustomView(R.layout.item_main_tab)
            if (null != tab.customView) {
                Log.e("test", "testtttt " + i)
                val tabItemIcon: AppCompatImageView = tab.customView!!.findViewById<AppCompatImageView>(R.id.mainTabItemIcon)
                val tabItemTitle: AppCompatTextView = tab.customView!!.findViewById<AppCompatTextView>(R.id.mainTabItemTitle)
                if (i == 0) {
                    tabItemIcon.setImageResource(R.drawable.ic_line_calendar)
                    tabItemIcon.setColorFilter(ContextCompat.getColor(applicationContext, R.color.selectTabItem))

                    tabItemTitle.setTextColor(ContextCompat.getColor(applicationContext, R.color.selectTabItem))
                    tabItemTitle.text = "예약"

                    val taskFragment = TaskFragment()
                    val bundle = Bundle()
//        bundle.putSerializable("dynamicUiConfiguration", dynamicUiConfigurations.get(i))
                    taskFragment.arguments = bundle
                    fragments!!.add(taskFragment)
                } else {
                    tabItemIcon.setImageResource(R.drawable.ic_solid_more)
                    tabItemTitle.text = "더보기"

                    val moreFragment = MoreFragment()
                    val bundle = Bundle()
//        bundle.putSerializable("dynamicUiConfiguration", dynamicUiConfigurations.get(i))
                    moreFragment.arguments = bundle
                    fragments!!.add(moreFragment)
                }
                //                        tab.getCustomView().setLayoutParams(params);

                binding!!.bottomNaviContainer.mainTabLayout.addTab(tab)
            }
        }

        binding!!.mainViewPager.offscreenPageLimit = fragments!!.size

        val pagerAdapter = object : FragmentStatePagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment {
                return fragments!!.get(position)
            }

            override fun getCount(): Int {
                return fragments!!.size
            }
        }
        binding!!.mainViewPager.adapter = pagerAdapter
        binding!!.mainViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding!!.bottomNaviContainer.mainTabLayout))
        binding!!.bottomNaviContainer.mainTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                currentTabPosition = tab.position
                binding!!.mainViewPager.currentItem = tab.position
                if (null != tab.customView) {
                    tab.customView!!.findViewById<AppCompatImageView>(R.id.mainTabItemIcon).setColorFilter(ContextCompat.getColor(applicationContext, R.color.selectTabItem))
                    tab.customView!!.findViewById<AppCompatTextView>(R.id.mainTabItemTitle).setTextColor(ContextCompat.getColor(applicationContext, R.color.selectTabItem))
                }
//                moveTop()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (null != tab) {
                    if (null != tab.customView) {
                        tab.customView!!.findViewById<AppCompatImageView>(R.id.mainTabItemIcon).setColorFilter(ContextCompat.getColor(applicationContext, R.color.unselectTabItem))
                        tab.customView!!.findViewById<AppCompatTextView>(R.id.mainTabItemTitle).setTextColor(ContextCompat.getColor(applicationContext, R.color.unselectTabItem))
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            RESULT_OK -> {
                if (data!!.action == null) {
                    if (null != fragments) {
                        MoreFragment.uploadImage(data.data, this)
                    }
                } else {
                    if (null != fragments) {
                        MoreFragment.uploadImage(getImageUri(applicationContext, data.extras.get("data") as Bitmap), this)
                        Log.e("test", "test")
                    }
                }
            }
        }
    }

    fun getImageUri(context: Context, bitmap: Bitmap): Uri{
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path)
    }
}