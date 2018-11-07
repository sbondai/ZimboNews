package com.example.sbondai.zimbonews;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.sbondai.zimbonews.Adapter.ZimboNewsAdapter;
import com.example.sbondai.zimbonews.Data.Model.WPArticle;
import com.example.sbondai.zimbonews.Data.Remote.NewsService;
import com.example.sbondai.zimbonews.Data.Remote.RetrofitClient;
import com.example.sbondai.zimbonews.Data.Utility.NewsUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ZimboNewsAdapter adapter;
    private NewsService mService;
    private RecyclerView recyclerView;
    private Button btn_previous;
    private Button btn_next;
    ProgressDialog progressDialog;
    private static int nPage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = NewsUtils.getNewsService();

      //  NewsService mService2 = RetrofitClient.getRetrofitInstance().create(NewsService.class);
        //Call<List<WPArticle>> call = mService2.getArticles();

        /**Log the URL called*/
        //Log.wtf("URL Called", call.request().url() + "");

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        loadData( 6, 1);

    }

    private void loadData(int catId,int page) {

        if ( nPage <= 1 && page == -1){
            nPage = 1;
        }else {
            nPage += page;

        }


        Toast.makeText(MainActivity.this, " "+nPage, Toast.LENGTH_SHORT).show();

        mService.getArticles(catId,nPage).enqueue(new Callback<List<WPArticle>>() {

            @Override
            public void onResponse(Call<List<WPArticle>> call, Response<List<WPArticle>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {

                    getUserListData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<WPArticle>> call, Throwable t) {
                progressDialog.dismiss();
                Log.wtf("ERROR", t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getUserListData(List<WPArticle> wpArticles) {

        recyclerView = findViewById(R.id.news_recycler);
        adapter = new ZimboNewsAdapter(wpArticles, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_previous);

        btn_next.setOnClickListener( click -> {

          loadData(6,1);


        });

        btn_previous.setOnClickListener( click -> {

          loadData(6,-1);


        });

        //recyclerView.setHasFixedSize(true);

    }
}
