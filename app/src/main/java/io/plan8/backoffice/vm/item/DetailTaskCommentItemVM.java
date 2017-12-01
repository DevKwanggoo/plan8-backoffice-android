package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.activity.DetailTaskActivity;
import io.plan8.backoffice.model.item.Comment;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class DetailTaskCommentItemVM extends ActivityVM {
    private Comment comment;

    public DetailTaskCommentItemVM(Activity activity, Bundle savedInstanceState, Comment comment) {
        super(activity, savedInstanceState);
        this.comment = comment;
    }

    @Bindable
    public String getImageUrl() {
        return "http://i.imgur.com/DvpvklR.png";
    }

    @Bindable
    public String getAuthor() {
        if (null == comment) {
            return "";
        }
        return comment.getAuthor();
    }

    @Bindable
    public String getComment() {
        if (null == comment) {
            return "";
        }
        return comment.getComment();
    }


    @Bindable
    public String getCreated() {
        if (null == comment) {
            return "";
        }
        return comment.getCreated();
    }

    public void deleteComment(View view) {
        if (getActivity() instanceof DetailTaskActivity) {
            ((DetailTaskActivity) getActivity()).deleteComment(comment);
        }
    }
}
