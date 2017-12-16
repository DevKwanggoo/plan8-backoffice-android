package io.plan8.backoffice.vm.item;

import android.content.Intent;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

import java.util.HashMap;
import java.util.Map;

import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.activity.DetailReservationActivity;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.model.api.Notification;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.util.ViewUtil;
import io.plan8.backoffice.vm.FragmentVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SSozi on 2017. 12. 5..
 */

public class NotificationItemVM extends FragmentVM {
    private Notification notification;

    public NotificationItemVM(Fragment fragment, Bundle savedInstanceState, Notification notification) {
        super(fragment, savedInstanceState);
        this.notification = notification;
    }

    @Bindable
    public String getText() {
        if (null == notification || null == notification.getAction()) {
            return "";
        }
        String text = "";
        text = ViewUtil.getInstance().getActionItemText(notification.getAction()) + "";
        if (null != notification.getAction().getText()
                && notification.getAction().getText().length() >= 75) {
            text += " " + notification.getAction().getText().substring(0, 75) + "...";
        } else {
            text += "";
        }

        return text;
    }

    @Bindable
    public String getAvatar() {
        if (null == notification || null == notification.getAction() || null == notification.getAction().getCreator()) {
            return "";
        }
        return notification.getAction().getCreator().getAvatar();
    }

    @Bindable
    public String getTeamNameAndLastModified() {
        String teamName = "픽스나우";
        String lastModified = "";

        if (null == notification) {
            lastModified = "";
        } else {
            lastModified = DateUtil.getInstance().getChatTime(notification.getAdded());
        }

        return teamName + " ∙ " + lastModified;
    }

    public void detailNotification(View view) {
        if (null == notification || null == notification.getAction() || null == notification.getAction().getReservation()) {
            return;
        }

        if (!notification.isRead()) {
            getFragment().getActivity().startActivity(DetailReservationActivity.buildIntent(getFragment().getContext(), notification.getAction().getReservation().getId(), notification.getId()));
            Map<String, Boolean> readMap = new HashMap<String, Boolean>();
            readMap.put("read", true);
            Call<Notification> readNotificationCall = RestfulAdapter.getInstance().getServiceApi().readNotification("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getFragment().getContext()), notification.getId(), readMap);
            readNotificationCall.enqueue(new Callback<Notification>() {
                @Override
                public void onResponse(Call<Notification> call, Response<Notification> response) {
                }

                @Override
                public void onFailure(Call<Notification> call, Throwable t) {
                }
            });
            setRead(true);
        }
        Intent detailTaskIntent = DetailReservationActivity.buildIntent(getFragment().getContext(), notification.getAction().getReservation().getId());
        getFragment().startActivity(detailTaskIntent);
    }

    @Bindable
    public boolean isRead() {
        if (null == notification) {
            return false;
        }
        return notification.isRead();
    }

    public void setRead(boolean read) {
        notification.setRead(read);
        notifyPropertyChanged(BR.read);
    }
}