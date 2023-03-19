package com.example.freshy;

import java.util.List;

public class HomePageModel

{
    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;


    private int type;
    private String Backgroundcolor;

    //////////////////////////////////////////////////////////////////////////banner slider
    private List<slider_model> sliderModelList;

    public HomePageModel(int type, List<slider_model> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<slider_model> getSliderModelList() {
        return sliderModelList;
    }

    public void setSliderModelList(List<slider_model> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }
    ///////////////////////////////////////////////////////////////////////////banner slider

    /////////////strip ad

    private String resource;


    public HomePageModel(int type, String resource, String backgroundcolor) {
        this.type = type;
        this.resource = resource;
        Backgroundcolor = backgroundcolor;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getBackgroundcolor() {
        return Backgroundcolor;
    }

    public void setBackgroundcolor(String backgroundcolor) {
        Backgroundcolor = backgroundcolor;
    }
    /////////////strip ad

    /////////////////horizontal

    private String title;
    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HomePageModel(int type, String title,String BackgroundColor, List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.type = type;
        this.title = title;
        this.Backgroundcolor = BackgroundColor;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalProductScrollModel> getHorizontalProductScrollModelList() {
        return horizontalProductScrollModelList;
    }

    public void setHorizontalProductScrollModelList(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }
    /////////////////horizontal

}
