package com.alpyuktug.covid_19.Fragments;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alpyuktug.covid_19.Adapters.RecylerViewAdapterImages;
import com.alpyuktug.covid_19.Models.Images;
import com.alpyuktug.covid_19.Models.ImageList;
import com.alpyuktug.covid_19.R;
import com.alpyuktug.covid_19.Services.ApiUtils;
import com.alpyuktug.covid_19.Services.AppDAOInerface;
import com.bluejamesbond.text.DocumentView;

import java.util.List;

import me.relex.circleindicator.CircleIndicator2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMeasures extends Fragment {

    public FragmentMeasures() {
    }

    private AppDAOInerface countriesDIF;
    private List<Images> imagesList;

    public RecyclerView RecylerViewImages;
    public RecylerViewAdapterImages ImagesAdapter;

    public CircleIndicator2 RecylerViewImagesIndicator;

    public DocumentView DocumentViewAbout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measures, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DocumentViewAbout = view.findViewById(R.id.DocumentViewAbout);
        DocumentViewAbout.setText(getContext().getResources().getString(R.string.About));

        RecylerViewImages =  (RecyclerView) view.findViewById(R.id.RecylerViewImages);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecylerViewImages.setLayoutManager(linearLayoutManager);
        RecylerViewImages.setHasFixedSize(true);

        RecylerViewImagesIndicator = view.findViewById(R.id.indicator);

        countriesDIF = ApiUtils.getCountriesDAOInerface();
        AllImage();

        return view;
    }

    private void AllImage() {
        if(getString(R.string.language).contains("TR"))
        {
            countriesDIF.GetImageListEN().enqueue(new Callback<ImageList>() {
                @Override
                public void onResponse(Call<ImageList> call, Response<com.alpyuktug.covid_19.Models.ImageList> response) {

                    imagesList = response.body().getImages();

                    ImagesAdapter = new RecylerViewAdapterImages(imagesList, getActivity());
                    RecylerViewImages.setAdapter(ImagesAdapter);

                    PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
                    pagerSnapHelper.attachToRecyclerView(RecylerViewImages);
                    RecylerViewImagesIndicator.attachToRecyclerView(RecylerViewImages, pagerSnapHelper);
                    ImagesAdapter.registerAdapterDataObserver(RecylerViewImagesIndicator.getAdapterDataObserver());
                }

                @Override
                public void onFailure(Call<com.alpyuktug.covid_19.Models.ImageList> call, Throwable t) {

                }


            });
        }
        else
        {
            countriesDIF.GetImageListEN().enqueue(new Callback<ImageList>() {
                @Override
                public void onResponse(Call<ImageList> call, Response<com.alpyuktug.covid_19.Models.ImageList> response) {

                    imagesList = response.body().getImages();

                    ImagesAdapter = new RecylerViewAdapterImages(imagesList, getActivity());
                    RecylerViewImages.setAdapter(ImagesAdapter);
                }

                @Override
                public void onFailure(Call<com.alpyuktug.covid_19.Models.ImageList> call, Throwable t) {

                }


            });
        }

    }

}

