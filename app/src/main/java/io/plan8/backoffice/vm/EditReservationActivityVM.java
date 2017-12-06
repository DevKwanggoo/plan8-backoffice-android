package io.plan8.backoffice.vm;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.plan8.backoffice.activity.EditReservationActivity;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class EditReservationActivityVM extends ActivityVM {
    private EditReservationActivity editReservationActivity;
    public EditReservationActivityVM(EditReservationActivity editReservationActivity, Bundle savedInstanceState) {
        super(editReservationActivity, savedInstanceState);
        this.editReservationActivity = editReservationActivity;
    }

    public void finishEditReservation(View view) {
        editReservationActivity.onBackPressed();
    }

    public void completeEditReservation(View view) {
        Toast.makeText(getActivity().getApplicationContext(), "완료", Toast.LENGTH_SHORT).show();
    }
}
