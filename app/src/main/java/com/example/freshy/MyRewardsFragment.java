package com.example.freshy;


import android.annotation.SuppressLint;
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
public class MyRewardsFragment extends Fragment {


    public MyRewardsFragment() {
        // Required empty public constructor
    }

    private RecyclerView rewardsRecyclerview;


    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_rewards, container, false);

        rewardsRecyclerview = view.findViewById(R.id.my_rewards_recylerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rewardsRecyclerview.setLayoutManager(layoutManager);

        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("Cashback","Valid till 2nd July 2019","GET FLAT 50% OFF ON ORDERS ABOVE RS.5000/-"));
        rewardModelList.add(new RewardModel("Discount","Valid till 2nd July 2019","GET FLAT 50% OFF ON ORDERS ABOVE RS.5000/-"));
        rewardModelList.add(new RewardModel("Buy One get One","Valid till 2nd July 2019","GET FLAT 50% OFF ON ORDERS ABOVE RS.5000/-"));
        rewardModelList.add(new RewardModel("Cashback","Valid till 2nd July 2019","GET FLAT 50% OFF ON ORDERS ABOVE RS.5000/-"));
        rewardModelList.add(new RewardModel("Discount","Valid till 2nd July 2019","GET FLAT 50% OFF ON ORDERS ABOVE RS.5000/-"));
        rewardModelList.add(new RewardModel("Buy One get One","Valid till 2nd July 2019","GET FLAT 50% OFF ON ORDERS ABOVE RS.5000/-"));
        rewardModelList.add(new RewardModel("Cashback","Valid till 2nd July 2019","GET FLAT 50% OFF ON ORDERS ABOVE RS.5000/-"));
        rewardModelList.add(new RewardModel("Discount","Valid till 2nd July 2019","GET FLAT 50% OFF ON ORDERS ABOVE RS.5000/-"));
        rewardModelList.add(new RewardModel("Buy One get One","Valid till 2nd July 2019","GET FLAT 50% OFF ON ORDERS ABOVE RS.5000/-"));

        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardModelList,false);
        rewardsRecyclerview.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();

        return view;
    }

}
