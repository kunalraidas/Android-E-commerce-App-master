package com.example.freshy;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {


    public MyOrdersFragment() {
        // Required empty public constructor
    }

    private RecyclerView myOrdersRecyclerview;


    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        myOrdersRecyclerview = view.findViewById(R.id.my_orders_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrdersRecyclerview.setLayoutManager(layoutManager);

        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.foodgrain,2,"Basumati Chaal","Delivered on Monday,16 March 2019"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.foodgrain,4,"Basumati Chaal","Cancelled"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.foodgrain,5,"Basumati Chaal","Delivered on Monday,16 March 2019"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.foodgrain,2,"Basumati Chaal","Delivered on Monday,16 March 2019"));

        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myOrdersRecyclerview.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();


        return view;
    }

}
