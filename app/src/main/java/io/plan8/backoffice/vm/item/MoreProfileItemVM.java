package io.plan8.backoffice.vm.item;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.Bindable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.dialog.Plan8BottomSheetDialog;
import io.plan8.backoffice.fragment.MoreFragment;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.vm.FragmentVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class MoreProfileItemVM extends FragmentVM implements View.OnClickListener {
    private String url;
    private User user;
    private Plan8BottomSheetDialog plan8BottomSheetDialog;
    private Pattern usernamePattern;
    private Matcher matcher;

    public MoreProfileItemVM(Fragment fragment, Bundle savedInstanceState, User user) {
        super(fragment, savedInstanceState);
        this.user = user;
        initBottomSheet();
    }

    private void initBottomSheet() {
        plan8BottomSheetDialog = new Plan8BottomSheetDialog(getFragment().getContext());
        plan8BottomSheetDialog.setFirstItem(R.drawable.ic_line_field, "이름 편집");
        plan8BottomSheetDialog.getFirstItem().setOnClickListener(this);
        plan8BottomSheetDialog.setSecondItem(R.drawable.ic_line_field, "아이디 변경");
        plan8BottomSheetDialog.getSecondItem().setOnClickListener(this);
        plan8BottomSheetDialog.setThirdItem(R.drawable.ic_line_camera, "프로필 사진 변경");
        plan8BottomSheetDialog.getThirdItem().setOnClickListener(this);
        usernamePattern = Pattern.compile("^([A-Za-z0-9_]{1,15})$");
    }

    private void refreshFragment() {
        if (getFragment() instanceof MoreFragment) {
            ((MoreFragment) getFragment()).refreshMoreFragmentData();
        }
    }

    @Bindable
    public String getAvatar() {
        if (null == ApplicationManager.getInstance().getUser()) {
            return "";
        }
        return ApplicationManager.getInstance().getUser().getAvatar();
    }

    @Bindable
    public String getProfileName() {
        if (null == user.getName()) {
            return "이름 없음";
        }
        return user.getName();
    }

    @Bindable
    public String getProfileUserName() {
        if (user != null && user.getUsername() != null) {
            return "@" + user.getUsername();
        }
        return "";
    }

    public void editProfile(View view) {
        plan8BottomSheetDialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bottomSheetFirstItem) {
            plan8BottomSheetDialog.hide();
            new MaterialDialog.Builder(getFragment().getContext())
                    .title("내 이름 편집")
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .positiveText("완료")
                    .negativeText("취소")
                    .input("다른 사람에게 표시될 프로필명을 입력하세요.", user.getName(), new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                            if (!input.toString().trim().equals("")) {
                                ((MoreFragment) getFragment()).setCompletedLoading(false);
                                HashMap<String, String> putMap = new HashMap<String, String>();
                                putMap.put("name", input.toString());
                                Call<User> putMeCall = RestfulAdapter.getInstance().getServiceApi().putMe("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getFragment().getContext()), putMap);
                                putMeCall.enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        ApplicationManager.getInstance().setUser(response.body());
                                        refreshFragment();
                                        if (getFragment() instanceof MoreFragment) {
                                            ((MoreFragment) getFragment()).setCompletedLoading(true);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                        Toast.makeText(getFragment().getContext(), "잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                                        ((MoreFragment) getFragment()).setCompletedLoading(true);
                                    }
                                });
                            } else {
                                Toast.makeText(getFragment().getContext(), "변경할 프로필명을 한 글자 이상 입력하세요.", Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }).show();
        } else if (view.getId() == R.id.bottomSheetSecondItem) {
            plan8BottomSheetDialog.hide();
            new MaterialDialog.Builder(getFragment().getContext())
                    .title("아이디 편집")
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .positiveText("완료")
                    .negativeText("취소")
                    .input("다른 사람에게 표시될 아이디를 입력하세요.", user.getUsername(), new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                            if (!input.toString().trim().equals("")) {
                                matcher = usernamePattern.matcher(input.toString());
                                if (matcher.find()) {
                                    ((MoreFragment) getFragment()).setCompletedLoading(false);
                                    HashMap<String, String> putUserMap = new HashMap<String, String>();
                                    putUserMap.put("username", input.toString());
                                    Call<User> putUser = RestfulAdapter.getInstance().getServiceApi().putMe("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getFragment().getContext()), putUserMap);
                                    putUser.enqueue(new Callback<User>() {
                                        @Override
                                        public void onResponse(Call<User> call, Response<User> response) {
                                            User user = response.body();
                                            if (user != null) {
                                                ApplicationManager.getInstance().setUser(user);
                                                refreshFragment();
                                            } else {
                                                Toast.makeText(getFragment().getContext(), "중복된 아이디가 있거나 사용할 수 없는 아이디입니다.", Toast.LENGTH_SHORT).show();
                                            }
                                            ((MoreFragment) getFragment()).setCompletedLoading(true);
                                        }

                                        @Override
                                        public void onFailure(Call<User> call, Throwable t) {
                                            Toast.makeText(getFragment().getContext(), "아이디 변경에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                            ((MoreFragment) getFragment()).setCompletedLoading(true);
                                        }
                                    });
                                } else {
                                    Toast.makeText(getFragment().getContext(), "중복된 아이디가 있거나 사용할 수 없는 아이디입니다.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getFragment().getContext(), "변경할 아이디를 한 글자 이상 입력하세요.", Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }).show();
        } else {
            plan8BottomSheetDialog.hide();
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
    }
}