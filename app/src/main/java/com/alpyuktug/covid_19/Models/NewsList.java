
package com.alpyuktug.covid_19.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsList {

    @SerializedName("News")
    @Expose
    private List<News> news = null;

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

}
