package com.example.freshy;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyWishlistFragment extends Fragment {


    public MyWishlistFragment() {
        // Required empty public constructor
    }

    private RecyclerView wishlistRecyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_wishlist, container, false);

        wishlistRecyclerview = view.findViewById(R.id.my_wishlist_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        wishlistRecyclerview.setLayoutManager(linearLayoutManager);

        List<WishlistModel> wishlistModelList = new ArrayList<>();
        wishlistModelList.add(new WishlistModel(R.drawable.chocolate,"Cadbury Dairy Milk",1, "3",145,"Rs.599/-","Rs.699/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.chocolate,"Cadbury Dairy Milk",0, "3",145,"Rs.599/-","Rs.699/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.chocolate,"Cadbury Dairy Milk",1, "5",145,"Rs.599/-","Rs.699/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.chocolate,"Cadbury Dairy Milk",4, "1",145,"Rs.599/-","Rs.699/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.chocolate,"Cadbury Dairy Milk",2, "2",145,"Rs.599/-","Rs.699/-","Cash on delivery"));

        WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlistModelList,true);
        wishlistRecyclerview.setAdapter(wishlistAdapter);
        wishlistAdapter.notifyDataSetChanged();

        return view;
    }

}
