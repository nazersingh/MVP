package com.example.saini.mvp.manager.network;

import android.content.Context;
import android.util.Log;


import com.example.saini.mvp.BuildConfig;
import com.example.saini.mvp.interfaces.ApiInterface;
import com.example.saini.mvp.util.PreferenceHandler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nazer on 9/16/2017.
 */

public class RetrofitClient {

    public static final String mBaseUrl="";

    public static ApiInterface getClient(final Context context)
    {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ConnectivityInterceptor(context))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String token= PreferenceHandler.readString(context,PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
                        Request request = chain.request().newBuilder()
                                .addHeader("x-logintoken", token)
                                .build();
                        if (BuildConfig.DEBUG) {
                            Log.e(getClass().getName(),request.headers()+""+request.body()+""+ request.method() + " " + request.url());
                            Log.e(getClass().getName(), "" + request.header("Cookie"));
                            RequestBody rb = request.body();
                            Buffer buffer = new Buffer();
                            if (rb != null)
                                rb.writeTo(buffer);
                            Log.e(getClass().getName(), "Payload- " + buffer.readUtf8());
                        }
                        return chain.proceed(request);
                    }
                })
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

       return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface.class);

    }
}
