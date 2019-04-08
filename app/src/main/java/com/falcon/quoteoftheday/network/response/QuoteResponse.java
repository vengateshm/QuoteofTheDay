package com.falcon.quoteoftheday.network.response;

import com.falcon.quoteoftheday.pojos.Contents;
import com.falcon.quoteoftheday.pojos.Success;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuoteResponse {
    @SerializedName("success")
    @Expose
    private Success success;
    @SerializedName("contents")
    @Expose
    private Contents contents;
    private String error;
    private boolean status = true;

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
