package io.plan8.backoffice.util;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.IOException;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.activity.NetworkExceptionActivity;
import io.plan8.backoffice.exception.NoConnectionNetworkException;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SSozi on 2017. 12. 20..
 */

public class AutoFillHeaderInterceptor extends ConnectivityInterceptor {
    private Context context;

    public AutoFillHeaderInterceptor(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtil.getInstance().checkInternetConnection(context)) {
            Intent networkExceptionIntent = NetworkExceptionActivity.buildIntent(context);
            networkExceptionIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(networkExceptionIntent);
            throw new NoConnectionNetworkException();
        }

        Request.Builder builder = chain.request().newBuilder();
        String token = SharedPreferenceManager.getInstance().getUserToken(context);
        if (null != token && !token.equals("")) {
            builder.addHeader("authorization", "Bearer " + token);
        }

        Request request = builder.build();
        Response response = chain.proceed(request);

        if (response.code() == 401) {
            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "로그인 정보가 만료되었습니다. 다시 로그인 해 주세요.", Toast.LENGTH_SHORT).show();
                    ApplicationManager.getInstance().logout();
                }
            }, 0);
            throw new IOException();
        } else if (response.code() == 403) {
            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "요청하신 페이지의 접근권한이 없습니다.", Toast.LENGTH_SHORT).show();
                    ApplicationManager.getInstance().logout();
                }
            }, 0);
            throw new IOException();
        }
        return response;
    }
}