package com.example.waleed.latestnews;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.waleed.latestnews.Common.Common;
import com.example.waleed.latestnews.Interface.NewsService;
import com.example.waleed.latestnews.Model.Article;
import com.example.waleed.latestnews.Model.News;
import com.example.waleed.latestnews.adapter.ListNewsAdapter;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListNews extends AppCompatActivity {
    KenBurnsView  kbv;
    DiagonalLayout diagonal;
    NewsService mservice;
    AlertDialog dialog;


    TextView top_author,top_title;
    SwipeRefreshLayout swipeRefreshLayout;
    String source="",sortBy="",webHotUrl="";
    ListNewsAdapter adapter;
    RecyclerView listnews;
    RecyclerView.LayoutManager layoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        //service
        mservice= Common.getNewsService();
       dialog=new SpotsDialog(this);
        //view
        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews(source,true);
            }
        });
        diagonal=(DiagonalLayout) findViewById(R.id.Diagonal_layout);
        diagonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lataest news toread
                Intent detail=new Intent(getBaseContext(),DetailArticle.class);
                detail.putExtra("weburl",webHotUrl);
                startActivity(detail);


            }
        });
        kbv=(KenBurnsView)findViewById(R.id.top_image);
        top_title=(TextView)findViewById(R.id.top_title);
        top_author=(TextView)findViewById(R.id.topauthor);
        listnews=(RecyclerView)findViewById(R.id.list_News);
        listnews.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        listnews.setLayoutManager(layoutManager);


        //intent
        if (getIntent()!=null){
            source=getIntent().getStringExtra("source");
            if (!source.isEmpty()){
                loadNews(source,true);


            }


        }


    }

    private void loadNews(String source, boolean isRefreshed) {
       if (!isRefreshed)
       {
           dialog.show();
           mservice.getNewestArticles(Common.getApiUrl(source,Common.Api_KEY))
           .enqueue(new Callback<News>() {
               @Override
               public void onResponse(Call<News> call, Response<News> response) {
                   dialog.dismiss();
                   Picasso.with(getBaseContext()).load(response.body().getArticles().get(0).getUrltoimage()).into(kbv);
                   top_title.setText(response.body().getArticles().get(0).getTitle());
                   top_author.setText(response.body().getArticles().get(0).getAuthor());
                   webHotUrl=response.body().getArticles().get(0).getUrl();
                   //load remain article
                   List<Article>removeFirstitem=response.body().getArticles();
                   removeFirstitem.remove(0);
                   adapter=new ListNewsAdapter(removeFirstitem,getBaseContext());
                   adapter.notifyDataSetChanged();
                   listnews.setAdapter(adapter);




               }


               @Override
               public void onFailure(Call<News> call, Throwable t) {

               }
           });



       }
       else {
           dialog.show();
           mservice.getNewestArticles(Common.getApiUrl(source,Common.Api_KEY))
                   .enqueue(new Callback<News>() {
                       @Override
                       public void onResponse(Call<News> call, Response<News> response) {
                           dialog.dismiss();
                           Picasso.with(getBaseContext()).load(response.body().getArticles().get(0).getUrltoimage()).into(kbv);
                           top_title.setText(response.body().getArticles().get(0).getTitle());
                           top_author.setText(response.body().getArticles().get(0).getAuthor());
                           webHotUrl=response.body().getArticles().get(0).getUrl();
                           //load remain article
                           List<Article>removeFirstitem=response.body().getArticles();
                           removeFirstitem.remove(0);
                           adapter=new ListNewsAdapter(removeFirstitem,getBaseContext());
                           adapter.notifyDataSetChanged();
                           listnews.setAdapter(adapter);




                       }


                       @Override
                       public void onFailure(Call<News> call, Throwable t) {

                       }
                   });
           swipeRefreshLayout.setRefreshing(false);


       }
    }
}
