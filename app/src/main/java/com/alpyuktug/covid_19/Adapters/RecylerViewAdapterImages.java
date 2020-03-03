package com.alpyuktug.covid_19.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.alpyuktug.covid_19.Models.Images;
import com.alpyuktug.covid_19.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecylerViewAdapterImages extends RecyclerView.Adapter<RecylerViewAdapterImages.ImagesViewHolder>{

    Context context;
    private List<Images> imagesList;

    public RecylerViewAdapterImages(List<Images> imagesList, Context context) {
        this.imagesList = imagesList;
        this.context = context;
    }

    @Override
    public RecylerViewAdapterImages.ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_cardview, parent, false);
        RecylerViewAdapterImages.ImagesViewHolder gvh = new RecylerViewAdapterImages.ImagesViewHolder(v);
        return gvh;

    }

    public void ImagesDelete(int position) {
        imagesList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(RecylerViewAdapterImages.ImagesViewHolder holder, final int position) {

        String Image = imagesList.get(position).getImageURL();

        Glide.with(context).load(Image).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImagesViewHolder(View view) {
            super(view);
            imageView=view.findViewById(R.id.imageView);
        }
    }
}

