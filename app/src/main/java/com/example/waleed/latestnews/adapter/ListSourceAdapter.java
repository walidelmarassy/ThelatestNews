package com.example.waleed.latestnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.waleed.latestnews.Interface.ItemClickListener;
import com.example.waleed.latestnews.ListNews;
import com.example.waleed.latestnews.Model.WebSite;
import com.example.waleed.latestnews.R;

class listsourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    ItemClickListener itemClickListener;
    TextView Source_title;


    public listsourceViewHolder(View itemView) {
        super(itemView);
        Source_title=(TextView)itemView.findViewById(R.id.source_name);
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

public class  ListSourceAdapter extends RecyclerView.Adapter<listsourceViewHolder>{
    private Context context;
    private WebSite webSite;


    public ListSourceAdapter(Context context, WebSite webSite) {
        this.context = context;
        this.webSite = webSite;
    }

    @Override
    public listsourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemview=layoutInflater.inflate(R.layout.source_layout,parent,false);
        return new listsourceViewHolder(itemview);


    }

    @Override
    public void onBindViewHolder(final listsourceViewHolder holder, final int position) {

        holder.Source_title.setText(webSite.getSources().get(position).getName());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onclick(View view, int postion, boolean islongclick) {
                Intent intent=new Intent(context, ListNews.class);
                intent.putExtra("source",webSite.getSources().get(position).getId());
                context.startActivity(intent);



            }
        });





    }

    @Override
    public int getItemCount() {
        return webSite.getSources().size();

    }
}
