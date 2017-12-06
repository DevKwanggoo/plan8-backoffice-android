package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;

import io.plan8.backoffice.model.item.CommentReplaceItem;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by SSozi on 2017. 11. 29..
 */

public class DetailReservationCommentReplaceItemVM extends ActivityVM {
    private CommentReplaceItem replaceItem;

    public DetailReservationCommentReplaceItemVM(Activity activity, Bundle savedInstanceState, CommentReplaceItem replaceItem) {
        super(activity, savedInstanceState);
        this.replaceItem = replaceItem;
    }

    @Bindable
    public String getReplaceTitle(){
        if (replaceItem != null && replaceItem.getReplacePoint() != null){
            return replaceItem.getUserName() + "님이" + replaceItem.getReplacePoint() + "를 수정하였습니다.";
        }
        return "";
    }

    @Bindable
    public String getCreatedDate(){
        if (replaceItem != null && replaceItem.getCreatedDate() != null){
            return replaceItem.getCreatedDate();
        }
        return "";
    }
}
