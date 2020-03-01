package com.alpyuktug.covid_19.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.alpyuktug.covid_19.Models.News;
import com.alpyuktug.covid_19.R;
import com.alpyuktug.covid_19.Services.ApiUtils;
import com.alpyuktug.covid_19.Services.AppDAOInerface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNews extends Fragment {

    public FragmentNews() {
    }

    private AppDAOInerface countriesDIF;
    private List<News> NewsLists;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

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

                    for (News n: NewsLists)
                    {
                        Log.e("News Number",n.getNewsNumber());
                    }
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

                }

                @Override
                public void onFailure(Call<com.alpyuktug.covid_19.Models.NewsList> call, Throwable t) {

                }
            });
        }
    }

}
