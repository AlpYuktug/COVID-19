package com.alpyuktug.covid_19.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alpyuktug.covid_19.Adapters.RecylerViewAdapterNews;
import com.alpyuktug.covid_19.Models.News;
import com.alpyuktug.covid_19.Models.NewsList;
import com.alpyuktug.covid_19.R;
import com.alpyuktug.covid_19.Services.ApiUtils;
import com.alpyuktug.covid_19.Services.AppDAOInerface;
import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.TextAlignment;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class FragmentNewsDetail extends Fragment {


    public FragmentNewsDetail() {
    }

    public ImageView imageViewNewsPic;
    public TextView textViewNewsLongHead;
    public DocumentView textViewNewsDetail;

    private AppDAOInerface countriesDIF;
    private List<News> NewsLists;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_details, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        textViewNewsLongHead = view.findViewById(R.id.textViewNewsLongHead);
        textViewNewsDetail = view.findViewById(R.id.textViewNewsDetail);
        imageViewNewsPic = view.findViewById(R.id.imageViewNewsPic);

        countriesDIF = ApiUtils.getCountriesDAOInerface();

        NewsDetail();
        return view;
    }

    private void NewsDetail() {
        if(getString(R.string.language).contains("TR"))
        {
            SharedPreferences shared_preferences = getContext().getSharedPreferences("NewsNumberStock",MODE_PRIVATE);
            String ClickNewsNumber = shared_preferences.getString("NewsNumber", "");

            countriesDIF.GetNewsListTRDetails(ClickNewsNumber).enqueue(new Callback<com.alpyuktug.covid_19.Models.NewsList>() {
                @Override
                public void onResponse(Call<com.alpyuktug.covid_19.Models.NewsList> call, Response<com.alpyuktug.covid_19.Models.NewsList> response) {

                    NewsLists = response.body().getNews();

                    for (News n: NewsLists)
                    {
                        Glide.with(getContext()).load(n.getNewsImage()).into(imageViewNewsPic);

                        textViewNewsLongHead.setText(n.getNewsHead());

                        textViewNewsDetail.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
                        textViewNewsDetail.setText(n.getNewsDetails());
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
