package com.example.waleed.latestnews;

import android.app.AlertDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.waleed.latestnews.Common.Common;
import com.example.waleed.latestnews.Interface.NewsService;
import com.example.waleed.latestnews.Model.WebSite;
import com.example.waleed.latestnews.adapter.ListSourceAdapter;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView Listwebsite;
    RecyclerView.LayoutManager layoutManager;
    NewsService mservice;
    ListSourceAdapter adapter;
   AlertDialog dialog;
    SwipeRefreshLayout swipelayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Init cache
        Paper.init(this);
        //Init services
        mservice= Common.getNewsService();
        //Init view
        swipelayout=(SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                LoadWebsitesource(true);

            }
        });
        Listwebsite=(RecyclerView)findViewById(R.id.List_Source);
        Listwebsite.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        Listwebsite.setLayoutManager(layoutManager);
        dialog= new SpotsDialog(this);
        LoadWebsitesource(false);
        }

    private void LoadWebsitesource(boolean isRefreshed) {
        if (!isRefreshed){
            String cache=Paper.book().read("cache");
            if (cache!=null&&!cache.isEmpty()&&!cache.equals("null")){
                WebSite webSite=new Gson().fromJson(cache,WebSite.class);//need to understand it
                adapter=new ListSourceAdapter(getBaseContext(),webSite);
                Listwebsite.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }
            else {//if not have cache
                dialog.show();

                //Fetch Newdata
                mservice.getSources().enqueue(new Callback<WebSite>() {
                    @Override
                    public void onResponse(Call<WebSite> call, Response<WebSite> response)
                    {
                        adapter=new ListSourceAdapter(getBaseContext(),response.body());
                        Listwebsite.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        Paper.book().write("cache",new Gson().toJson(response.body()));//save data in cach here
                        dialog.dismiss();


                    }

                    @Override
                    public void onFailure(Call<WebSite> call, Throwable t) {

                    }
                });




            }

        }
        else{ //if from swipe to refresh
            swipelayout.setRefreshing(true);
            //fetch new data
            mservice.getSources().enqueue(new Callback<WebSite>() {
                @Override
                public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                    adapter=new ListSourceAdapter(getBaseContext(),response.body());
                    Listwebsite.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    //save to cache
                    Paper.book().write("cache",new Gson().toJson(response.body()));
                    //dismiss refresh progressing
                    swipelayout.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<WebSite> call, Throwable t) {

                }
            });


            //save data in cach here


                }


        }

    }

