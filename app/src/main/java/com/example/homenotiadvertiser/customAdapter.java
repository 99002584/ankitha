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


        ArrayList<String> cTitle;
        ArrayList<String> cDescription;
        ArrayList<String> cCategory;
        ArrayList<String> cDate;
        Integer[] arr;
        Context context;

        public customAdapter(ArrayList<String> cTitle, ArrayList<String> cDescription, ArrayList<String> cCategory, ArrayList<String> cDate, Integer[] arr, Context context) {
            this.cTitle = cTitle;
            this.cDescription = cDescription;
            this.cCategory = cCategory;
            this.cDate = cDate;
            this.arr = arr;
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
            TextView htitle=holder.title;
            TextView hdescription=holder.description;
            TextView hcat=holder.cat;
            TextView hdate=holder.date;
            Button happly=holder.apply;
            Button hknwMore=holder.knwMore;

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
            hknwMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment campaign=new InfluencerDetails();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();
                }
            });

            htitle.setText(cTitle.get(position));
            hdescription.setText(cDescription.get(position));
            hcat.setText(cCategory.get(position));
            hdate.setText("Apply by "+cDate.get(position));
            himage.setImageResource(arr[position]);
            //button on click
            //card on click
        }

        @Override
        public int getItemCount() {
            return cTitle.size();

        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView description;
            TextView cat;
            TextView date;
            Button apply;
            Button knwMore;
            ImageView image;
            CardView card;

            public MyViewHolder(View v) {
                super(v);
                title=(TextView) v.findViewById(R.id.name);
                description=(TextView) v.findViewById(R.id.descrpt);
                cat=(TextView) v.findViewById(R.id.cat);

                apply=(Button) v.findViewById(R.id.apply);
                knwMore=(Button) v.findViewById(R.id.knw);
                image=(ImageView)v.findViewById(R.id.image);
                card=(CardView)v.findViewById(R.id.card);

            }
        }

    }

