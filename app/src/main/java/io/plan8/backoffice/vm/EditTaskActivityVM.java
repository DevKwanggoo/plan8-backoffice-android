package io.plan8.backoffice.vm;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.plan8.backoffice.activity.EditTaskActivity;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class EditTaskActivityVM extends ActivityVM {
    private EditTaskActivity editTaskActivity;
    public EditTaskActivityVM(EditTaskActivity editTaskActivity, Bundle savedInstanceState) {
        super(editTaskActivity, savedInstanceState);
        this.editTaskActivity = editTaskActivity;
    }

    public void finishEditTask(View view) {
        editTaskActivity.onBackPressed();
    }

    public void completeEditTask(View view) {
        Toast.makeText(getActivity().getApplicationContext(), "완료", Toast.LENGTH_SHORT).show();
    }
}
