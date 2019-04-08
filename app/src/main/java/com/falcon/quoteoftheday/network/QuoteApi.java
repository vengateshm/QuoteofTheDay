package com.falcon.quoteoftheday.network;

import com.falcon.quoteoftheday.network.response.QuoteResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteApi {
    @GET("qod.json")
    Call<QuoteResponse> getQuoteOfTheDay();
}
