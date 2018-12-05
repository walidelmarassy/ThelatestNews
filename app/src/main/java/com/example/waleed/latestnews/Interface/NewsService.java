package com.example.waleed.latestnews.Interface;

import android.support.v7.widget.CardView;

import com.example.waleed.latestnews.Common.Common;
import com.example.waleed.latestnews.Model.News;
import com.example.waleed.latestnews.Model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewsService {
    @GET("v2/sources?language=en&apiKey="+ Common.Api_KEY)//need to update
    Call<WebSite>getSources();
    @GET
    Call<News>getNewestArticles(@Url String url);

}
