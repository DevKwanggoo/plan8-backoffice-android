package io.plan8.backoffice.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.ActivityMainBinding;
import io.plan8.backoffice.fragment.MoreFragment;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private MainActivityVM vm;
    private FragmentManager fragmentManager;
    private int currentTabPosition = 0;
    private List<Fragment> fragments = new ArrayList<>();

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vm = MainActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        if (Constants.getMe().getTeam() != null && Constants.getMe().getTeam().size > 0) {
            vm.emptyTeamFlag = false;
        }

        initTabAndViewPager();
    }

    private void initTabAndViewPager() {
        for (int i = 0; i < 2; i++) {
            TabLayout.Tab tab = binding.mainTabLayout.newTab();
            binding.mainTabLayout.setSelectedTabIndicatorHeight(0);
            tab.setCustomView(R.layout.item_main_tab);

            if (null != tab.getCustomView()) {
                Log.e("test", "testtttt " + i);
                AppCompatImageView tabItemIcon = tab.getCustomView().findViewById(R.id.mainTabItemIcon);
                AppCompatTextView tabItemTitle = tab.getCustomView().findViewById(R.id.mainTabItemTitle);
                if (i == 0) {
                    tabItemIcon.setImageResource(R.drawable.ic_line_calendar);
                    tabItemIcon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem))

                    tabItemTitle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem))
                    tabItemTitle.setText("예약");

                    TaskFragment taskFragment = new TaskFragment();
                    Bundle bundle = new Bundle();
//        bundle.putSerializable("dynamicUiConfiguration", dynamicUiConfigurations.get(i))
                    taskFragment.arguments = bundle;
                    fragments.add(taskFragment);
                } else {
                    tabItemIcon.setImageResource(R.drawable.ic_solid_more)
                    tabItemTitle.setText("더보기");

                    MoreFragment moreFragment = new MoreFragment();
                    Bundle bundle = new Bundle();
//        bundle.putSerializable("dynamicUiConfiguration", dynamicUiConfigurations.get(i))
                    moreFragment.arguments = bundle;
                    fragments.add(moreFragment);
                }
                //                        tab.getCustomView().setLayoutParams(params);

                binding.mainTabLayout.addTab(tab);
            }
        }

        binding.mainViewPager.setOffscreenPageLimit(fragments.size());

        FragmentStatePagerAdapter pagerAdapter = new FragmentStatePagerAdapter() {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        binding.mainViewPager.setAdapter(pagerAdapter);
        binding.mainViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.mainTabLayout));
        binding.mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTabPosition = tab.getPosition();
                binding.mainViewPager.setCurrentItem(tab.getPosition());
                if (tab.getCustomView() != null){
                    tab.getCustomView().findViewById(R.id.mainTabItemIcon).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem));
                    tab.getCustomView().findViewById(R.id.mainTabTitle).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab != null){
                    tab.getCustomView().findViewById(R.id.mainTabItemIcon).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.unselectTabItem));
                    tab.getCustomView().findViewById(R.id.mainTabTitle).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.unselectTabItem));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (data.getAction() == null) {
                    if (null != fragments && null != fragments.get(1) && fragments.get(1) instanceof MoreMenuFragment) {
                        ((MoreMenuFragment) fragments.get(1)).uploadImage(data.getData());
                    }
                } else {
                    if (null != fragments && null != fragments.get(1) && fragments.get(1) instanceof MoreMenuFragment) {
                        ((MoreMenuFragment) fragments.get(1)).uploadImage(getImageUri(getApplicationContext(),(Bitmap) data.getExtras().get("data")));
                    }
                }
            default:
                break;
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    @Override
    protected void onDestroy() {
        binding.unbind();
        super.onDestroy();
    }
}