package io.plan8.backoffice.vm.item;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.Bindable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.InputType;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.HashMap;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.BR;
import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.model.api.Me;
import io.plan8.backoffice.model.item.MoreProfileItem;
import io.plan8.backoffice.vm.FragmentVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class MoreProfileItemVM extends FragmentVM {
    private String url;
    private MoreProfileItem moreProfileItem;
    private BottomSheetDialog bottomSheetDialog;

    public MoreProfileItemVM(Fragment fragment, Bundle savedInstanceState, MoreProfileItem moreProfileItem) {
        super(fragment, savedInstanceState);
        this.moreProfileItem = moreProfileItem;
        initBottomSheet();
    }

    private void initBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(getFragment().getContext());
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
        final AppCompatImageView bottomSheetFirstImageView = bottomSheetDialog.findViewById(R.id.bottomSheetFirstIcon);
        TextView bottomSheetFirstTitle = bottomSheetDialog.findViewById(R.id.bottomSheetFirstTitle);
        if (null != bottomSheetFirstImageView) {
            bottomSheetFirstImageView.setImageResource(R.drawable.ic_line_field);
            bottomSheetFirstImageView.setColorFilter(ContextCompat.getColor(getFragment().getContext(), R.color.grayColor));
        }
        if (null != bottomSheetFirstTitle) {
            bottomSheetFirstTitle.setText("이름 편집");
        }
        RelativeLayout bottomSheetFirstItem = bottomSheetDialog.findViewById(R.id.bottomSheetFirstItem);
        if (null != bottomSheetFirstItem) {
            bottomSheetFirstItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bottomSheetDialog.hide();
                    new MaterialDialog.Builder(getFragment().getContext())
                            .title("내 이름 편집")
                            .inputType(InputType.TYPE_CLASS_TEXT)
                            .positiveText("완료")
                            .negativeText("취소")
                            .input("다른 사람에게 표시될 프로필명을 입력하세요.", moreProfileItem.getName(), new MaterialDialog.InputCallback() {
                                @Override
                                public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                    if (!input.equals("")) {
                                        HashMap<String, String> putMap = new HashMap<String, String>();
                                        putMap.put("name", input.toString());
                                        Call<Me> putMeCall = RestfulAdapter.getInstance().getServiceApi().putMe("Bearer "+ SharedPreferenceManager.getInstance().getUserToken(getFragment().getContext()), putMap);
                                        putMeCall.enqueue(new Callback<Me>() {
                                            @Override
                                            public void onResponse(Call<Me> call, Response<Me> response) {
                                                ApplicationManager.getInstance().setMe(response.body());
                                                refreshFragment();
                                            }

                                            @Override
                                            public void onFailure(Call<Me> call, Throwable t) {
                                                Toast.makeText(getFragment().getContext(), "잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                    dialog.dismiss();
                                }
                            });

                }
            });
        }

        final AppCompatImageView bottomSheetSecondImageView = bottomSheetDialog.findViewById(R.id.bottomSheetSecondIcon);
        TextView bottomSheetSecondTitle = bottomSheetDialog.findViewById(R.id.bottomSheetSecondTitle);
        if (null != bottomSheetSecondImageView) {
            bottomSheetSecondImageView.setImageResource(R.drawable.ic_line_field);
            bottomSheetSecondImageView.setColorFilter(ContextCompat.getColor(getFragment().getContext(), R.color.grayColor));
        }
        if (null != bottomSheetSecondTitle) {
            bottomSheetSecondTitle.setText("프로필 사진 변경");
        }
        RelativeLayout bottomSheetSecondItem = bottomSheetDialog.findViewById(R.id.bottomSheetSecondItem);
        if (null != bottomSheetSecondItem) {
            bottomSheetSecondItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bottomSheetDialog.hide();
                    if (ContextCompat.checkSelfPermission(getFragment().getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
                            || ContextCompat.checkSelfPermission(getFragment().getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        PermissionListener permissionListener = new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/*");
                                getFragment().startActivityForResult(intent, Constants.SELECT_IMAGE_CODE);
                            }

                            @Override
                            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                            }
                        };

                        TedPermission.with(getFragment().getContext())
                                .setPermissionListener(permissionListener)
                                .setRationaleMessage("프로필 이미지 변경을 정상적으로 사용하기 위해서는 내부저장소 접근권한이 필요합니다.")
                                .setDeniedMessage("접근권한을 거부하셨습니다. \n[설정] > [권한] 에서 권한을 허용할 수 있습니다.")
                                .setPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .check();
                    } else {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        getFragment().startActivityForResult(intent, Constants.SELECT_IMAGE_CODE);
                    }
                }
            });
        }
    }

    private void refreshFragment() {
        getFragment().getActivity().getSupportFragmentManager()
                .beginTransaction()
                .detach(getFragment())
                .attach(getFragment())
                .commitAllowingStateLoss();
    }

    @Bindable
    public String getTestUri() {
        if (null != ApplicationManager.getInstance().getMe()) {
            return ApplicationManager.getInstance().getMe().getAvatar();
        } else {
            return url;
        }
    }

    public void setTestUri(String uri) {
        this.url = uri;
        notifyPropertyChanged(BR.testUri);
    }

    @Bindable
    public String getProfileName() {
        if (null == moreProfileItem) {
            return "";
        }
        return moreProfileItem.getName();
    }

    public void setMoreProfileItem(String profileName) {
        moreProfileItem.setName(profileName);
        notifyPropertyChanged(BR.profileName);
    }

    @Bindable
    public String getProfilePhoneNumber() {
        if (null == moreProfileItem) {
            return "";
        }
        return moreProfileItem.getPhoneNumber();
    }

    public void editProfile(View view) {
        bottomSheetDialog.show();
    }
}