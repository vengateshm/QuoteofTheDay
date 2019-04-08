package com.falcon.quoteoftheday.network;

import com.falcon.quoteoftheday.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuoteApiClient {
    private static final QuoteApiClient ourInstance = new QuoteApiClient();

    public static QuoteApiClient getInstance() {
        return ourInstance;
    }

    private static QuoteApi quoteApi;

    private static final String BASE_URL = "http://quotes.rest/";

    private QuoteApiClient() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();

        // Configure GSON
        Gson gson = new GsonBuilder().create();
        // Add gson converter factory
        builder.addConverterFactory(GsonConverterFactory.create(gson));

        // Building OkHttp client
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            // If only debug mode add logging interceptor
            // Configure logging interceptor
            HttpLoggingInterceptor loggingInterceptor =
                    new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY);

            clientBuilder.addInterceptor(loggingInterceptor);
        }

        Retrofit retrofit = builder.baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .build();

        quoteApi = retrofit.create(QuoteApi.class);
    }

    public QuoteApi getQuoteApi() {
        return quoteApi;
    }
}
