package com.example.waleed.latestnews.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IconBetterideaclient {
    private static Retrofit retrofit=null;
    public static Retrofit getClient(String baseUrl){


        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                           .baseUrl("https://icons.better-idea.org/").
                            addConverterFactory(GsonConverterFactory.create()).
                            build();




        }
        return retrofit;


    }
}
