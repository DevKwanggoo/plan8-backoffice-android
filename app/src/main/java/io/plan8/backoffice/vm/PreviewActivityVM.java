package io.plan8.backoffice.vm;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.model.api.Attachment;

/**
 * Created by SSozi on 2017. 11. 30..
 */

public class PreviewActivityVM extends ActivityVM {
    private Attachment attachment;

    public PreviewActivityVM(Activity activity, Bundle savedInstanceState, Attachment attachment) {
        super(activity, savedInstanceState);
        this.attachment = attachment;
    }

    @Bindable
    public String getPreviewUrl(){
        if (attachment != null && attachment.getUrl() != null){
            return attachment.getUrl();
        }
        return "";
    }

    @Bindable
    public String getPrevTitle() {
        if (attachment != null && attachment.getName() != null){
            return attachment.getName();
        }
        return "이름 없음";
    }

    public void closePreview(View view){
        getActivity().onBackPressed();
    }
}
