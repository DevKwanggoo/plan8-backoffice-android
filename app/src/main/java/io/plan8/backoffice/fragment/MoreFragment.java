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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.databinding.FragmentMoreBinding;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class MoreFragment extends BaseFragment {
    private FragmentMoreBinding binding;
    private RelativeLayout progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        List<Object> testData = new ArrayList<>();
        String userName;

        if (Constants.getMe().getUserName() != null) {
            userName = Constants.getMe().getUserName();
        } else {
            userName = "이름없음";
        }

        testData.add(new MoreProfileItem(userName, Constants.getMe().getPhoneNumber()));
        testData.add(new MoreTitleItem("팀 선택"));

        if (Constants.getMe().getTeam() != null && Constants.getMe().getTeam().size() > 0) {
            for (int i = 0; i < Constants.getMe().getTeam().size(); i++) {
                testData.add(new MoreTeamItem(Constants.getMe().getTeam().getTeamName(), Constants.getMe().getTeam().getTeamName()));
            }
        }
        testData.add(new EmptySpaceItem(0));
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_more, container, false);
        vm = MoreFragmentVM(this, savedInstanceState, testData);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        progressBar = binding.moreMenuProgressBar;

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

//        if (RestfulAdapter.instance !!.serviceApi != null){
//            RestfulAdapter.instance !!.serviceApi !!.
//            postUpload("Bearer " + SharedPreferenceManager(activity).userToken, requestFile).enqueue(object :
//            Callback<List<UploadInfo>> {
//                override fun onResponse(call:Call<List<UploadInfo>>?,response:
//                Response<List<UploadInfo>>?){
//                    Log.e("test", "test")
//                }
//
//                override fun onFailure(call:Call<List<UploadInfo>>?,t:
//                Throwable ?){
//                    Log.e("test", "test")
//                }
//            })
//        }
    }

    private String getRealPathFromURI(Uri imageUri) {
        String[] path = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(imageUri, path, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
