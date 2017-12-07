package io.plan8.backoffice.vm;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.activity.PreviewActivity;

/**
 * Created by SSozi on 2017. 11. 30..
 */

public class PreviewActivityVM extends ActivityVM {
    private String imageUrl;

    public PreviewActivityVM(Activity activity, Bundle savedInstanceState, String imageUrl) {
        super(activity, savedInstanceState);
        this.imageUrl = imageUrl;
    }

    @Bindable
    public String getPreviewUrl(){
        if (imageUrl != null && !imageUrl.equals("")){
            return imageUrl;
        }
        return "http://www.city.kr/files/attach/images/1326/622/387/004/cb59682631ac9d64a0a188c7833fc359.jpg";
    }

    public void closePreview(View view){
        getActivity().onBackPressed();
    }
}
