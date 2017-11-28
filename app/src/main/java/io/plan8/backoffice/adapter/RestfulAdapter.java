package io.plan8.backoffice.adapter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.plan8.backoffice.api.ApiService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class RestfulAdapter {
    private static RestfulAdapter instance;
    private static ApiService retrofitServiceApi;
    private static ApiService upLoadFileApiService;
    private static ApiService newApiService;
    private Context context;

    public RestfulAdapter() {
    }

    public RestfulAdapter(Context context) {
        this.context = context;
    }

    public static RestfulAdapter getInstance() {
        return instance;
    }

    public static synchronized RestfulAdapter build(Context context) {
        if (instance == null) instance = new RestfulAdapter(context);
        return instance;
    }

    public ApiService getServiceApi() {

        if (newApiService == null) {
            OkHttpClient client = new OkHttpClient.Builder().build();

            Gson gson = new GsonBuilder()
                    .setLenient()
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
                    .baseUrl(Constants.API_SERVICE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build().create(ApiService.class); //인터페이스 연결

            return newApiService;
        } else {
            return newApiService;
        }
    }
}
