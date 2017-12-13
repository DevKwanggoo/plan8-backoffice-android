package io.plan8.backoffice.fragment;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.databinding.FragmentMoreBinding;
import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.model.api.Attachment;
import io.plan8.backoffice.model.api.Member;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.model.item.EmptySpaceItem;
import io.plan8.backoffice.model.item.LabelItem;
import io.plan8.backoffice.vm.MoreFragmentVM;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class MoreFragment extends BaseFragment {
    private FragmentMoreBinding binding;
    private MoreFragmentVM vm;
    private RelativeLayout progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        List<BaseModel> moreFragmentData = new ArrayList<>();

        moreFragmentData.add(new LabelItem("내 프로필"));
        if (ApplicationManager.getInstance().getUser() != null) {
            moreFragmentData.add(ApplicationManager.getInstance().getUser());
        }
        moreFragmentData.add(new LabelItem("팀 선택"));

        if (null != ApplicationManager.getInstance().getMembers()) {
            List<Member> members = new ArrayList<>();
            for (Member m : ApplicationManager.getInstance().getMembers()) {
                if (null != m) {
                    members.add(m);
                }
            }

            if (members.size() > 0) {
                moreFragmentData.addAll(members);
            }
        }
        moreFragmentData.add(new EmptySpaceItem(0));

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_more, container, false);
        vm = new MoreFragmentVM(this, savedInstanceState, moreFragmentData);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        progressBar = binding.moreMenuProgressBar;
        vm.setData(moreFragmentData);

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        binding.unbind();
        super.onDestroy();
    }

    public void uploadImage(Uri data) {
        Uri uri = data;
        String imagePath = getRealPathFromURI(uri);

        final Cursor cursor = getActivity().getContentResolver().query(Uri.parse(uri.toString()), null, null, null, null);
        assert cursor != null;
        cursor.moveToNext();
        String absolutePath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
        cursor.close();

        File files = new File(absolutePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse(getActivity().getContentResolver().getType(uri)), files);
        MultipartBody.Part multipart = MultipartBody.Part.createFormData("files", files.getName(), requestFile);
        Call<List<Attachment>> uploadCall = RestfulAdapter.getInstance().getServiceApi().postUpload("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getActivity()), multipart);
        uploadCall.enqueue(new Callback<List<Attachment>>() {
            @Override
            public void onResponse(Call<List<Attachment>> call, Response<List<Attachment>> response) {
                if (response.body() != null) {
                    HashMap<String, String> putMeImage = new HashMap<String, String>();
                    putMeImage.put("avatar", response.body().get(0).getUrl());
                    Call<User> putMe = RestfulAdapter.getInstance().getServiceApi().putMe("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getActivity()), putMeImage);
                    putMe.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.body() != null) {
                                ApplicationManager.getInstance().setUser(response.body());
                                refreshFragment();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getContext(), "프로필 사진 업로드에 실패하였습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Attachment>> call, Throwable t) {
                Toast.makeText(getContext(), "프로필 사진 업로드에 실패하였습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getRealPathFromURI(Uri imageUri) {
        String[] path = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(imageUri, path, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void refreshFragment() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .detach(this)
                .attach(this)
                .commitAllowingStateLoss();
    }
}
