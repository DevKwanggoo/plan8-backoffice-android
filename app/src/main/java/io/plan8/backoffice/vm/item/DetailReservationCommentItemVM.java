package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;

import io.plan8.backoffice.model.api.Comment;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class DetailReservationCommentItemVM extends ActivityVM {
    private Comment comment;

    public DetailReservationCommentItemVM(Activity activity, Bundle savedInstanceState, Comment comment) {
        super(activity, savedInstanceState);
        this.comment = comment;
    }

    @Bindable
    public String getImageUrl() {
        return "http://i.imgur.com/DvpvklR.png";
    }

    @Bindable
    public String getAuthor() {
        if (null == comment || null == comment.getCreator()) {
            return "";
        }
        return comment.getCreator().getName();
    }

    @Bindable
    public String getComment() {
        if (null == comment) {
            return "";
        }
        return comment.getText();
    }


    @Bindable
    public String getCreated() {
        if (null == comment) {
            return "";
        }
        return DateUtil.getInstance().getChatTime(comment.getCreated());
    }
}
