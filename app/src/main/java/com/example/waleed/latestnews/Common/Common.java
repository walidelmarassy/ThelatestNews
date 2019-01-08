package com.example.waleed.latestnews.Common;

import com.example.waleed.latestnews.Interface.IconBetterIdeaServices;
import com.example.waleed.latestnews.Interface.NewsService;
import com.example.waleed.latestnews.Remote.IconBetterideaclient;
import com.example.waleed.latestnews.Remote.RetrofitClient;

public class Common {
    private static final String BASE_URL="https://newsapi.org/";
    public static final String Api_KEY="2b74a34f798c460aad5302783bc345e9";
    public static NewsService getNewsService(){
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);



    }







    public static String getApiUrl(String source, String apiKEY){
        StringBuilder apiUrl=new StringBuilder("https://newsapi.org/v2/top-headlines?sources=");
      return  apiUrl.append(source).append("&apiKey=").append(apiKEY).toString();


    }



}
