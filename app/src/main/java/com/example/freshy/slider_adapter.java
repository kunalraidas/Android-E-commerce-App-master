package com.example.freshy;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class slider_adapter extends PagerAdapter
{
    private List<slider_model> slider_modelList;

    public slider_adapter(List<slider_model> slider_modelList) {
        this.slider_modelList = slider_modelList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout,container,false);
        ConstraintLayout bannercontainer = view.findViewById(R.id.banner_container);
        bannercontainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(slider_modelList.get(position).getBackgroundcolor())));
        ImageView banner = view.findViewById(R.id.banner_slide);
        Glide.with(container.getContext()).load(slider_modelList.get(position).getBanner()).apply(new RequestOptions().placeholder(R.drawable.home)).into(banner);
        container.addView(view,0);
        return view;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return slider_modelList.size();
    }
}
