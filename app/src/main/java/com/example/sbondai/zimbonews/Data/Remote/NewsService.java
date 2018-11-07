package com.example.sbondai.zimbonews.Data.Remote;

import com.example.sbondai.zimbonews.Data.Model.WPArticle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NewsService {

    @GET("posts/")
    Call<List<WPArticle>> getArticles();

    @GET("posts/")
    Call<List<WPArticle>> getArticles(@Query("categories") int catId, @Query("page") int page);

}
