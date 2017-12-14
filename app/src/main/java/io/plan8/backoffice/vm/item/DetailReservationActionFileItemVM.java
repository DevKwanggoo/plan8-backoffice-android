package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.content.Intent;
import android.databinding.Bindable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.PreviewActivity;
import io.plan8.backoffice.manager.RealTimeHandlerManager;
import io.plan8.backoffice.model.api.Action;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.util.FileDownloader;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by SSozi on 2017. 11. 29..
 */

public class DetailReservationActionFileItemVM extends ActivityVM {
    private Action action;
    private Handler handler;

    public DetailReservationActionFileItemVM(Activity activity, Bundle savedInstanceState, final Action action) {
        super(activity, savedInstanceState);
        this.action = action;

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                setCreated();
                Log.e(action.getType() + "-updated : ", "OK");
                this.sendEmptyMessageDelayed(0, 30000);
            }
        };
        RealTimeHandlerManager.getInstance().addActionHandler(handler);
        RealTimeHandlerManager.getInstance().addHandler("action", RealTimeHandlerManager.getInstance().getActionHandler());
        handler.sendEmptyMessage(0);
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
        return action.getAttachment().getName();
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

    public void fileDownload(View view){
        if (action != null && action.getAttachment() != null && action.getAttachment().getUrl() != null) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(action.getAttachment().getUrl()));
            getActivity().startActivity(i);
        } else {
            Toast.makeText(getActivity(), "파일을 다운로드 할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setCreated() {
        notifyPropertyChanged(BR.createdDate);
    }
}
