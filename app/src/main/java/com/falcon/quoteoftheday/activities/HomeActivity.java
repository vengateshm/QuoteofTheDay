package com.falcon.quoteoftheday.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.falcon.quoteoftheday.R;
import com.falcon.quoteoftheday.databinding.ActivityHomeBinding;
import com.falcon.quoteoftheday.pojos.Quote;
import com.falcon.quoteoftheday.utils.Utils;
import com.falcon.quoteoftheday.viewModels.HomeViewModel;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        if (!Utils.isConnected(this)) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
            return;
        }

        homeViewModel.getQuoteOfTheDay().observe(
                this, new Observer<Quote>() {
                    @Override
                    public void onChanged(@Nullable Quote quote) {
                        /*quote.setQuote("If I work as hard as I can, I wonder how much I can do in a day?");
                        quote.setAuthor("Ezra Taft Benson");
                        quote.setBackground("https://theysaidso.com/img/bgs/man_on_the_mountain.jpg");*/
                        setQuote(quote);
                    }
                }
        );
    }

    private void setQuote(Quote quote) {
        if (quote.getError() != null) {
            Toast.makeText(this, quote.getError(), Toast.LENGTH_SHORT).show();
        } else {
            quote.setQuote("If I work as hard as I can, I wonder how much I can do in a day?");
            quote.setAuthor("Ezra Taft Benson");
            quote.setBackground("https://theysaidso.com/img/bgs/man_on_the_mountain.jpg");
            binding.setQuote(quote);
        }
    }
}
