package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.activity.DetailReservationActivity;
import io.plan8.backoffice.model.api.Worker;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by chokwanghwan on 2017. 12. 1..
 */

public class MentionItemVM extends ActivityVM {
    private Worker worker;

    public MentionItemVM(Activity activity, Bundle savedInstanceState, Worker worker) {
        super(activity, savedInstanceState);
        this.worker = worker;
    }

    @Bindable
    public String getName() {
        if (worker != null && worker.getName() != null){
            return worker.getName();
        }
        return "이름없음";
    }

    @Bindable
    public String getUserName(){
        if (worker != null && worker.getUsername() != null){
            return "@" + worker.getUsername();
        }
        return "비어있음";
    }

    @Bindable
    public String getAvatar() {
        if (null == worker) {
            return "";
        }
        return worker.getAvatar();
    }

    public void clickMention(View view) {
        if (getActivity() instanceof DetailReservationActivity) {
            ((DetailReservationActivity) getActivity()).replaceToMention(worker);
        }
    }
}
