package io.plan8.backoffice.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.ActivityDetailTaskBinding;
import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.model.item.Comment;
import io.plan8.backoffice.model.item.DetailTaskMoreButtonItem;
import io.plan8.backoffice.model.item.TaskItem;
import io.plan8.backoffice.vm.DetailTaskActivityVM;

public class DetailTaskActivity extends BaseActivity {
    private ActivityDetailTaskBinding binding;
    private DetailTaskActivityVM vm;
    private TaskItem taskItem;

    public static Intent buildIntent(Context context, TaskItem taskItem) {
        Intent intent = new Intent(context, DetailTaskActivity.class);
        intent.putExtra("taskItem", taskItem);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.taskItem = (TaskItem) getIntent().getSerializableExtra("taskItem");
        List<BaseModel> testData = new ArrayList<>();
        testData.add(taskItem);
        testData.add(new DetailTaskMoreButtonItem("이전 내용 보기"));
        testData.add(new Comment("김주석", "댓글입니당\n댓글요\n그래요 댓글", "2일 전"));
        testData.add(new Comment("이주석", "댓글입니당동해물과백두산이\n댓글요댓글입니당동해물과백두산이\n그래요 댓글입니당동해물과백두산이댓글", "3일 전"));
        testData.add(new Comment("이주석", "댓글입니당동해물과백두산이\n댓글요댓글입니당동해물과백두산이\n그래요 댓글입니당동해물과백두산이댓글", "3일 전"));
        testData.add(new Comment("이주석", "댓글입니당동해물과백두산이\n댓글요댓글입니당동해물과백두산이\n그래요 댓글입니당동해물과백두산이댓글", "3일 전"));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_task);
        vm = new DetailTaskActivityVM(this, savedInstanceState, testData);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();
    }

    @Override
    protected void onDestroy() {
        binding.unbind();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
    }

    public void showBottomSheet() {
        if (null != vm) {
            vm.showBottomSheet();
        }
    }

    public void deleteComment(Comment comment) {
        //TODO : 코멘트 삭제
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .content("댓글을 삭제하시겠어요?")
                .positiveText("삭제")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(getApplicationContext(), "댓글 삭제", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        dialog.show();
    }

    public void callMoreComment() {
        //TODO : 이전 내용 보기
        Toast.makeText(getApplicationContext(), "이전 내용 보기", Toast.LENGTH_SHORT).show();
    }
}