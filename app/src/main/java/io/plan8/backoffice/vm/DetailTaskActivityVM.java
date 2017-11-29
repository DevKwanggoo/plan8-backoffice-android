package io.plan8.backoffice.vm;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.EditTaskActivity;
import io.plan8.backoffice.model.item.TaskItem;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class DetailTaskActivityVM extends ActivityVM {
    private TaskItem taskItem;
    private BottomSheetDialog bottomSheetDialog;
    public DetailTaskActivityVM(Activity activity, Bundle savedInstanceState, TaskItem taskItem) {
        super(activity, savedInstanceState);
        this.taskItem = taskItem;
        initBottomSheet();
    }

    private void initBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(getActivity().getApplicationContext());
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
        AppCompatImageView bottomSheetFirstImageView = bottomSheetDialog.findViewById(R.id.bottomSheetFirstIcon);
        TextView bottomSheetFirstTitle = bottomSheetDialog.findViewById(R.id.bottomSheetFirstTitle);
        if (null != bottomSheetFirstImageView) {
            bottomSheetFirstImageView.setImageResource(R.drawable.ic_line_field);
            bottomSheetFirstImageView.setColorFilter(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.grayColor));
        }
        if (null != bottomSheetFirstTitle) {
            bottomSheetFirstTitle.setText("이름 편집");
        }
        RelativeLayout bottomSheetFirstItem = bottomSheetDialog.findViewById(R.id.bottomSheetFirstItem);
        if (null != bottomSheetFirstItem) {
            bottomSheetFirstItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    taskItem.setStatus(Constants.TASK_STATUS_BLUE);
                    notifyPropertyChanged(BR.reservationStatus);
                    bottomSheetDialog.hide();
                }
            });
        }

        final AppCompatImageView bottomSheetSecondImageView = bottomSheetDialog.findViewById(R.id.bottomSheetSecondIcon);
        TextView bottomSheetSecondTitle = bottomSheetDialog.findViewById(R.id.bottomSheetSecondTitle);
        if (null != bottomSheetSecondImageView) {
            bottomSheetSecondImageView.setImageResource(R.drawable.ic_line_field);
            bottomSheetSecondImageView.setColorFilter(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.grayColor));
        }
        if (null != bottomSheetSecondTitle) {
            bottomSheetSecondTitle.setText("프로필 사진 변경");
        }
        RelativeLayout bottomSheetSecondItem = bottomSheetDialog.findViewById(R.id.bottomSheetSecondItem);
        if (bottomSheetSecondItem != null) {
            bottomSheetSecondItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    taskItem.setStatus(Constants.TASK_STATUS_RED);
                    notifyPropertyChanged(BR.reservationStatus);
                    bottomSheetDialog.hide();
                }
            });
        }
    }

    @Bindable
    public String getCustomerName() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getCustomerName();
    }

    @Bindable
    public String getCustomerPhoneNumber() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getCustomerPhoneNumber();
    }

    @Bindable
    public String getCustomerAddress() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getCustomerAddress();
    }

    @Bindable
    public String getReservationDate() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getReservationDate();
    }

    @Bindable
    public String getReservationTime() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getReservationTime();
    }

    @Bindable
    public String getReservationEndTime() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getReservationEndTime();
    }

    @Bindable
    public String getProductionName() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getProductionName();
    }

    @Bindable
    public String getCustomerRequest() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getCustomerRequest();
    }

    @Bindable
    public String getProductionDescription() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getProductionDescription();
    }

    @Bindable
    public String getReservationStatus() {
        if (null != taskItem && taskItem.equals(Constants.TASK_STATUS_BLUE)) {
            return "완료";
        } else {
            return "미완료";
        }
    }

    public void finish(View view) {
        getActivity().onBackPressed();
        getActivity().overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
    }

    public void openEditActivity(View view) {
        getActivity().startActivity(EditTaskActivity.buildIntent(getActivity()));
        getActivity().overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
    }

    public void editTaskStatus(View view) {
        bottomSheetDialog.show();
    }
}
