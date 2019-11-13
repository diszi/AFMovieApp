package d2.hu.afmovieapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie{

    @SerializedName("budget")
    @Expose
    String budget;
    @SerializedName("homepage")
    @Expose
    String homepage;
    @SerializedName("original_title")
    String original_title;

    Movie(String budget,String homepage)
    {
        this.homepage = homepage;
        this.budget=budget;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }
}
