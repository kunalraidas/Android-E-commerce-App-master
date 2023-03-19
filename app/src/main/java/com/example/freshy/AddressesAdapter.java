package com.example.freshy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.freshy.DeliverActivity.SELECT_ADDRESS;
import static com.example.freshy.MyAccountFragment.MANAGE_ADDRESS;
import static com.example.freshy.MyAddressesActivity.refreshItem;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.Viewholder> {

    private List<AddresesModel> addresesModelList;

    private int MODE;

    private int preSelectedPosition = -1;

    public AddressesAdapter(List<AddresesModel> addresesModelList,int MODE) {
        this.addresesModelList = addresesModelList;
        this.MODE = MODE;
    }

    @NonNull
    @Override
    public AddressesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.addresses_item_layout,viewGroup,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressesAdapter.Viewholder viewholder, int position) {

        String name = addresesModelList.get(position).getFullname();
        String address = addresesModelList.get(position).getAddress();
        String pincode = addresesModelList.get(position).getPincode();
        Boolean selected = addresesModelList.get(position).getSelected();
        viewholder.setData(name,address,pincode,selected,position);

    }

    @Override
    public int getItemCount() {
        return addresesModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView fullname;
        private TextView address;
        private TextView pincode;
        private ImageView icon;
        private LinearLayout optionContainer;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            fullname = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            pincode = itemView.findViewById(R.id.pincode);
            icon = itemView.findViewById(R.id.icon_view);
            optionContainer = itemView.findViewById(R.id.option_container);
        }

        private void setData(String username, String userAddress, String userPincode, Boolean selected, final int position)
        {
           fullname.setText(username);
           address.setText(userAddress);
           pincode.setText(userPincode);

           if(MODE == SELECT_ADDRESS)
           {
               icon.setImageResource(R.drawable.tick);
               if(selected)
               {
                   icon.setVisibility(View.VISIBLE);
                   preSelectedPosition = position;
               }
               else
               {
                   icon.setVisibility(View.GONE);
               }
               itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       if (preSelectedPosition != position) {
                           addresesModelList.get(position).setSelected(true);
                           addresesModelList.get(preSelectedPosition).setSelected(false);
                           refreshItem(preSelectedPosition, position);
                           preSelectedPosition = position;
                       }
                   }
               });
           }
           else if(MODE == MANAGE_ADDRESS)
           {
                optionContainer.setVisibility(View.GONE);
                icon.setImageResource(R.drawable.more_dots);
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        optionContainer.setVisibility(View.VISIBLE);
                        refreshItem(preSelectedPosition,preSelectedPosition);
                        preSelectedPosition = position;
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        refreshItem(preSelectedPosition,preSelectedPosition);
                        preSelectedPosition = -1;
                    }
                });
           }
        }
    }
}
