package org.jiumao.common.AsynHttp;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;


public class DoNothingListener implements FutureCallback<HttpResponse> {

    private HttpResponse result;


    @Override
    public void completed(HttpResponse result) {
        this.result = result;
    }


    @Override
    public void failed(Exception ex) {
    }


    @Override
    public void cancelled() {
    }


    public HttpResponse getResult() {
        return result;
    }


    public void setResult(HttpResponse result) {
        this.result = result;
    }

}
