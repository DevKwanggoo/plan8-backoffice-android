package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.content.Intent;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.PreviewActivity;
import io.plan8.backoffice.model.item.CommentFile;
import io.plan8.backoffice.vm.ActivityVM;
import io.plan8.backoffice.vm.BaseVM;

/**
 * Created by SSozi on 2017. 11. 29..
 */

public class DetailReservationCommentFileItemVM extends ActivityVM {
    private CommentFile commentFile;

    public DetailReservationCommentFileItemVM(Activity activity, Bundle savedInstanceState, CommentFile commentFile) {
        super(activity, savedInstanceState);
        this.commentFile = commentFile;
    }

    @Bindable
    public boolean isImage(){
        return commentFile.getMimeType().contains("image");
    }

    @Bindable
    public String getAuthName(){
        if (commentFile != null && commentFile.getName() != null){
            return commentFile.getName();
        }
        return "";
    }

    @Bindable
    public String getAuthAvatar() {
        if (commentFile != null && commentFile.getAuthAvatar() != null){
            return commentFile.getAuthAvatar();
        }
        return "";
    }

    @Bindable
    public String getFileName(){
        if (commentFile != null && commentFile.getFileName() != null){
            return commentFile.getFileName() + "." +commentFile.getMimeType();
        }
        return "";
    }

    @Bindable
    public String getCreatedDate(){
        if (commentFile != null && commentFile.getCreatedDate() != null){
            return commentFile.getCreatedDate();
        }
        return "";
    }

    @Bindable
    public String getImageUrl() {
        if (commentFile != null && commentFile.getImageUrl() != null){
            return commentFile.getImageUrl();
        }
        return "";
    }

    public void previewImage(View view){
        Intent previewIntent = PreviewActivity.buildIntent(getActivity(), commentFile.getImageUrl());
        getActivity().startActivity(previewIntent);
        getActivity().overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
    }
}
