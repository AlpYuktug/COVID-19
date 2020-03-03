package com.alpyuktug.covid_19.Fragments;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alpyuktug.covid_19.Adapters.RecylerViewAdapterImages;
import com.alpyuktug.covid_19.Models.Images;
import com.alpyuktug.covid_19.Models.ImageList;
import com.alpyuktug.covid_19.R;
import com.alpyuktug.covid_19.Services.ApiUtils;
import com.alpyuktug.covid_19.Services.AppDAOInerface;

import java.util.List;

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

        RecylerViewImages = (RecyclerView) view.findViewById(R.id.RecylerViewImages);
        RecylerViewImages.setHasFixedSize(true);
        RecylerViewImages.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

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
                }

                @Override
                public void onFailure(Call<com.alpyuktug.covid_19.Models.ImageList> call, Throwable t) {

                }


            });
        }

    }

}

