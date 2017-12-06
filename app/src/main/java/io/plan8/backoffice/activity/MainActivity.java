package io.plan8.backoffice.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.databinding.ActivityMainBinding;
import io.plan8.backoffice.fragment.MoreFragment;
import io.plan8.backoffice.fragment.NotificationFragment;
import io.plan8.backoffice.fragment.ReservationFragment;
import io.plan8.backoffice.model.api.Team;
import io.plan8.backoffice.vm.MainActivityVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        vm = new MainActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        Call<List<Team>> getTeams = RestfulAdapter.getInstance().getServiceApi().getTeams("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getApplicationContext()));
        getTeams.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                if (response.body() != null) {
                    List<Team> teams = response.body();
                    ApplicationManager.getInstance().setTeams(teams);
                    if (null == teams || teams.size()==0) {
                        vm.setEmptyTeamFlag(true);
                    } else {
                        vm.setEmptyTeamFlag(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Log.e("api : ", "failure");
            }
        });

        initTabAndViewPager();
    }

    private void initTabAndViewPager() {
        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = binding.mainTabLayout.newTab();
            binding.mainTabLayout.setSelectedTabIndicatorHeight(0);
            tab.setCustomView(R.layout.item_main_tab);

            if (null != tab.getCustomView()) {
                AppCompatImageView tabItemIcon = tab.getCustomView().findViewById(R.id.mainTabItemIcon);
                AppCompatTextView tabItemTitle = tab.getCustomView().findViewById(R.id.mainTabItemTitle);
                if (i == 0) {
                    tabItemIcon.setImageResource(R.drawable.ic_line_calendar);
                    tabItemIcon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem));

                    tabItemTitle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem));
                    tabItemTitle.setText("예약");

                    ReservationFragment reservationFragment = new ReservationFragment();
                    Bundle bundle = new Bundle();
//        bundle.putSerializable("dynamicUiConfiguration", dynamicUiConfigurations.get(i))
                    reservationFragment.setArguments(bundle);
                    fragments.add(reservationFragment);
                } else if (i == 1) {
                    tabItemIcon.setImageResource(R.drawable.ic_line_alarm);
                    tabItemTitle.setText("알림");

                    NotificationFragment notiFragment = new NotificationFragment();
                    Bundle bundle = new Bundle();
//        bundle.putSerializable("dynamicUiConfiguration", dynamicUiConfigurations.get(i))
                    notiFragment.setArguments(bundle);
                    fragments.add(notiFragment);
                } else {
                    tabItemIcon.setImageResource(R.drawable.ic_solid_more);
                    tabItemTitle.setText("더보기");

                    MoreFragment moreFragment = new MoreFragment();
                    Bundle bundle = new Bundle();
//        bundle.putSerializable("dynamicUiConfiguration", dynamicUiConfigurations.get(i))
                    moreFragment.setArguments(bundle);
                    fragments.add(moreFragment);
                }
                //                        tab.getCustomView().setLayoutParams(params);

                binding.mainTabLayout.addTab(tab);
            }
        }

        binding.mainViewPager.setOffscreenPageLimit(fragments.size());

        fragmentManager = getSupportFragmentManager();
        FragmentStatePagerAdapter pagerAdapter = new FragmentStatePagerAdapter(fragmentManager) {
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
                if (tab.getCustomView() != null) {
                    ((AppCompatImageView)tab.getCustomView().findViewById(R.id.mainTabItemIcon)).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem));
                    ((AppCompatTextView)tab.getCustomView().findViewById(R.id.mainTabItemTitle)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab != null) {
                    ((AppCompatImageView)tab.getCustomView().findViewById(R.id.mainTabItemIcon)).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.unselectTabItem));
                    ((AppCompatTextView)tab.getCustomView().findViewById(R.id.mainTabItemTitle)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.unselectTabItem));
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
                    if (null != fragments && null != fragments.get(1) && fragments.get(1) instanceof MoreFragment) {
                        ((MoreFragment) fragments.get(1)).uploadImage(data.getData());
                    }
                } else {
                    if (null != fragments && null != fragments.get(1) && fragments.get(1) instanceof MoreFragment) {
                        ((MoreFragment) fragments.get(1)).uploadImage(getImageUri(getApplicationContext(), (Bitmap) data.getExtras().get("data")));
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

    public void setEmptyFlag(boolean flag){
        vm.setEmptyTeamFlag(flag);
    }
}