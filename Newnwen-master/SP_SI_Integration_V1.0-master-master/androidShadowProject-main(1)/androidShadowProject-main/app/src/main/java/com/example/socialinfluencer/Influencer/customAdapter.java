package com.example.socialinfluencer.Influencer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialinfluencer.DataModels.Campaign_Data_Model;
import com.example.socialinfluencer.Campaign.campaign;
import com.example.socialinfluencer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder>{

    ArrayList<String> cTitle;
    ArrayList<String> cDescription;
    ArrayList<String> cCategory;
    ArrayList<String> cDate;
    Integer[] arr;
    Context context;
    List<String> CampaignID;
    FirebaseAuth fauth;

    private List<Campaign_Data_Model> details;

    public customAdapter(List<Campaign_Data_Model> details, List<String> CampaignID, Context ctx) {
        this.details = details;
        this.CampaignID=CampaignID;
        this.context=ctx;
    }



//    public customAdapter(ArrayList<String> cTitle, ArrayList<String> cDescription, ArrayList<String> cCategory, ArrayList<String> cDate, Integer[] arr, Context context) {
//        this.cTitle = cTitle;
//        this.cDescription = cDescription;
//        this.cCategory = cCategory;
//        this.cDate = cDate;
//        this.arr = arr;
//        this.context = context;
//    }

    @NonNull
    @Override
    public customAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        MyViewHolder vh= new MyViewHolder(v);
        return vh;
    }
//
    @Override
    public void onBindViewHolder(@NonNull customAdapter.MyViewHolder holder, final int position) {
        Campaign_Data_Model cd = details.get(position);
        TextView htitle = holder.title;
        TextView hdescription = holder.description;
        TextView hcat = holder.cat;
        TextView hdate = holder.date;
        Button happly = holder.apply;
        Button hknwMore = holder.knwMore;
        fauth=FirebaseAuth.getInstance();

        ImageView himage = holder.image;
        CardView cam_card = holder.card;
        cam_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment campaign = new campaign();
                Bundle bundle = new Bundle();
                bundle.putString("CampaignID", CampaignID.get(position));
                campaign.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();
            }
        });
        hknwMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment campaign = new campaign();
                Bundle bundle = new Bundle();
                bundle.putString("CampaignID", CampaignID.get(position));
                campaign.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(campaign.toString()).commit();
            }
        });

        htitle.setText(cd.getCampaign_Name());
        hdescription.setText(cd.getDescripton());
        List<String> Cat = cd.getCategories();
        hcat.setText(Cat.get(0));
        if (getCountOfDays(cd.getStart_Date(), cd.getEnd_Date()) > 0) {
            hdate.setText("Apply by " + cd.getEnd_Date());
            happly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Applied Successfully",Toast.LENGTH_SHORT).show();
                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Influencer/"+fauth.getUid());
                    ref.child("Campaigns").child(CampaignID.get(position)).child("Status").setValue("Applied");
                }
            });

        } else if (getCountOfDays(cd.getStart_Date(), cd.getEnd_Date()) == 0)
        {
            hdate.setText("Campaign Ends Today " + cd.getEnd_Date());
            happly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Applied Successfully",Toast.LENGTH_SHORT).show();
                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Influencer/"+fauth.getUid());
                    ref.child("Campaigns").child(CampaignID.get(position)).child("Status").setValue("Applied");

                }
            });

        }
        else
        {
            hdate.setText("Campaign Ended on " + cd.getEnd_Date());
            happly.setEnabled(false);
        }
//        hdate.setText("Apply by "+cd.getEnd_Date());
        Glide.with(context)
                .load(cd.getCampaign_image())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return details.size();
        
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
            title=(TextView) v.findViewById(R.id.title);
            description=(TextView) v.findViewById(R.id.descrpt);
            cat=(TextView) v.findViewById(R.id.cat);
            date=(TextView) v.findViewById(R.id.date);
            apply=(Button) v.findViewById(R.id.apply);
            knwMore=(Button) v.findViewById(R.id.knw);
            image=(ImageView)v.findViewById(R.id.image);
            card=(CardView)v.findViewById(R.id.card);

        }
    }

    public float getCountOfDays(String createdDateString, String expireDateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        Date createdConvertedDate = null, expireCovertedDate = null, todayWithZeroTime = null;
        try {
            createdConvertedDate = dateFormat.parse(createdDateString);
            expireCovertedDate = dateFormat.parse(expireDateString);

            Date today = new Date();

            todayWithZeroTime = dateFormat.parse(dateFormat.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int cYear = 0, cMonth = 0, cDay = 0;

        if (createdConvertedDate.after(todayWithZeroTime)) {
            Calendar cCal = Calendar.getInstance();
            cCal.setTime(createdConvertedDate);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);

        } else {
            Calendar cCal = Calendar.getInstance();
            cCal.setTime(todayWithZeroTime);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);
        }


    /*Calendar todayCal = Calendar.getInstance();
    int todayYear = todayCal.get(Calendar.YEAR);
    int today = todayCal.get(Calendar.MONTH);
    int todayDay = todayCal.get(Calendar.DAY_OF_MONTH);
    */

        Calendar eCal = Calendar.getInstance();
        eCal.setTime(expireCovertedDate);

        int eYear = eCal.get(Calendar.YEAR);
        int eMonth = eCal.get(Calendar.MONTH);
        int eDay = eCal.get(Calendar.DAY_OF_MONTH);

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        date1.clear();
        date1.set(cYear, cMonth, cDay);
        date2.clear();
        date2.set(eYear, eMonth, eDay);

        long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
        return dayCount;
    }
}
