package com.example.sbondai.zimbonews.Data.Utility;

import com.example.sbondai.zimbonews.Data.Remote.NewsService;
import com.example.sbondai.zimbonews.Data.Remote.RetrofitClient;

public class NewsUtils {

    public static final String BASE_URL = "https://www.sardc.net/en/wp-json/wp/v2/";

    public static NewsService getNewsService() {
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }

}
