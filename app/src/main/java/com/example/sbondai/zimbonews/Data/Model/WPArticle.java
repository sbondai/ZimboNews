package com.example.sbondai.zimbonews.Data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WPArticle {

    @SerializedName("featured_image_urls")
    @Expose
    private FeaturedImageUrls featuredImageUrls;
    private Integer id;
    private Title title;
    private Excerpt excerpt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Excerpt getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(Excerpt excerpt) {
        this.excerpt = excerpt;
    }

    public FeaturedImageUrls getFeaturedImageUrls() {
        return featuredImageUrls;
    }

    public void setFeaturedImageUrls(FeaturedImageUrls featuredImageUrls) {
        this.featuredImageUrls = featuredImageUrls;
    }
}


