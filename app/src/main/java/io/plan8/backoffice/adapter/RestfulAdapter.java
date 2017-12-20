package io.plan8.backoffice.adapter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.api.ApiService;
import io.plan8.backoffice.util.ConnectivityInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class RestfulAdapter {
    private static volatile RestfulAdapter instance = null;
    private ApiService newApiService;
    private Context context;

    public RestfulAdapter() {
    }

    public RestfulAdapter(Context context) {
        this.context = context;
    }

    public static RestfulAdapter getInstance() {
        if (null == instance) {
            synchronized (RestfulAdapter.class) {
                instance = new RestfulAdapter();
            }
        }
        return instance;
    }

    public ApiService getServiceApi() {

        if (newApiService == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(new ConnectivityInterceptor(context)).build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();

            /**
             *  api - sandbox
             */
//                //Retrofit 설정
//                newApiService = new Retrofit.Builder()
//                        .baseUrl(Constants.API_SANDBOX_URL)
//                        .client(client)
//                        .addConverterFactory(GsonConverterFactory.create(gson))
//                        .build().create(ApiService.class); //인터페이스 연결

            /******
             *  api - production
             */
            //Retrofit 설정
            newApiService = new Retrofit.Builder()
                    .baseUrl(ApplicationManager.getInstance().getServerUrl())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build().create(ApiService.class); //인터페이스 연결

            return newApiService;
        } else {
            return newApiService;
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
