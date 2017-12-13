package io.plan8.backoffice.vm;

import android.app.Activity;
import android.databinding.Bindable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.DetailReservationActivity;
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter;
import io.plan8.backoffice.dialog.Plan8BottomSheetDialog;
import io.plan8.backoffice.listener.OnTextChangeListener;
import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.model.api.Action;
import io.plan8.backoffice.model.api.Reservation;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.model.item.DetailReservationMoreButtonItem;
import io.plan8.backoffice.vm.item.DetailReservationActionFileItemVM;
import io.plan8.backoffice.vm.item.DetailReservationActionItemVM;
import io.plan8.backoffice.vm.item.DetailReservationActionReplaceItemVM;
import io.plan8.backoffice.vm.item.DetailReservationHeaderItemVM;
import io.plan8.backoffice.vm.item.DetailReservationMoreButtonItemVM;
import io.plan8.backoffice.vm.item.MentionItemVM;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class DetailReservationActivityVM extends ActivityVM implements View.OnClickListener {
    private BindingRecyclerViewAdapter<BaseModel> adapter;
    private BindingRecyclerViewAdapter<User> mentionAdapter;
    private List<User> userList;
    private Plan8BottomSheetDialog plan8BottomSheetDialog;
    private Plan8BottomSheetDialog fileUploadBottomSheet;
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
                } else if (data instanceof Action) {
                    if (null != ((Action) data).getText()) {
                        return R.layout.item_detail_reservation_action;
                    } else if (null != ((Action) data).getData()
                            && null != ((Action) data).getData().getBefore()
                            && null != ((Action) data).getData().getAfter()) {
                        return R.layout.item_detail_reservation_action_replace;
                    } else if (null != ((Action) data).getAttachment()
                            || null != ((Action) data).getAttachment().getUrl()) {
                        return R.layout.item_detail_reservation_action_file;
                    } else {
                        return R.layout.item_detail_reservation_empty;
                    }
                } else {
                    return R.layout.item_detail_reservation_more_button;
                }
            }

            @Override
            protected void bindVariables(ViewDataBinding binding, BaseModel data) {
                if (data instanceof Reservation) {
                    binding.setVariable(BR.vm, new DetailReservationHeaderItemVM(getActivity(), savedInstanceState, (Reservation) data));
                } else if (data instanceof Action) {
                    if (null != ((Action) data).getText()) {
                        binding.setVariable(BR.vm, new DetailReservationActionItemVM(getActivity(), savedInstanceState, (Action) data));
                    } else if (null != ((Action) data).getData()
                            && null != ((Action) data).getData().getBefore()
                            && null != ((Action) data).getData().getAfter()) {
                        binding.setVariable(BR.vm, new DetailReservationActionReplaceItemVM(getActivity(), savedInstanceState, (Action) data));
                    } else if (null != ((Action) data).getAttachment()
                            || null != ((Action) data).getAttachment().getUrl()) {
                        binding.setVariable(BR.vm, new DetailReservationActionFileItemVM(getActivity(), savedInstanceState, (Action) data));
                    }
                } else {
                    binding.setVariable(BR.vm, new DetailReservationMoreButtonItemVM(getActivity(), savedInstanceState, (DetailReservationMoreButtonItem) data));
                }
            }
        };

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
        plan8BottomSheetDialog = new Plan8BottomSheetDialog(getActivity());
        plan8BottomSheetDialog.setFirstItem("완료");
        plan8BottomSheetDialog.getFirstItem().setOnClickListener(this);
        plan8BottomSheetDialog.setSecondItem("대기");
        plan8BottomSheetDialog.getSecondItem().setOnClickListener(this);
        plan8BottomSheetDialog.setThirdItem("취소됨");
        plan8BottomSheetDialog.getThirdItem().setOnClickListener(this);

        fileUploadBottomSheet = new Plan8BottomSheetDialog(getActivity());
        fileUploadBottomSheet.setFirstItem(R.drawable.ic_line_camera, "카메라에서 가져오기");
        fileUploadBottomSheet.getFirstItem().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileUploadBottomSheet.hide();
                ((DetailReservationActivity)getActivity()).pickImageForCamera();
            }
        });
        fileUploadBottomSheet.setSecondItem(R.drawable.ic_line_file, "파일매니저에서 가져오기");
        fileUploadBottomSheet.getSecondItem().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileUploadBottomSheet.hide();
                ((DetailReservationActivity)getActivity()).pickFileForFileManager();
            }
        });
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
        getActivity().onBackPressed();
        getActivity().overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
    }

    public void setData(List<BaseModel> datas) {
        adapter.setData(datas);
    }

    public void addDatas(List<BaseModel> data, int startIndex, int dataSize) {
        adapter.addData(data, startIndex, dataSize);
    }

    public void addData(BaseModel data) {
        adapter.addData(data);
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
        plan8BottomSheetDialog.show();
    }

    public void showFileBottomSheet() {
        fileUploadBottomSheet.show();
    }

    public void uploadFile(View view) {
        ((DetailReservationActivity) getActivity()).checkPermission();
    }

    public void sendComment(View view) {
        if (getActivity() instanceof DetailReservationActivity) {
            if (null != currentText && currentText.length() > 0) {
                ((DetailReservationActivity) getActivity()).sendAction(currentText);
            }
        }
    }

    public void setAutoCompleteMentionData(List<User> userList) {
        this.userList = userList;
        if (null != userList) {
            mentionAdapter.setData(userList);
        }
        if (null == userList
                || userList.size() <= 0) {
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void replaceToMention(User user) {
        setUserList(null);
        setEmptyMentionList(true);

        int index = 0;
        for (int i = currentTextIndex; i >= 0; i--) {
            if (currentText.charAt(i) == '@') {
                index = i;
                break;
            }
        }
        setCurrentText(currentText.substring(0, index) + "@" + user.getUsername() + " ");
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
        if (view.getId() == R.id.bottomSheetFirstItem) {
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
