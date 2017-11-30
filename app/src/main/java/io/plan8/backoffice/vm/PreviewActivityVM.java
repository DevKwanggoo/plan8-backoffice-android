package io.plan8.backoffice.vm;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;

/**
 * Created by SSozi on 2017. 11. 30..
 */

public class PreviewActivityVM extends ActivityVM {
    public PreviewActivityVM(Activity activity, Bundle savedInstanceState) {
        super(activity, savedInstanceState);
    }

    @Bindable
    public String getPreviewUrl(){
        return "http://www.city.kr/files/attach/images/1326/622/387/004/cb59682631ac9d64a0a188c7833fc359.jpg";
    }
}
