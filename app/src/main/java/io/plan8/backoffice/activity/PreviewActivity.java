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
import io.plan8.backoffice.model.api.Attachment;
import io.plan8.backoffice.util.FileDownloader;
import io.plan8.backoffice.vm.PreviewActivityVM;

public class PreviewActivity extends AppCompatActivity {
    private ActivityPreviewBinding binding;
    private PreviewActivityVM vm;
    private Attachment attachment;

    public static Intent buildIntent(Context context, Attachment attachment) {
        Intent intent = new Intent(context, PreviewActivity.class);
        intent.putExtra("attachment", attachment);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        attachment = (Attachment) getIntent().getSerializableExtra("attachment");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_preview);
        vm = new PreviewActivityVM(this, savedInstanceState, attachment);
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
        new FileDownloader(this, "jpg").execute(attachment.getUrl());
//        new FileDownloader(this, "pdf").execute("http://javacan.tistory.com/attachment/cfile1.uf@246A424C57222FCB1F9229.pdf");
    }

    public void saveRemoteFile(InputStream is, OutputStream os) throws IOException {
        int c = 0;
        while ((c = is.read()) != -1)
            os.write(c);
        os.flush();
    }
}
