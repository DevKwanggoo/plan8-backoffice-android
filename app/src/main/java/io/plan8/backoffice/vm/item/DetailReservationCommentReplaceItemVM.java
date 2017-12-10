package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;

import io.plan8.backoffice.model.api.Comment;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.util.ViewUtil;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by SSozi on 2017. 11. 29..
 */

public class DetailReservationCommentReplaceItemVM extends ActivityVM {
    private Comment comment;

    public DetailReservationCommentReplaceItemVM(Activity activity, Bundle savedInstanceState, Comment comment) {
        super(activity, savedInstanceState);
        this.comment = comment;
    }

    @Bindable
    public String getReplaceTitle(){
        return ViewUtil.getInstance().getActivityItemText(comment);
    }

    @Bindable
    public String getCreatedDate(){
        if (null == comment) {
            return "";
        }
        return DateUtil.getInstance().getChatTime(comment.getCreated());
    }
}