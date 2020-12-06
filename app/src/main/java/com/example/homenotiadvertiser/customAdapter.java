package com.example.homenotiadvertiser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder>{


        ArrayList<String> cname;
        ArrayList<String> cpricetxt;
        ArrayList<String> cprice;
        ArrayList<String> cfollowerstxt;
        ArrayList<String> cinstafollowers;
        ArrayList<String> cysubscriberstxt;
        ArrayList<String> cyoutubesubscribers;
        ArrayList<String> cffollowerstxt;
        ArrayList<String> cfbfollowers;
        ArrayList<String> cCategory;
        ArrayList<String> cCat1;
        ArrayList<String> cCat2;

        Integer[] arr;
        Integer[] arr1;
        Integer[] arr2;
        Integer[] arr3;

        Context context;

        public customAdapter(ArrayList<String> cname, ArrayList<String> cpricetxt, ArrayList<String> cCategory, ArrayList<String> cysubscriberstxt, ArrayList<String> cfollowerstxt, ArrayList<String> cffollowerstxt, ArrayList<String> cprice, Integer[] arr, Context context) {
            this.cname = cname;
            this.cpricetxt = cpricetxt;
            this.cprice = cprice;
            this.cCategory = cCategory;
            this.cCat1 = cCat1;
            this.cCat1 = cCat1;
            this.cfollowerstxt = cfollowerstxt;
            this.cinstafollowers= cinstafollowers;
            this.cysubscriberstxt = cysubscriberstxt;
            this.cyoutubesubscribers = cyoutubesubscribers;
            this.cffollowerstxt = cffollowerstxt;
            this.cfbfollowers = cfbfollowers;
            this.arr = arr;
            this.arr = arr1;
            this.arr = arr2;
            this.arr = arr3;
            this.context = context;
        }

        @NonNull
        @Override
        public customAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
            MyViewHolder vh= new MyViewHolder(v);
            return vh;
        }


        @Override
        public void onBindViewHolder(@NonNull customAdapter.MyViewHolder holder, int position) {
            TextView hname=holder.iname;
            TextView hpricetxt=holder.ipricetext;
            TextView hprice=holder.iprice;
            TextView hfollowerstxt=holder.ifollowerstxt;
            TextView hinstafollowers=holder.instafollowers;
            TextView hffollowerstxt=holder.ffollowerstxt;
            TextView hfbfollowers=holder.fbfollowers;
            TextView hyoutubesubscribers=holder.youtubesubscribers;
            TextView hysubscriberstxt=holder.ysubscriberstxt;
            TextView hcategory=holder.icat;
            TextView hcat1=holder.icat1;
            TextView hcat2=holder.icat2;

            Button hvp=holder.vp;
            Button hrq=holder.rq;

            ImageView himage=holder.image;
            CardView cam_card=holder.card;
            cam_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment influencerDetails=new InfluencerDetails();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, influencerDetails).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();
                }
            });
            hvp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment campaign=new InfluencerDetails();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();
                }
            });

            hname.setText(cname.get(position));
            hpricetxt.setText(cpricetxt.get(position));
            hprice.setText(cprice.get(position));
            hcategory.setText(cCategory.get(position));
            hcat1.setText(cCat1.get(position));
            hcat2.setText(cCat2.get(position));

            hfollowerstxt.setText(cfollowerstxt.get(position));
            hinstafollowers.setText(cinstafollowers.get(position));
            hyoutubesubscribers.setText(cyoutubesubscribers.get(position));
            hysubscriberstxt.setText(cysubscriberstxt.get(position));
            hfbfollowers.setText(cfbfollowers.get(position));
            hffollowerstxt.setText(cffollowerstxt.get(position));

            himage.setImageResource(arr[position]);
            himage.setImageResource(arr1[position]);
            himage.setImageResource(arr2[position]);
            himage.setImageResource(arr3[position]);
            //button on click
            //card on click
        }

        @Override
        public int getItemCount() {
            return cname.size();

        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView iname;
            TextView ipricetext;
            TextView iprice;
            TextView ifollowerstxt;
            TextView instafollowers;
            TextView ysubscriberstxt;
            TextView youtubesubscribers;
            TextView ffollowerstxt;
            TextView fbfollowers;
            TextView icat;
            TextView icat1;
            TextView icat2;

            Button vp;
            Button rq;

            ImageView image;
            ImageView instaimg;
            ImageView ytimg;
            ImageView fbimg;

            CardView card;

            public MyViewHolder(View v) {
                super(v);
                iname=(TextView) v.findViewById(R.id.name);
                ipricetext=(TextView) v.findViewById(R.id.pricetxt);
                iprice=(TextView) v.findViewById(R.id.price);
                icat=(TextView) v.findViewById(R.id.categories);
                icat1=(TextView) v.findViewById(R.id.cat1);
                icat2=(TextView) v.findViewById(R.id.cat2);
                ifollowerstxt=(TextView) v.findViewById(R.id.ifollowers);
               ysubscriberstxt=(TextView) v.findViewById(R.id.ysubscribers);
                ffollowerstxt=(TextView) v.findViewById(R.id.ffollowers);
                instafollowers=(TextView) v.findViewById(R.id.insta);
                youtubesubscribers=(TextView) v.findViewById(R.id.youtube);
                fbfollowers=(TextView) v.findViewById(R.id.facebook);

                vp=(Button) v.findViewById(R.id.viewprofile);
                rq=(Button) v.findViewById(R.id.request);

                image=(ImageView)v.findViewById(R.id.image);
                instaimg=(ImageView)v.findViewById(R.id.iimage);
                ytimg=(ImageView)v.findViewById(R.id.yimage);
                fbimg=(ImageView)v.findViewById(R.id.fimage);
                card=(CardView)v.findViewById(R.id.card);

            }
        }

    }

