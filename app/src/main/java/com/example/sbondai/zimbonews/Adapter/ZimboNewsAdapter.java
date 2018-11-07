package com.example.sbondai.zimbonews.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sbondai.zimbonews.Data.Model.WPArticle;
import com.example.sbondai.zimbonews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ZimboNewsAdapter extends RecyclerView.Adapter<ZimboNewsViewHolder> {

    private List<WPArticle> wpArticles;
    private Context context;

    public ZimboNewsAdapter(List<WPArticle> wpArticles, Context context) {
        this.wpArticles = wpArticles;
        this.context = context;
    }

    @NonNull
    @Override
    public ZimboNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.nacticle_layout, parent, false);
        return new ZimboNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ZimboNewsViewHolder holder, int position) {

        holder.title.setText(wpArticles.get(position).getTitle().getRendered());
        holder.excerpt.setText(stripHtml(wpArticles.get(position).getExcerpt().getRendered()));

       Picasso.get().load(wpArticles.get(position).getFeaturedImageUrls().getThumbnail()).into(holder.featured_image);

    }

    @Override
    public int getItemCount() {
        return wpArticles.size();
    }

    public void updateAnswers(List<WPArticle> articles) {
        wpArticles = articles;
        notifyDataSetChanged();
    }

    private WPArticle getArticlePos(int adapterPosition) {
        return wpArticles.get(adapterPosition);
    }

    public static String stripHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(html).toString();
        }
    }



}

class ZimboNewsViewHolder extends RecyclerView.ViewHolder {

    TextView title, excerpt;
    ImageView featured_image;

    public ZimboNewsViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.txt_title);
        excerpt = itemView.findViewById(R.id.txt_excerpt);
        featured_image = itemView.findViewById(R.id.featured_image);
    }



}