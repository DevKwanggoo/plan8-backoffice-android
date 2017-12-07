package io.plan8.backoffice.vm;

import android.app.Activity;
import android.databinding.Bindable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.DetailReservationActivity;
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter;
import io.plan8.backoffice.dialog.Plan8BottomSheetDialog;
import io.plan8.backoffice.listener.OnTextChangeListener;
import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.model.api.Member;
import io.plan8.backoffice.model.api.Reservation;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.model.item.Comment;
import io.plan8.backoffice.model.item.CommentFile;
import io.plan8.backoffice.model.item.CommentReplaceItem;
import io.plan8.backoffice.model.item.DetailReservationMoreButtonItem;
import io.plan8.backoffice.vm.item.DetailReservationCommentFileItemVM;
import io.plan8.backoffice.vm.item.DetailReservationCommentItemVM;
import io.plan8.backoffice.vm.item.DetailReservationCommentReplaceItemVM;
import io.plan8.backoffice.vm.item.DetailReservationHeaderItemVM;
import io.plan8.backoffice.vm.item.DetailReservationMoreButtonItemVM;
import io.plan8.backoffice.vm.item.MentionItemVM;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class DetailReservationActivityVM extends ActivityVM implements View.OnClickListener {
    private BindingRecyclerViewAdapter<BaseModel> adapter;
    private BindingRecyclerViewAdapter<Member> mentionAdapter;
    private List<Member> memberList;
    private Plan8BottomSheetDialog plan8BottomSheetDialog;
    private boolean isActiveSendBtn;
    private OnTextChangeListener onTextChangeListener;
    private String currentText = "";
    private int currentTextIndex;
    private boolean isEmptyMentionList;
    private boolean loadingFlag = false;

    public DetailReservationActivityVM(Activity activity, final Bundle savedInstanceState) {
        super(activity, savedInstanceState);
        adapter = new BindingRecyclerViewAdapter<BaseModel>() {
            @Override
            protected int selectViewLayoutType(BaseModel data) {
                if (data instanceof Reservation) {
                    return R.layout.item_detail_reservation_header;
                } else if (data instanceof CommentFile) {
                    return R.layout.item_detail_reservation_comment_file;
                } else if (data instanceof CommentReplaceItem) {
                    return R.layout.item_detail_reservation_comment_replace;
                } else if (data instanceof DetailReservationMoreButtonItem) {
                    return R.layout.item_detail_reservation_more_button;
                } else {
                    return R.layout.item_detail_reservation_comment;
                }
            }

            @Override
            protected void bindVariables(ViewDataBinding binding, BaseModel data) {
                if (data instanceof Reservation) {
                    binding.setVariable(BR.vm, new DetailReservationHeaderItemVM(getActivity(), savedInstanceState, (Reservation) data));
                } else if (data instanceof CommentFile) {
                    binding.setVariable(BR.vm, new DetailReservationCommentFileItemVM(getActivity(), savedInstanceState, (CommentFile) data));
                } else if (data instanceof CommentReplaceItem) {
                    binding.setVariable(BR.vm, new DetailReservationCommentReplaceItemVM(getActivity(), savedInstanceState, (CommentReplaceItem) data));
                } else if (data instanceof DetailReservationMoreButtonItem) {
                    binding.setVariable(BR.vm, new DetailReservationMoreButtonItemVM(getActivity(), savedInstanceState, (DetailReservationMoreButtonItem) data));
                } else {
                    binding.setVariable(BR.vm, new DetailReservationCommentItemVM(getActivity(), savedInstanceState, (Comment) data));
                }
            }
        };

        mentionAdapter = new BindingRecyclerViewAdapter<Member>() {
            @Override
            protected int selectViewLayoutType(Member data) {
                return R.layout.item_mention;
            }

            @Override
            protected void bindVariables(ViewDataBinding binding, Member data) {
                binding.setVariable(BR.vm, new MentionItemVM(getActivity(), savedInstanceState, data));
            }
        };

        setAutoCompleteMentionData(null);

        initBottomSheet();
    }

    private void initBottomSheet() {
        plan8BottomSheetDialog = new Plan8BottomSheetDialog(getActivity());
        plan8BottomSheetDialog.setFirstItem("완료");
        plan8BottomSheetDialog.getFirstItem().setOnClickListener(this);
        plan8BottomSheetDialog.setSecondItem("대기");
        plan8BottomSheetDialog.getSecondItem().setOnClickListener(this);
        plan8BottomSheetDialog.setThirdItem("취소됨");
        plan8BottomSheetDialog.getThirdItem().setOnClickListener(this);
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
        ((DetailReservationActivity) getActivity()).pickImageForCamera();
    }

    public void setData(List<BaseModel> datas) {
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

    public BindingRecyclerViewAdapter<Member> getMentionAdapter() {
        return mentionAdapter;
    }

    public void showBottomSheet() {
        plan8BottomSheetDialog.show();
    }

    public void uploadFile(View view) {
        Toast.makeText(getActivity().getApplicationContext(), "파일 업로드", Toast.LENGTH_SHORT).show();
    }

    public void sendComment(View view) {
        Toast.makeText(getActivity().getApplicationContext(), "메시지 전송", Toast.LENGTH_SHORT).show();
    }

    public void setAutoCompleteMentionData(List<Member> memberList) {
        this.memberList = memberList;
        if (null != memberList) {
            mentionAdapter.setData(memberList);
        }
        if (null == memberList
                || memberList.size() <= 0) {
            setEmptyMentionList(true);
        } else {
            setEmptyMentionList(false);
        }
    }

    public OnTextChangeListener getTextChangeListener() {
        if (null == onTextChangeListener) {

            onTextChangeListener = new OnTextChangeListener() {
                @Override
                public void onChange(EditText editText, CharSequence charSequence, int charIndex, boolean isBackpress) {
                    currentText = editText.getText().toString();

                    if (isBackpress) {
                        currentTextIndex = charIndex - 1;
                    } else {
                        currentTextIndex = charIndex;
                    }
                    if (currentText.length() > 0) {
                        setActiveSendBtn(true);
                    } else {
                        setActiveSendBtn(false);
                    }

                    if (currentText.length() <= 0
                            || !currentText.contains("@")
                            || editText.getText().toString().substring(editText.getText().length() - 1).equals(" ")) {
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

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public void replaceToMention(Member member) {
        setMemberList(null);
        setEmptyMentionList(true);

        int index = 0;
        for (int i = currentTextIndex; i >= 0; i--) {
            Log.e("test", "" + index);
            if (currentText.charAt(i) == '@') {
                index = i;
                break;
            }
        }
        setCurrentText(currentText.substring(0, index) + "@" + member.getUsername() + " ");
        setSelection();
    }

    @Bindable
    public int getTextLength() {
        return 0;
    }

    public void setSelection() {
        notifyPropertyChanged(BR.textLength);
    }

    @Override
    public void onClick(View view) {
        plan8BottomSheetDialog.hide();
        if (view.getId() == R.id.bottomSheetFirstItem){
            ((DetailReservationActivity) getActivity()).editReservationStatus(Constants.RESERVATION_STATUS_COMPLETE);
        } else if (view.getId() == R.id.bottomSheetSecondItem) {
            ((DetailReservationActivity) getActivity()).editReservationStatus(Constants.RESERVATION_STATUS_INCOMPLETE);
        } else {
            ((DetailReservationActivity) getActivity()).editReservationStatus(Constants.RESERVATION_STATUS_CANCELED);
        }
    }

    @Bindable
    public boolean getLoadingFlag() {
        return loadingFlag;
    }

    public void setLoadingFlag(boolean flag) {
        loadingFlag = flag;
        notifyPropertyChanged(BR.loadingFlag);
    }
}
