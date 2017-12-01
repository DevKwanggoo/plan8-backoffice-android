package io.plan8.backoffice.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.linkedin.android.spyglass.suggestions.SuggestionsResult;
import com.linkedin.android.spyglass.suggestions.interfaces.SuggestionsResultListener;
import com.linkedin.android.spyglass.suggestions.interfaces.SuggestionsVisibilityManager;
import com.linkedin.android.spyglass.tokenization.QueryToken;
import com.linkedin.android.spyglass.tokenization.impl.WordTokenizer;
import com.linkedin.android.spyglass.tokenization.impl.WordTokenizerConfig;
import com.linkedin.android.spyglass.tokenization.interfaces.QueryTokenReceiver;
import com.linkedin.android.spyglass.ui.MentionsEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.ActivityDetailTaskBinding;
import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.model.item.Comment;
import io.plan8.backoffice.model.item.DetailTaskMoreButtonItem;
import io.plan8.backoffice.model.item.TaskItem;
import io.plan8.backoffice.vm.DetailTaskActivityVM;

public class DetailTaskActivity extends BaseActivity implements SuggestionsResultListener {
    private ActivityDetailTaskBinding binding;
    private DetailTaskActivityVM vm;
    private TaskItem taskItem;
    private MentionsEditText mentionsEditText;
    private User.UserLoader user;
    private static final String BUCKET = "user";
    private static final WordTokenizerConfig tokenizerConfig = new WordTokenizerConfig
            .Builder()
            .setMaxNumKeywords(1)
//            .setWordBreakChars("@")
//            .setExplicitChars("")
//            .setMaxNumKeywords(2)
//            .setThreshold(1)
            .build();

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

        List<User> testUserList = new ArrayList<>();
        testUserList.add(new User("조광환", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("김철호", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("조영규", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("이해찬", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("김형규", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("조광환1", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("김철호1", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("조영규1", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("이해찬1", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("김형규1", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("조광환2", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("김철호2", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("조영규2", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("이해찬2", "http://i.imgur.com/DvpvklR.png"));
        testUserList.add(new User("김형규2", "http://i.imgur.com/DvpvklR.png"));
        user = new User.UserLoader(testUserList);

        mentionsEditText = findViewById(R.id.mentionEditText);
        mentionsEditText.setTokenizer(new WordTokenizer(tokenizerConfig));
        mentionsEditText.setQueryTokenReceiver(new QueryTokenReceiver() {
            @Override
            public List<String> onQueryReceived(@NonNull QueryToken queryToken) {
                List<String> buckets = Arrays.asList(BUCKET);
                List<User> suggestions = user.getSuggestions(queryToken);
                SuggestionsResult result = new SuggestionsResult(queryToken, suggestions);
                // Have suggestions, now call the listener (which is this activity)
                onReceiveSuggestionsResult(result, BUCKET);
                return buckets;
            }
        });
        mentionsEditText.setSuggestionsVisibilityManager(new SuggestionsVisibilityManager() {
            @Override
            public void displaySuggestions(boolean display) {

            }

            @Override
            public boolean isDisplayingSuggestions() {
                return false;
            }
        });
    }

    @Override
    public void onReceiveSuggestionsResult(@NonNull SuggestionsResult result, @NonNull String bucket) {
        List<User> userList = (List<User>) result.getSuggestions();
        vm.setAutoCompleteMentionData(userList);
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

    public void replaceToMention(User user) {
        vm.setUserList(null);
        vm.setCurrentText(vm.getCurrentText() + " (" + user.getName() + ") ");
    }
}