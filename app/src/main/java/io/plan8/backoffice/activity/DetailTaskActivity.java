package io.plan8.backoffice.activity;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.MimeTypeMap;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.databinding.ActivityDetailTaskBinding;
import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.model.api.Upload;
import io.plan8.backoffice.model.item.CommentFile;
import io.plan8.backoffice.model.item.CommentReplaceItem;
import io.plan8.backoffice.model.item.Comment;
import io.plan8.backoffice.model.item.DetailTaskMoreButtonItem;
import io.plan8.backoffice.model.item.TaskItem;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.vm.DetailTaskActivityVM;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTaskActivity extends BaseActivity implements SuggestionsResultListener {
    private ActivityDetailTaskBinding binding;
    private DetailTaskActivityVM vm;
    private Uri captureImageUri;
    private long fileLength;
    private TaskItem taskItem;
    private MentionsEditText mentionsEditText;
    private User.UserLoader user;
    private static final String BUCKET = "user";
    private boolean isAlreadyReplaceMention;
    private static final WordTokenizerConfig tokenizerConfig = new WordTokenizerConfig
            .Builder()
            .setMaxNumKeywords(1)
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
        testData.add(new Comment("이주석", "@조광환 댓글입니당동해물과백두산이\n댓글요댓 @김형규 글입니당동해물과백두산이\n그래요 댓글입니 @웅엉랑링 당동해물과백두산이댓글", "3일 전"));
        testData.add(new Comment("이주석", "댓글입니당동해물과백두산이\n댓글요댓글입니당동해물과백두산이\n그래요 댓글입니당동해물과백두산이댓글", "3일 전"));
        testData.add(new Comment("이주석", "댓글입니당동해물과백두산이\n댓글요댓글입니당동해물과백두산이\n그래요 댓글입니당동해물과백두산이댓글", "3일 전"));

        testData.add(new CommentFile("일주석", "zip", "3일 전", "filefilefile"));
        testData.add(new CommentFile("이주석", "7z", "2일 전", "file"));
        testData.add(new CommentFile("삼주석", "image", "2일 전", "file"));
        testData.add(new CommentReplaceItem("삼주석", "작업일자", "2분 전"));
        testData.add(new CommentFile("사주석", "doc", "1일 전", "filefilefilefilefile"));

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
        if (isAlreadyReplaceMention) {
            return;
        }
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
        Intent mainIntent = MainActivity.buildIntent(getApplicationContext());
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainIntent);
        finish();
        overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
    }

    public void showBottomSheet() {
        if (null != vm) {
            vm.showBottomSheet();
        }
    }

    public void pickImageForCamera() {
        Intent i = new Intent();
        i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        captureImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "task_" + new DateUtil().getCurrentDate() + ".jpg"));
        i.putExtra(MediaStore.EXTRA_OUTPUT, captureImageUri);
        startActivityForResult(i, Constants.PICK_IMAGE_CODE);
    }

    public void pickFileForFileManager() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, Constants.SELECT_FILE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.PICK_IMAGE_CODE) {
                callFileUpload(captureImageUri);
            } else if (requestCode == Constants.SELECT_FILE_CODE) {
                callFileUpload(data.getData());
            }
        }
    }

    public void callFileUpload(Uri data) {
        String absolutePath = getRealPathFromURI(getApplicationContext(), data);

        File file = null;

        if (absolutePath != null) {
            file = new File(absolutePath);
        }

        if (!Uri.fromFile(file).toString().contains("/cache/") && absolutePath != null) {
            MimeTypeMap type = MimeTypeMap.getSingleton();
            String extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
            String mime = type.getMimeTypeFromExtension(extension);

            RequestBody requestBody = RequestBody.create(MediaType.parse(mime), file);

            fileLength = file.length();

            MultipartBody.Part body = MultipartBody.Part.createFormData("multipart/form-data", file.getName(), requestBody);

            Call<List<Upload>> uploadCall = RestfulAdapter.getInstance().getServiceApi().postUpload("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getApplicationContext()), body);
            uploadCall.enqueue(new Callback<List<Upload>>() {
                @Override
                public void onResponse(Call<List<Upload>> call, Response<List<Upload>> response) {
                    Log.e("responese : ", response.body().get(0).getUrl());
                }

                @Override
                public void onFailure(Call<List<Upload>> call, Throwable t) {
                    Log.e("failure : ", t.getMessage());
                }
            });
        }
    }

    public String getRealPathFromURI(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                } // TODO handle non-primary volumes
            }// DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } // MediaProvider
            else if (isMediaDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        } // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }

    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
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
        isAlreadyReplaceMention = true;
        vm.replaceToMention(user);
        isAlreadyReplaceMention = false;
    }
}