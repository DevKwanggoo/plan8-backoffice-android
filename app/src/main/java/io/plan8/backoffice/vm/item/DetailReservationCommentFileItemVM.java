package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.content.Intent;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.PreviewActivity;
import io.plan8.backoffice.model.api.Comment;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by SSozi on 2017. 11. 29..
 */

public class DetailReservationCommentFileItemVM extends ActivityVM {
    private Comment comment;

    public DetailReservationCommentFileItemVM(Activity activity, Bundle savedInstanceState, Comment comment) {
        super(activity, savedInstanceState);
        this.comment = comment;
    }

    @Bindable
    public boolean isImage() {
        if (null == comment || null == comment.getAttachment() || null == comment.getAttachment().getMimetype()) {
            return false;
        }
        return comment.getAttachment().getMimetype().contains("image");
    }

    @Bindable
    public String getAuthName() {
        if (null == comment || null == comment.getCreator()) {
            return "";
        }

        return comment.getCreator().getName();
    }

    @Bindable
    public String getAuthAvatar() {
        if (null == comment || null == comment.getCreator()) {
            return "";
        }
        return comment.getCreator().getAvatar();
    }

    @Bindable
    public String getFileName() {
        if (null == comment || null == comment.getAttachment()) {
            return "";
        }
        return comment.getAttachment().getName() + "." + comment.getAttachment().getMimetype();
    }

    @Bindable
    public String getCreatedDate() {
        if (null == comment) {
            return "";
        }
        return DateUtil.getInstance().getChatTime(comment.getCreated());
    }

    @Bindable
    public String getImageUrl() {
        if (null == comment || null == comment.getAttachment()) {
            return "";
        }
        return comment.getAttachment().getUrl();
    }

    public void previewImage(View view) {
        if (null == comment || null == comment.getAttachment()) {
            return;
        }
        Intent previewIntent = PreviewActivity.buildIntent(getActivity(), comment.getAttachment());
        getActivity().startActivity(previewIntent);
        getActivity().overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
    }
}
