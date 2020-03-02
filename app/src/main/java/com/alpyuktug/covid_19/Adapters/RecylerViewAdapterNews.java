package com.alpyuktug.covid_19.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.alpyuktug.covid_19.Models.News;
import com.alpyuktug.covid_19.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecylerViewAdapterNews extends RecyclerView.Adapter<RecylerViewAdapterNews.NewsViewHolder>{

    Context context;
    private List<News> NewsList;

    public RecylerViewAdapterNews(List<News> NewsList, Context context) {
        this.NewsList = NewsList;
        this.context = context;
    }

    @Override
    public RecylerViewAdapterNews.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_cardview, parent, false);
        RecylerViewAdapterNews.NewsViewHolder gvh = new RecylerViewAdapterNews.NewsViewHolder(v);
        return gvh;
    }

    public void NewsDelete(int position) {
        NewsList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(RecylerViewAdapterNews.NewsViewHolder holder, final int position) {

        holder.textViewNewsHead.setText(NewsList.get(position).getNewsHead());
        holder.textViewNewsNumber.setText(NewsList.get(position).getNewsNumber().toString());

        String Image = NewsList.get(position).getNewsImage();

        Glide.with(context).load(Image).into(holder.imageViewNewsImage);

        holder.imageViewNewsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref = context.getSharedPreferences("NewsNumberStock",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("NewsNumber",NewsList.get(position).getNewsNumber());
                editor.commit();

                /*
                Fragment fragment = new FragmentHaberDetay();
                FragmentManager fm = ((AnaMenu) context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.fragmenticerik, fragment);
                ft.commit();
                */

            }
        });

        holder.textViewNewsHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref = context.getSharedPreferences("HaberIDDepo",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("HaberNumara",NewsList.get(position).getNewsNumber());
                editor.commit();

                /*
                Fragment fragment = new FragmentHaberDetay();
                FragmentManager fm = ((AnaMenu) context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.fragmenticerik, fragment);
                ft.commit();

                 */
            }
        });
    }

    @Override
    public int getItemCount() {
        return NewsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewNewsImage;
        TextView textViewNewsNumber,textViewNewsHead;

        public NewsViewHolder(View view) {
            super(view);
            imageViewNewsImage=view.findViewById(R.id.imageViewNewsImage);
            textViewNewsHead=view.findViewById(R.id.textViewNewsHead);
            textViewNewsNumber=view.findViewById(R.id.textViewNewsNumber);
        }
    }
}
