package com.alpyuktug.covid_19.Fragments;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alpyuktug.covid_19.Adapters.RecylerViewAdapterNews;
import com.alpyuktug.covid_19.Models.News;
import com.alpyuktug.covid_19.R;
import com.alpyuktug.covid_19.Services.ApiUtils;
import com.alpyuktug.covid_19.Services.AppDAOInerface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNews extends Fragment {

    public FragmentNews() {
    }

    private AppDAOInerface countriesDIF;
    private List<News> NewsLists;

    public RecyclerView RecylerViewNews;
    public RecylerViewAdapterNews NewsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        RecylerViewNews = (RecyclerView) view.findViewById(R.id.RecylerViewNews);
        RecylerViewNews.setHasFixedSize(true);
        RecylerViewNews.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        countriesDIF = ApiUtils.getCountriesDAOInerface();
        AllNews();

        return view;
    }

    private void AllNews() {
        if(getString(R.string.language).contains("TR"))
        {
            countriesDIF.GetNewsListTR().enqueue(new Callback<com.alpyuktug.covid_19.Models.NewsList>() {
                @Override
                public void onResponse(Call<com.alpyuktug.covid_19.Models.NewsList> call, Response<com.alpyuktug.covid_19.Models.NewsList> response) {
                    NewsLists = response.body().getNews();

                    NewsAdapter = new RecylerViewAdapterNews(NewsLists, getActivity());
                    RecylerViewNews.setAdapter(NewsAdapter);
                }

                @Override
                public void onFailure(Call<com.alpyuktug.covid_19.Models.NewsList> call, Throwable t) {

                }
            });
        }
        else
        {
            countriesDIF.GetNewsListTR().enqueue(new Callback<com.alpyuktug.covid_19.Models.NewsList>() {
                @Override
                public void onResponse(Call<com.alpyuktug.covid_19.Models.NewsList> call, Response<com.alpyuktug.covid_19.Models.NewsList> response) {
                    NewsLists = response.body().getNews();

                    NewsAdapter = new RecylerViewAdapterNews(NewsLists, getActivity());
                    RecylerViewNews.setAdapter(NewsAdapter);
                }

                @Override
                public void onFailure(Call<com.alpyuktug.covid_19.Models.NewsList> call, Throwable t) {

                }
            });
        }
    }

}
