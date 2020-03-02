
package com.alpyuktug.covid_19.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("NewsNumber")
    @Expose
    private String newsNumber;
    @SerializedName("NewsHead")
    @Expose
    private String newsHead;
    @SerializedName("NewsDetails")
    @Expose
    private String newsDetails;
    @SerializedName("NewsDate")
    @Expose
    private String newsDate;
    @SerializedName("NewsImage")
    @Expose
    private String newsImage;

    public News(String newsNumber, String newsHead, String newsImage) {
        this.newsNumber = newsNumber;
        this.newsHead = newsHead;
        this.newsImage = newsImage;
    }

    public String getNewsNumber() {
        return newsNumber;
    }

    public void setNewsNumber(String newsNumber) {
        this.newsNumber = newsNumber;
    }

    public String getNewsHead() {
        return newsHead;
    }

    public void setNewsHead(String newsHead) {
        this.newsHead = newsHead;
    }

    public String getNewsDetails() {
        return newsDetails;
    }

    public void setNewsDetails(String newsDetails) {
        this.newsDetails = newsDetails;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

}
