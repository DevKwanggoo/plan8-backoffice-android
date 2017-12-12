package io.plan8.backoffice.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by SSozi on 2017. 11. 30..
 */

public class FileDownloader extends AsyncTask<String, Void, Void> {
    private String fileName;
    private final String SAVE_FOLDER = "/plan8-download";
    private Activity activity;
    private String mimeType;

    public FileDownloader(Activity activity, String mimeType) {
        this.activity = activity;
        this.mimeType = mimeType;
    }

    @Override
    protected Void doInBackground(String... params) {
        //다운로드 경로를 지정
        String savePath = Environment.getExternalStorageDirectory().toString() + SAVE_FOLDER;
        File dir = new File(savePath);

        //상위 디렉토리가 존재하지 않을 경우 생성
        if (!dir.exists()) {
            dir.mkdirs();
        }

        //파일 이름 :날짜_시간
        Date day = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA);
        fileName = String.valueOf(sdf.format(day));

        //웹 서버 쪽 파일이 있는 경로
        String fileUrl = params[0];

        //다운로드 폴더에 동일한 파일명이 존재하는지 확인
        if (!new File(savePath + "/" + fileName).exists()) {

        } else {

        }

        String localPath = savePath + "/" + fileName + "." + mimeType;

        try {
            URL imgUrl = new URL(fileUrl);

            //서버와 접속하는 클라이언트 객체 생성
            HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
            int len = conn.getContentLength();
            byte[] tmpByte = new byte[len];

            //입력 스트림을 구한다
            InputStream is = conn.getInputStream();
            File file = new File(localPath);

            //파일 저장 스트림 생성
            FileOutputStream fos = new FileOutputStream(file);
            int read;

            //입력 스트림을 파일로 저장
            for (; ; ) {
                read = is.read(tmpByte);
                if (read <= 0) {
                    break;
                }
                fos.write(tmpByte, 0, read); //file 생성
            }

            is.close();
            fos.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (mimeType.contains("jpg") || mimeType.contains("png") || mimeType.contains("mpeg")){
            String targetDir = Environment.getExternalStorageDirectory().toString() + SAVE_FOLDER;
            File file = new File(targetDir + "/" + fileName + ".jpg");

            //이미지 스캔해서 갤러리 업데이트
            activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        }

        Toast.makeText(activity, "다운로드 완료", Toast.LENGTH_SHORT).show();
    }
}
