package com.falcon.quoteoftheday.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.falcon.quoteoftheday.network.QuoteApiClient;
import com.falcon.quoteoftheday.network.response.QuoteResponse;
import com.falcon.quoteoftheday.pojos.Quote;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    public MutableLiveData<Quote> getQuoteOfTheDay() {
        final MutableLiveData<Quote> liveData = new MutableLiveData<>();

        Call<QuoteResponse> quoteResponseCall = QuoteApiClient.getInstance()
                .getQuoteApi().getQuoteOfTheDay();
        quoteResponseCall.enqueue(new Callback<QuoteResponse>() {
            @Override
            public void onResponse(Call<QuoteResponse> call, Response<QuoteResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    if (response.body() != null) {
                        if (response.body().getContents() != null) {
                            if (response.body().getContents().getQuotes() != null &&
                                    !response.body().getContents().getQuotes().isEmpty()) {
                                liveData.setValue(response.body().getContents().getQuotes().get(0));
                            } else {
                                liveData.setValue(getErrorQuote("No data!"));
                            }
                        } else {
                            liveData.setValue(getErrorQuote("No data!"));
                        }
                    } else {
                        liveData.setValue(getErrorQuote("Error retrieving data!"));
                    }
                } else {
                    liveData.setValue(getErrorQuote("Request failed!"));
                }
            }

            @Override
            public void onFailure(Call<QuoteResponse> call, Throwable t) {
                liveData.setValue(getErrorQuote("Request failed!"));
            }
        });

        return liveData;
    }

    private Quote getErrorQuote(String error) {
        Quote quote = new Quote();
        quote.setError(error);

        return quote;
    }
}
