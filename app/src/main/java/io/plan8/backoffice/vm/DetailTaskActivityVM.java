package io.plan8.backoffice.vm;

import android.app.Activity;
import android.databinding.Bindable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.DetailTaskActivity;
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter;
import io.plan8.backoffice.listener.OnTextChangeListener;
import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.model.item.Comment;
import io.plan8.backoffice.model.item.CommentFile;
import io.plan8.backoffice.model.item.CommentReplaceItem;
import io.plan8.backoffice.model.item.DetailTaskMoreButtonItem;
import io.plan8.backoffice.model.item.TaskItem;
import io.plan8.backoffice.vm.item.DetailTaskCommentFileItemVM;
import io.plan8.backoffice.vm.item.DetailTaskCommentItemVM;
import io.plan8.backoffice.vm.item.DetailTaskCommentReplaceItemVM;
import io.plan8.backoffice.vm.item.DetailTaskHeaderItemVM;
import io.plan8.backoffice.vm.item.DetailTaskMoreButtonItemVM;
import io.plan8.backoffice.vm.item.MentionItemVM;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class DetailTaskActivityVM extends ActivityVM {
    private BindingRecyclerViewAdapter<BaseModel> adapter;
    private BindingRecyclerViewAdapter<User> mentionAdapter;
    private List<BaseModel> datas;
    private List<User> userList;
    private BottomSheetDialog bottomSheetDialog;
    private boolean isActiveSendBtn;
    private OnTextChangeListener onTextChangeListener;
    private String currentText = "";
    private boolean isEmptyMentionList;

    public DetailTaskActivityVM(Activity activity, final Bundle savedInstanceState, List<BaseModel> datas) {
        super(activity, savedInstanceState);
        this.datas = datas;
        adapter = new BindingRecyclerViewAdapter<BaseModel>() {
            @Override
            protected int selectViewLayoutType(BaseModel data) {
                if (data instanceof TaskItem) {
                    return R.layout.item_detail_task_header;
                } else if (data instanceof CommentFile) {
                    return R.layout.item_detail_task_comment_file;
                } else if (data instanceof CommentReplaceItem) {
                    return R.layout.item_detail_task_comment_replace;
                } else if (data instanceof DetailTaskMoreButtonItem) {
                    return R.layout.item_detail_task_more_button;
                } else {
                    return R.layout.item_detail_task_comment;
                }
            }

            @Override
            protected void bindVariables(ViewDataBinding binding, BaseModel data) {
                if (data instanceof TaskItem) {
                    binding.setVariable(BR.vm, new DetailTaskHeaderItemVM(getActivity(), savedInstanceState, (TaskItem) data));
                } else if (data instanceof CommentFile) {
                    binding.setVariable(BR.vm, new DetailTaskCommentFileItemVM(getActivity(), savedInstanceState, (CommentFile) data));
                } else if (data instanceof CommentReplaceItem) {
                    binding.setVariable(BR.vm, new DetailTaskCommentReplaceItemVM(getActivity(), savedInstanceState, (CommentReplaceItem) data));
                } else if (data instanceof DetailTaskMoreButtonItem) {
                    binding.setVariable(BR.vm, new DetailTaskMoreButtonItemVM(getActivity(), savedInstanceState, (DetailTaskMoreButtonItem) data));
                } else {
                    binding.setVariable(BR.vm, new DetailTaskCommentItemVM(getActivity(), savedInstanceState, (Comment) data));
                }
            }
        };

        setData(datas);

        mentionAdapter = new BindingRecyclerViewAdapter<User>() {
            @Override
            protected int selectViewLayoutType(User data) {
                return R.layout.item_mention;
            }

            @Override
            protected void bindVariables(ViewDataBinding binding, User data) {
                binding.setVariable(BR.vm, new MentionItemVM(getActivity(), savedInstanceState, data));
            }
        };

        setAutoCompleteMentionData(null);

        initBottomSheet();
    }

    private void initBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
        AppCompatImageView bottomSheetFirstImageView = bottomSheetDialog.findViewById(R.id.bottomSheetFirstIcon);
        TextView bottomSheetFirstTitle = bottomSheetDialog.findViewById(R.id.bottomSheetFirstTitle);
        if (null != bottomSheetFirstImageView) {
            bottomSheetFirstImageView.setImageResource(R.drawable.ic_line_field);
            bottomSheetFirstImageView.setColorFilter(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.grayColor));
        }
        if (null != bottomSheetFirstTitle) {
            bottomSheetFirstTitle.setText("완료");
        }
        RelativeLayout bottomSheetFirstItem = bottomSheetDialog.findViewById(R.id.bottomSheetFirstItem);
        if (null != bottomSheetFirstItem) {
            bottomSheetFirstItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO : 리스트 갱신
//                    taskItem.setStatus(Constants.TASK_STATUS_BLUE);
//                    notifyPropertyChanged(BR.reservationStatus);
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
            bottomSheetSecondTitle.setText("미완료");
        }
        RelativeLayout bottomSheetSecondItem = bottomSheetDialog.findViewById(R.id.bottomSheetSecondItem);
        if (bottomSheetSecondItem != null) {
            bottomSheetSecondItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO : 리스트 갱신
//                    taskItem.setStatus(Constants.TASK_STATUS_RED);
//                    notifyPropertyChanged(BR.reservationStatus);
                    bottomSheetDialog.hide();
                }
            });
        }
    }

    @Bindable
    public boolean isActiveSendBtn() {
        return isActiveSendBtn;
    }

    public void setActiveSendBtn(boolean isActiveSendBtn) {
        this.isActiveSendBtn = isActiveSendBtn;
        notifyPropertyChanged(BR.activeSendBtn);
    }

    public void finish(View view) {
//        getActivity().onBackPressed();
//        getActivity().overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
        ((DetailTaskActivity) getActivity()).pickImageForCamera();
    }

    public void setData(List<BaseModel> datas) {
        this.datas = datas;
        adapter.setData(datas);
    }

    public RecyclerView.LayoutManager getVerticalLayoutManager() {
        return new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
    }

    public RecyclerView.LayoutManager getHorizontalLayoutManager() {
        return new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public BindingRecyclerViewAdapter<User> getMentionAdapter() {
        return mentionAdapter;
    }

    public void showBottomSheet() {
        bottomSheetDialog.show();
    }

    public void uploadFile(View view) {
        Toast.makeText(getActivity().getApplicationContext(), "파일 업로드", Toast.LENGTH_SHORT).show();
    }

    public void sendComment(View view) {
        Toast.makeText(getActivity().getApplicationContext(), "메시지 전송", Toast.LENGTH_SHORT).show();
    }

    public void setAutoCompleteMentionData(List<User> userList) {
        this.userList = userList;
        if (null != userList) {
            mentionAdapter.setData(userList);
        }
        if (null == userList
                || userList.size() <= 0) {
            setEmptyMentionList(true);
//            return true;
        } else {
            //TODO : 멘션 아이템의 클릭이벤트 발생시 요기 지나가게 해야함.
            setEmptyMentionList(false);
        }
//        notifyPropertyChanged(BR.emptyMentionList);
    }

    public OnTextChangeListener getTextChangeListener() {
        if (null == onTextChangeListener) {

            onTextChangeListener = new OnTextChangeListener() {
                @Override
                public void onChange(EditText editText, CharSequence charSequence, int charIndex, boolean isBackpress) {
                    currentText = editText.getText().toString();
                    if (currentText.length() > 0) {
                        setActiveSendBtn(true);
                    } else {
                        setActiveSendBtn(false);
                    }

                    //TODO : 마지막 index가 공백이면 setEmptyMentionList(true);
                    if (currentText.length() <= 0
                            || !currentText.contains("@")) {
                        setEmptyMentionList(true);
                    }
                }
            };
        }
        return onTextChangeListener;
    }

    @Bindable
    public boolean isEmptyMentionList() {
        return isEmptyMentionList;
    }

    public void setEmptyMentionList(boolean emptyMentionList) {
        this.isEmptyMentionList = emptyMentionList;
        notifyPropertyChanged(BR.emptyMentionList);
    }

    @Bindable
    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String text) {
        this.currentText = text;
        notifyPropertyChanged(BR.currentText);
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
