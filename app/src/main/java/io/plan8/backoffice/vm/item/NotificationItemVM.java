package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

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
        text = ViewUtil.getInstance().getActionItemText(notification.getAction()) + " ";
        if (null != notification.getAction().getText()
                && notification.getAction().getText().length() >= 75) {
            text += notification.getAction().getText().substring(0, 75) + "...";
        } else {
            text += notification.getAction().getText();
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
    public String getLastModified() {
        if (null == notification) {
            return "";
        }

        return DateUtil.getInstance().getChatTime(notification.getCreated());
    }

    public void detailNotification(View view) {
        if (null == notification || null == notification.getAction()) {
            return;
        }

        getFragment().getActivity().startActivity(DetailReservationActivity.buildIntent(getFragment().getContext(), notification.getAction().getReservation().getId(), notification.getId()));
        Call<Notification> readNotificationCall = RestfulAdapter.getInstance().getServiceApi().readNotification("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getFragment().getContext()), notification.getId(), true);
        readNotificationCall.enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
                setRead(true);
            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {
                Log.e("test", "test");
            }
        });
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