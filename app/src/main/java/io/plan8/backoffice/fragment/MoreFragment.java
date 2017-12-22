package io.plan8.backoffice.fragment;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import io.plan8.backoffice.listener.EndlessRecyclerOnScrollListener;
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
    private final int GET_USER = 1;
    private final int GET_MEMBERS = 2;
    private boolean isCompletedGetUser = false;
    private boolean isCompletedGetMembers = false;
    private FragmentMoreBinding binding;
    private MoreFragmentVM vm;
    private List<BaseModel> moreFragmentData;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_more, container, false);
        vm = new MoreFragmentVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore(int currentPage) {
                refreshMoreFragmentData();
            }
        };

        binding.moreFragmentRefresh.setColorSchemeResources(R.color.colorAccent);
        binding.moreFragmentRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moreFragmentData.clear();
                endlessRecyclerOnScrollListener.initPrevItemCount();
                refreshMoreFragmentData();
                binding.moreFragmentRefresh.setRefreshing(false);
            }
        });
        refreshMoreFragmentData();
    }

    @Override
    public void onDestroy() {
        if (null != binding) {
            binding.unbind();
        }
        super.onDestroy();
    }

    private void completeRefreshData(int apiFlag) {
        if (null == moreFragmentData) {
            moreFragmentData = new ArrayList<>();
        }
        if (apiFlag == GET_USER) {
            isCompletedGetUser = true;
        }
        if (apiFlag == GET_MEMBERS) {
            isCompletedGetMembers = true;
        }

        if (isCompletedGetMembers && isCompletedGetUser) {
            moreFragmentData = new ArrayList<>();

            moreFragmentData.add(new LabelItem("내 프로필"));
            if (ApplicationManager.getInstance().getUser() != null) {
                moreFragmentData.add(ApplicationManager.getInstance().getUser());
            }
            moreFragmentData.add(new LabelItem("내가 속한 팀"));

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

            vm.setData(moreFragmentData);
        }
        setCompletedLoading(true);
    }

    public void refreshMoreFragmentData() {
        setCompletedLoading(false);
        Call<List<Member>> getUserMembersCall = RestfulAdapter.getInstance().getServiceApi().getUserMembers("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getContext()), ApplicationManager.getInstance().getUser().getId());
        getUserMembersCall.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                List<Member> members = response.body();
                ApplicationManager.getInstance().setMembers(members);
                completeRefreshData(GET_MEMBERS);
            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
                completeRefreshData(GET_MEMBERS);
            }
        });

        Call<User> meCall = RestfulAdapter.getInstance().getServiceApi().getMe("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getContext()));
        meCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                ApplicationManager.getInstance().setUser(user);
                completeRefreshData(GET_USER);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                completeRefreshData(GET_USER);
            }
        });
    }

    public void uploadImage(Uri data) {
        setCompletedLoading(false);
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
                List<Attachment> attachments = response.body();
                if (attachments != null) {
                    HashMap<String, String> putMeImage = new HashMap<String, String>();
                    putMeImage.put("avatar", attachments.get(0).getUrl());
                    Call<User> putMe = RestfulAdapter.getInstance().getServiceApi().putMe("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getActivity()), putMeImage);
                    putMe.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.body() != null) {
                                ApplicationManager.getInstance().setUser(response.body());
                                refreshMoreFragmentData();
                            }
                            setCompletedLoading(true);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getContext(), "프로필 사진 업로드에 실패하였습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                            setCompletedLoading(true);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Attachment>> call, Throwable t) {
                Toast.makeText(getContext(), "프로필 사진 업로드에 실패하였습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                setCompletedLoading(true);
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

    public void setCompletedLoading(boolean completedLoading) {
        vm.setCompletedLoading(completedLoading);
    }
}
