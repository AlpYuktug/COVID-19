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

import com.alpyuktug.covid_19.Activities.MainActivity;
import com.alpyuktug.covid_19.Models.Covid19Country;
import com.alpyuktug.covid_19.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecylerViewAdapterCountry extends RecyclerView.Adapter<RecylerViewAdapterCountry.CountryViewHolder>{

    Context context;
    private List<Covid19Country> CountryList;

    public RecylerViewAdapterCountry(List<Covid19Country> CountryList, Context context) {
        this.CountryList = CountryList;
        this.context = context;
    }

    @Override
    public RecylerViewAdapterCountry.CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_cardview, parent, false);

        RecylerViewAdapterCountry.CountryViewHolder gvh = new RecylerViewAdapterCountry.CountryViewHolder(v);
        return gvh;
    }

    public void CountryDelete(int position) {
        CountryList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(RecylerViewAdapterCountry.CountryViewHolder holder, final int position) {

        holder.textViewName.setText(CountryList.get(position).getCountryName());
        holder.textViewTotal.setText(String.valueOf(CountryList.get(position).getCountryVirusCount()));
        holder.textViewDead.setText(String.valueOf(CountryList.get(position).getCountryVirusDeadCount()));
        holder.textViewRecover.setText(String.valueOf(CountryList.get(position).getCountryVirusRecoveredCount()));

    }

    @Override
    public int getItemCount() {
        return CountryList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName,textViewTotal,textViewDead,textViewRecover;

        public CountryViewHolder(View view) {
            super(view);
            textViewName=view.findViewById(R.id.textViewName);
            textViewTotal=view.findViewById(R.id.textViewTotal);
            textViewDead=view.findViewById(R.id.textViewDead);
            textViewRecover=view.findViewById(R.id.textViewRecover);
        }
    }
}
