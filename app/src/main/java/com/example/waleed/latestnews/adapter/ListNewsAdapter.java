package com.example.waleed.latestnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.waleed.latestnews.Common.ISO8601Parse;
import com.example.waleed.latestnews.DetailArticle;
import com.example.waleed.latestnews.Interface.ItemClickListener;
import com.example.waleed.latestnews.ListNews;
import com.example.waleed.latestnews.Model.Article;
import com.example.waleed.latestnews.R;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

class ListNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ItemClickListener itemClickListener;
    RelativeTimeTextView article_time;
    CircleImageView article_image;
    TextView articletitle;





    public ListNewsViewHolder(View itemView) {

        super(itemView);
        articletitle=(TextView)itemView.findViewById(R.id.article_title);
        article_time=(RelativeTimeTextView)itemView.findViewById(R.id.article_time);
        article_image=(CircleImageView)itemView.findViewById(R.id.article_image);
        itemView.setOnClickListener(this);

        }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onclick(view,getAdapterPosition(),false);

    }
}

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsViewHolder> {
    private List<Article>articleList;
    private Context context;
    public ListNewsAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;



    }





    @Override
    public ListNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemview=layoutInflater.inflate(R.layout.news_layout,parent,false);
        return new ListNewsViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ListNewsViewHolder holder, final int position) {

        Picasso.with(context).load(articleList.get(position).getUrltoimage()).into(holder.article_image);
        if (articleList.get(position).getTitle().length()>65)// what is the meaning of >65
            holder.articletitle.setText(articleList.get(position).getTitle().substring(0,65)+"...");
        else {
            holder.articletitle.setText(articleList.get(position).getTitle());
            Date date=null;
            try {
                date= ISO8601Parse.parse(articleList.get(position).getPublishAt());


            }catch (ParseException ex)
            {
                ex.printStackTrace();

            }
            holder.article_time.setReferenceTime(date.getTime());
            //setEventClick
            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onclick(View view, int postion, boolean islongclick) { //click for every article
                    Intent detail=new Intent(context,DetailArticle.class);
                    detail.putExtra("weburl",articleList.get(position).getUrl());

                    context.startActivity(detail);

                }
            });





        }



    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}