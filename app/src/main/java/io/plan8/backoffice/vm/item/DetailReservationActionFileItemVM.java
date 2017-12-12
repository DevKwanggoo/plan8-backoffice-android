package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.content.Intent;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.PreviewActivity;
import io.plan8.backoffice.model.api.Action;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by SSozi on 2017. 11. 29..
 */

public class DetailReservationActionFileItemVM extends ActivityVM {
    private Action action;

    public DetailReservationActionFileItemVM(Activity activity, Bundle savedInstanceState, Action action) {
        super(activity, savedInstanceState);
        this.action = action;
    }

    @Bindable
    public boolean isImage() {
        if (null == action || null == action.getAttachment() || null == action.getAttachment().getMimetype()) {
            return false;
        }
        return action.getAttachment().getMimetype().contains("image");
    }

    @Bindable
    public String getAuthName() {
        if (null == action || null == action.getCreator()) {
            return "";
        }

        return action.getCreator().getName();
    }

    @Bindable
    public String getAuthAvatar() {
        if (null == action || null == action.getCreator()) {
            return "";
        }
        return action.getCreator().getAvatar();
    }

    @Bindable
    public String getFileName() {
        if (null == action || null == action.getAttachment()) {
            return "";
        }
        return action.getAttachment().getName() + "." + action.getAttachment().getMimetype();
    }

    @Bindable
    public String getCreatedDate() {
        if (null == action) {
            return "";
        }
        return DateUtil.getInstance().getChatTime(action.getCreated());
    }

    @Bindable
    public String getImageUrl() {
        if (null == action || null == action.getAttachment()) {
            return "";
        }
        return action.getAttachment().getUrl();
    }

    public void previewImage(View view) {
        if (null == action || null == action.getAttachment()) {
            return;
        }
        Intent previewIntent = PreviewActivity.buildIntent(getActivity(), action.getAttachment());
        getActivity().startActivity(previewIntent);
        getActivity().overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
    }
}