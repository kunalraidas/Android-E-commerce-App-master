package com.example.freshy;

public class slider_model
{
    private String banner;
    private String backgroundcolor;

    public slider_model(String banner, String backgroundcolor) {
        this.banner = banner;
        this.backgroundcolor = backgroundcolor;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getBackgroundcolor() {
        return backgroundcolor;
    }

    public void setBackgroundcolor(String backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }
}
