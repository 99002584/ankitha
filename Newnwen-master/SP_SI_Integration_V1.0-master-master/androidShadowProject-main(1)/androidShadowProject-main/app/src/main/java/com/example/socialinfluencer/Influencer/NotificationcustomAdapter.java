package com.example.socialinfluencer.Influencer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialinfluencer.R;

import java.util.ArrayList;
import java.util.List;


import static com.example.socialinfluencer.Influencer.ItemClass.card1;
import static com.example.socialinfluencer.Influencer.ItemClass.card;


public class NotificationcustomAdapter extends RecyclerView.Adapter {
    private List<ItemClass> itemClassList;
    int card1Position=0;
    int card2Position=0;
    public NotificationcustomAdapter(List<ItemClass> itemClassList) {
        this.itemClassList = itemClassList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case card:
                View layoutOne
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.influencernotificationcard1, parent,
                                false);
                return new cardViewHolder(layoutOne);
            case card1:
                View layoutTwo
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.influencernotificationcard2, parent,
                                false);
                return new card1ViewHolder(layoutTwo);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (itemClassList.get(position).getViewType()) {
            case card:
                String aname = itemClassList.get(position).getaName().get(card1Position);
//                String aCampaign = itemClassList.get(position).getaCampaign().get(card1Position);
                String aInfluencer = itemClassList.get(position).getaInfluencer().get(card1Position);
                String aPrice = itemClassList.get(position).getaPrice().get(card1Position);
                String aTime = itemClassList.get(position).getaTime().get(card1Position);
                ((cardViewHolder)holder).setViews(aname,aInfluencer,aPrice,aTime);
                card1Position+=1;
                break;
            case card1:
                String bname = itemClassList.get(position).getbName().get(card2Position);
//                String bCampaign = itemClassList.get(position).getbCampaign().get(card2Position);
                String bInfluencer = itemClassList.get(position).getbInfluencer().get(card2Position);
                ((card1ViewHolder)holder).setViews(bname,bInfluencer);
                card2Position+=1;
                break;
        }


    }


    public int getItemViewType(int position) {
        switch (itemClassList.get(position).getViewType()) {
            case 0:
                return card;
            case 1:
                return card1;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return itemClassList.size();
    }

    class cardViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView brand;
        TextView campaign;
        TextView price;
        TextView time;
        Button accept;
        Button reject;
        CardView card;

        public cardViewHolder(@NonNull View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.notificationName);
            brand = (TextView) v.findViewById(R.id.brandName);
//            campaign = (TextView) v.findViewById(R.id.campaignName);
            price = (TextView) v.findViewById(R.id.price);
            time = (TextView) v.findViewById(R.id.time);
            accept = (Button) v.findViewById(R.id.accept);
            reject = (Button) v.findViewById(R.id.reject);
            card = (CardView) v.findViewById(R.id.card);
        }

        private void setViews(String cName, String cInfluencer, String cPrice, String cTime) {
            name.setText((CharSequence) cName);
            brand.setText((CharSequence) cInfluencer);
//            campaign.setText((CharSequence) cCampaign);
            price.setText((CharSequence) cPrice);
            time.setText((CharSequence) cTime);

        }

    }


    class card1ViewHolder extends RecyclerView.ViewHolder {
        TextView namE;
        TextView branD ;
        TextView campaigN;
        CardView card1;

        public card1ViewHolder(@NonNull View v) {
            super(v);
            namE = (TextView) v.findViewById(R.id.NotificationName);
            branD = (TextView) v.findViewById(R.id.Brand);
//            campaigN = (TextView) v.findViewById(R.id.CampaignName);
            card1 = (CardView) v.findViewById(R.id.card);
        }

        private void setViews(String dName, String dInfluencer) {
            namE.setText((CharSequence) dName);
            branD.setText((CharSequence) dInfluencer);
//            campaigN.setText((CharSequence) dCampaign);
        }
    }

}


