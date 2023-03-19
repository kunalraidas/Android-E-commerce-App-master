package com.example.freshy;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.String;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import static android.graphics.Color.*;

public class HomePageAdapter extends RecyclerView.Adapter {
    private List<HomePageModel> homePageModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;

            case 1:
                return HomePageModel.STRIP_AD_BANNER;

            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;

            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;


            default:
                return -1;


        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View BannerSliderView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sliding_ad_banner, viewGroup, false);
                return new BannerSliderViewHolder(BannerSliderView);

            case HomePageModel.STRIP_AD_BANNER:
                View StripAdView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.strip_ad_layout, viewGroup, false);
                return new StripAdBannerViewHolder(StripAdView);

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalProductView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontalscroll_layout, viewGroup, false);
                return new horizontalproductviewholder(horizontalProductView);

            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridProductView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_product_layout, viewGroup, false);
                return new gridproductviewholder(gridProductView);

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewholder, int position) {
        switch (homePageModelList.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<slider_model> slider_modelList = homePageModelList.get(position).getSliderModelList();
                ((BannerSliderViewHolder) viewholder).setBannersliderViewPager(slider_modelList);
                break;

            case HomePageModel.STRIP_AD_BANNER:
                String resource = homePageModelList.get(position).getResource();
                String color = homePageModelList.get(position).getBackgroundcolor();
                ((StripAdBannerViewHolder) viewholder).setStripAd(resource, color);
                break;

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String layoutcolor = homePageModelList.get(position).getBackgroundcolor();
                String horizontalLayouttitle = homePageModelList.get(position).getTitle();
                List<HorizontalProductScrollModel> horizontalProductScrollModelList = homePageModelList.get(position).getHorizontalProductScrollModelList();
                ((horizontalproductviewholder) viewholder).sethorizontalproductlayout(horizontalProductScrollModelList,horizontalLayouttitle,layoutcolor);
                break;

            case HomePageModel.GRID_PRODUCT_VIEW:
                String GridLayoutColor = homePageModelList.get(position).getBackgroundcolor();
                String GridLayouttitle = homePageModelList.get(position).getTitle();
                List<HorizontalProductScrollModel> gridProductScrollModelList = homePageModelList.get(position).getHorizontalProductScrollModelList();
                ((gridproductviewholder)viewholder).setGridLayout(gridProductScrollModelList,GridLayouttitle,GridLayoutColor);
                break;


            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {

        private ViewPager bannersliderViewPager;
        private int currentpage;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        private List<slider_model> arrangedList;

        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannersliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);


        }

        private void setBannersliderViewPager(final List<slider_model> sliderModelList) {
            currentpage = 2;
            if(timer != null){
                timer.cancel();
            }
            arrangedList = new ArrayList<>();
            for(int x=0;x < sliderModelList.size();x++){
                arrangedList.add(x,sliderModelList.get(x));
            }
            arrangedList.add(0,sliderModelList.get(sliderModelList.size()-2));
            arrangedList.add(1,sliderModelList.get(sliderModelList.size()-1));
            arrangedList.add(sliderModelList.get(0));
            arrangedList.add(sliderModelList.get(1));

            slider_adapter sliderAdapter = new slider_adapter(arrangedList);
            bannersliderViewPager.setAdapter(sliderAdapter);
            bannersliderViewPager.setClipToPadding(false);
            bannersliderViewPager.setPageMargin(20);

            bannersliderViewPager.setCurrentItem(currentpage);

            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentpage = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(arrangedList);
                    }
                }
            };
            bannersliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startbannerslideshow(arrangedList);
            bannersliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    pageLooper(arrangedList);
                    stopbannerslideshow();
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        startbannerslideshow(arrangedList);
                    }
                    return false;
                }
            });
        }

        private void pageLooper(List<slider_model> sliderModelList) {
            if (currentpage == sliderModelList.size() - 2) {
                currentpage = 2;
                bannersliderViewPager.setCurrentItem(currentpage, false);
            }
            if (currentpage == 1) {
                currentpage = sliderModelList.size() - 3;
                bannersliderViewPager.setCurrentItem(currentpage, false);
            }
        }

        private void startbannerslideshow(final List<slider_model> sliderModelList) {
            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentpage >= sliderModelList.size()) {
                        currentpage = 1;
                    }
                    bannersliderViewPager.setCurrentItem(currentpage++, true);
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopbannerslideshow() {
            timer.cancel();
        }
    }

    public class StripAdBannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView stripAdImage;
        private ConstraintLayout stripAdContainer;

        public StripAdBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            stripAdImage = itemView.findViewById(R.id.strip_ad_image);
            stripAdContainer = itemView.findViewById(R.id.strip_ad_container);

        }

        private void setStripAd(String resource, String color) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.home)).into(stripAdImage);
            stripAdContainer.setBackgroundColor(parseColor(color));
        }
    }
    public class horizontalproductviewholder extends RecyclerView.ViewHolder{

        private ConstraintLayout container;
        private TextView horizontalLayoutTitle;
        private Button horizontalViewAllBtn;
        private RecyclerView horizontalRecyclerView;

        public horizontalproductviewholder(@NonNull View itemView) {
            super(itemView);

            container= itemView.findViewById(R.id.container);
            horizontalLayoutTitle = itemView.findViewById(R.id.horizontal_scroll_layout_title);
            horizontalViewAllBtn = itemView.findViewById(R.id.horizontal_scroll_view_all_button);
            horizontalRecyclerView = itemView.findViewById(R.id.horizontal_scroll_layout_recycler_view);
            horizontalRecyclerView.setRecycledViewPool(recycledViewPool);

        }
        private void sethorizontalproductlayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList,String title,String color)
        {
//            container.setBackgroundTintList(ColorStateList.valueOf(parseColor(color)));
            horizontalLayoutTitle.setText(title);
            if(horizontalProductScrollModelList.size() > 8)
            {
                horizontalViewAllBtn.setVisibility(View.VISIBLE);
                horizontalViewAllBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent viewAllIntent = new Intent(itemView.getContext(),ViewAllActivity.class);
                        viewAllIntent.putExtra("layout_code",0);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            }
            else
            {
                horizontalViewAllBtn.setVisibility(View.INVISIBLE);
            }

            HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalRecyclerView.setLayoutManager(linearLayoutManager);
            horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
            horizontalProductScrollAdapter.notifyDataSetChanged();
        }
    }
    public class gridproductviewholder extends RecyclerView.ViewHolder
    {
        private TextView gridLayoutTitle;
        private Button gridLayoutViewAllButton;
        private GridLayout gridProductLayout;
        private ConstraintLayout container;

        public gridproductviewholder(@NonNull View itemView) {

            super(itemView);
             gridLayoutTitle = itemView.findViewById(R.id.grid_product_layout_title);
             gridLayoutViewAllButton = itemView.findViewById(R.id.grid_product_layout_viewall_btn);
             gridProductLayout = itemView.findViewById(R.id.grid_layout);
             container = itemView.findViewById(R.id.container);
        }
        private void setGridLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList,String title,String color)
        {
            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
            gridLayoutTitle.setText(title);


            for (int x=0;x<4;x++)
            {
                ImageView productImage = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_product_image);
                TextView productTitle = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_product_title);
                TextView productDescription = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_product_description);
                TextView productPrice = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_product_price);

                Glide.with(itemView.getContext()).load(horizontalProductScrollModelList.get(x)).apply(new RequestOptions().placeholder(R.drawable.home)).into(productImage);
                productTitle.setText(horizontalProductScrollModelList.get(x).getProductTitle());
                productDescription.setText(horizontalProductScrollModelList.get(x).getProductDescription());
                productPrice.setText(horizontalProductScrollModelList.get(x).getProductPrice());
                gridProductLayout.getChildAt(x).setBackgroundColor(parseColor("#ffffff"));

                gridProductLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });
            }


            gridLayoutViewAllButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent viewAllIntent = new Intent(itemView.getContext(),ViewAllActivity.class);
                    viewAllIntent.putExtra("layout_code",1);
                    itemView.getContext().startActivity(viewAllIntent);
                }
            });
        }
    }

}
