package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.plan8.backoffice.model.item.LabelItem;
import io.plan8.backoffice.vm.FragmentVM;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class LabelItemVM extends FragmentVM {
    private LabelItem labelItem;
    public LabelItemVM(Fragment fragment, Bundle savedInstanceState, LabelItem labelItem) {
        super(fragment, savedInstanceState);
        this.labelItem = labelItem;
    }

    @Bindable
    public String getMoreTitle() {
        if (null == labelItem) {
            return "";
        }
        return labelItem.getTitle();
    }
}
