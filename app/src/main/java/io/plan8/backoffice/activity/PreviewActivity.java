package io.plan8.backoffice.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.ActivityPreviewBinding;
import io.plan8.backoffice.util.FileDownloader;
import io.plan8.backoffice.vm.PreviewActivityVM;

public class PreviewActivity extends AppCompatActivity {
    private ActivityPreviewBinding binding;
    private PreviewActivityVM vm;
    private String imageUrl;

    public static Intent buildIntent(Context context, String imageUrl) {
        Intent intent = new Intent(context, PreviewActivity.class);
        intent.putExtra("imageUrl", imageUrl);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageUrl = getIntent().getStringExtra("imageUrl");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_preview);
        vm = new PreviewActivityVM(this, savedInstanceState, imageUrl);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        binding.previewDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileDownload();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
    }

    private void fileDownload() {
        Toast.makeText(getApplicationContext(), "다운로드 로직만구현해놈", Toast.LENGTH_SHORT).show();
//        new FileDownloader(this, "jpg").execute("http://www.city.kr/files/attach/images/1326/622/387/004/cb59682631ac9d64a0a188c7833fc359.jpg");

//        new FileDownloader(this, "pdf").execute("http://javacan.tistory.com/attachment/cfile1.uf@246A424C57222FCB1F9229.pdf");
    }

    public void saveRemoteFile(InputStream is, OutputStream os) throws IOException {
        int c = 0;
        while ((c = is.read()) != -1)
            os.write(c);
        os.flush();
    }
}
