package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;

import io.plan8.backoffice.model.item.CommentFile;
import io.plan8.backoffice.vm.ActivityVM;
import io.plan8.backoffice.vm.BaseVM;

/**
 * Created by SSozi on 2017. 11. 29..
 */

public class DetailTaskCommentFileItemVM extends ActivityVM {
    private CommentFile commentFile;

    public DetailTaskCommentFileItemVM(Activity activity, Bundle savedInstanceState, CommentFile commentFile) {
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
}
