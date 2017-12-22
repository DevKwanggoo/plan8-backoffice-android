package io.plan8.backoffice.util;

import android.content.Context;
import android.content.Intent;

import java.io.IOException;

import io.plan8.backoffice.activity.NetworkExceptionActivity;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SSozi on 2017. 12. 20..
 */

public class ConnectivityInterceptor implements Interceptor{
    private Context mContext;

    public ConnectivityInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtil.getInstance().checkInternetConnection(mContext)) {
            Intent networkExceptionIntent = NetworkExceptionActivity.buildIntent(mContext);
            networkExceptionIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(networkExceptionIntent);
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
