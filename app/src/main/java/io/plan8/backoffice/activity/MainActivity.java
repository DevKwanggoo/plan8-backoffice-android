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
import android.view.MotionEvent;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.badgeview.BGABadgeImageView;
import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.BR;
import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.databinding.ActivityMainBinding;
import io.plan8.backoffice.fragment.MoreFragment;
import io.plan8.backoffice.fragment.NotificationFragment;
import io.plan8.backoffice.fragment.ReservationFragment;
import io.plan8.backoffice.model.api.Member;
import io.plan8.backoffice.model.api.Reservation;
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
    private List<Member> members;
    private ReservationFragment reservationFragment;
    private NotificationFragment notificationFragment;
    private BGABadgeImageView badgeTabItemIcon;

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vm = new MainActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        Call<List<Member>> getUserMembersCall = RestfulAdapter.getInstance().getServiceApi().getUserMembers("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getApplicationContext()), ApplicationManager.getInstance().getUser().getId());
        getUserMembersCall.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                members = response.body();
                ApplicationManager.getInstance().setMembers(members);
                if (null == members || members.size() == 0) {
                    vm.setEmptyTeamFlag(true);
                } else {
                    initTabAndViewPager();
                    vm.setEmptyTeamFlag(false);
                }
            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
            }
        });
    }

    private void initTabAndViewPager() {
        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = binding.mainTabLayout.newTab();
            if (i == 1) {
                tab.setCustomView(R.layout.item_badge_tab);
            } else {
                tab.setCustomView(R.layout.item_main_tab);
            }
            binding.mainTabLayout.setSelectedTabIndicatorHeight(0);

            if (null != tab.getCustomView()) {
                AppCompatTextView tabItemTitle = tab.getCustomView().findViewById(R.id.mainTabItemTitle);
                badgeTabItemIcon = tab.getCustomView().findViewById(R.id.mainTabBadgeItemImageView);
                AppCompatImageView tabItemIcon = tab.getCustomView().findViewById(R.id.mainTabItemIcon);

                if (i == 0) {
                    tabItemIcon.setImageResource(R.drawable.ic_line_calendar);
                    tabItemIcon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem));
                    tabItemTitle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem));
                    tabItemTitle.setText("예약");

                    reservationFragment = new ReservationFragment();
                    Bundle bundle = new Bundle();
                    reservationFragment.setArguments(bundle);
                    fragments.add(reservationFragment);
                } else if (i == 1) {
                    badgeTabItemIcon.setImageResource(R.drawable.ic_line_alarm);
                    badgeTabItemIcon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.unselectTabItem));
                    refreshNotificationBadgeCount();
                    tabItemTitle.setText("알림");
                    tabItemTitle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.unselectTabItem));

                    notificationFragment = new NotificationFragment();
                    Bundle bundle = new Bundle();
                    notificationFragment.setArguments(bundle);
                    fragments.add(notificationFragment);
                } else {
                    tabItemIcon.setImageResource(R.drawable.ic_solid_more);
                    tabItemIcon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.unselectTabItem));
                    tabItemTitle.setText("더보기");
                    tabItemTitle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.unselectTabItem));

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
                    if (null != tab.getCustomView().findViewById(R.id.mainTabItemIcon)) {
                        ((AppCompatImageView) tab.getCustomView().findViewById(R.id.mainTabItemIcon)).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem));
                    }
                    if (null != tab.getCustomView().findViewById(R.id.mainTabBadgeItemImageView)) {
                        ((BGABadgeImageView) tab.getCustomView().findViewById(R.id.mainTabBadgeItemImageView)).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem));
                    }
                    ((AppCompatTextView) tab.getCustomView().findViewById(R.id.mainTabItemTitle)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectTabItem));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab != null) {
                    if (tab.getCustomView() != null) {
                        if (null != tab.getCustomView().findViewById(R.id.mainTabItemIcon)) {
                            ((AppCompatImageView) tab.getCustomView().findViewById(R.id.mainTabItemIcon)).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.unselectTabItem));
                        }
                        if (null != tab.getCustomView().findViewById(R.id.mainTabBadgeItemImageView)) {
                            ((BGABadgeImageView) tab.getCustomView().findViewById(R.id.mainTabBadgeItemImageView)).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.unselectTabItem));
                        }
                        ((AppCompatTextView) tab.getCustomView().findViewById(R.id.mainTabItemTitle)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.unselectTabItem));
                    }
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.mainViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                reservationFragment.setSwipeFlag(false);
                notificationFragment.setSwipeFlag(false);
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        reservationFragment.setSwipeFlag(true);
                        notificationFragment.setSwipeFlag(true);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (data.getAction() == null) {
                    if (null != fragments && null != fragments.get(2) && fragments.get(2) instanceof MoreFragment) {
                        ((MoreFragment) fragments.get(2)).uploadImage(data.getData());
                    }
                } else {
                    if (null != fragments && null != fragments.get(2) && fragments.get(2) instanceof MoreFragment) {
                        ((MoreFragment) fragments.get(2)).uploadImage(getImageUri(getApplicationContext(), (Bitmap) data.getExtras().get("data")));
                    }
                }
                break;
            case Constants.REFRESH_RESERVATION_FRAGMENT:
                reservationFragment.setEditFlag(true);
                reservationFragment.editItem((Reservation) data.getSerializableExtra("reservation"));
                break;
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
        ApplicationManager.getInstance().setMainActivity(null);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        ApplicationManager.getInstance().setMainActivity(this);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public NotificationFragment getNotificationFragment() {
        return notificationFragment;
    }

    public void refreshNotificationBadgeCount() {
        if (null != badgeTabItemIcon) {
            badgeTabItemIcon.showTextBadge("" + ApplicationManager.getInstance().getNotificationCount());
            if (ApplicationManager.getInstance().getNotificationCount() == 0) {
                badgeTabItemIcon.hiddenBadge();
            }
            //TODO : 밖에있는 뱃지
        }
    }
}