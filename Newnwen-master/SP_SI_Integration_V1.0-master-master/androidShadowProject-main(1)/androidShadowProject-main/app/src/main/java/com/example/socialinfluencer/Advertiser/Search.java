package com.example.socialinfluencer.Advertiser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.socialinfluencer.DataModels.Campaign_Data_Model;
import com.example.socialinfluencer.DataModels.InfluencerProfileData;
import com.example.socialinfluencer.Influencer.SearchCampaignsAdapter;
import com.example.socialinfluencer.Influencer.customAdapter;
import com.example.socialinfluencer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String s;
    private List<String> CampID;
    SearchCampaignsAdapter adapter;
    RecyclerView Campaigns;
    private List<InfluencerProfileData> listData;

    public Search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search.
     */
    // TODO: Rename and change types and number of parameters
    public static Search newInstance(String param1, String param2) {
        Search fragment = new Search();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
//        searchCategory("");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Campaigns = view.findViewById(R.id.SearchResults);
        final SearchView search = view.findViewById(R.id.influencerSearch);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchProcess(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchProcess(newText);
                return false;
            }

        });

        searchProcess("");

    }
    public void searchProcess(String s)
    {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Influencer").child("Profile");
        Query CampaignsQuery = ref.orderByChild("Influncer_Name").startAt(s).endAt(s+"\uff8f");
        Campaigns.setLayoutManager(new LinearLayoutManager(getContext()));
        searchCategory(s);
//        customAdapter adapter = new customAdapter(listData,  CampID,getContext());
//        Campaigns.setAdapter(adapter);
    }
    public void searchCategory(final String s)
    {
        CampID=new ArrayList<>();
        listData=new ArrayList<>();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Influencer");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    if(datas.hasChild("Profile")) {
                        InfluencerProfileData Icd = datas.child("Profile").getValue(InfluencerProfileData.class);
                        if(Icd.getInfluncer_Name().toUpperCase().indexOf(s.toUpperCase())!=-1){
//                    Log.d("/outputAdd",scd.getAdvertiserID());
                        listData.add(Icd); CampID.add(datas.getKey());}
                    }
                    InfluencerCustomAdapter customeAdapter = new InfluencerCustomAdapter(listData, CampID, getContext());
                    Campaigns.setAdapter(customeAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search2, container, false);
    }
}