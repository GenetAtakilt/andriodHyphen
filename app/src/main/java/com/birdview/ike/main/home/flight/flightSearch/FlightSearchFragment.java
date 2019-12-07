package com.birdview.ike.main.home.flight.flightSearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.birdview.ike.framework.R;
import com.birdview.ike.main.home.flight.flightDetail.FightDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class FlightSearchFragment extends Fragment implements View.OnClickListener {

    Button flightSearch;
    List<FlightSearch> flightSearches;
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flight_search_recycler,container,false);

        recyclerView = view.findViewById(R.id.flightlist);
        FlightSearchAdapter flightSearchAdapter = new FlightSearchAdapter(getContext(),flightSearches);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(flightSearchAdapter);
        flightSearch = view.findViewById(R.id.button);
        flightSearch.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flightSearches = new ArrayList<>();

        flightSearches.add(new FlightSearch(1,"Los Angeles","Las Vegas",R.drawable.ic_location_on_black_24dp,R.drawable.ic_location_on_black_24dp));
        flightSearches.add(new FlightSearch(1,"Add a return Flight","October 1,2019,Tuesday",R.drawable.ic_unfold_more_black_24dp, R.drawable.ic_date_range_black_24dp));

    }

    private void flightDetial()
    {

        FightDetailFragment fightDetailFragment = new FightDetailFragment();
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.viewPager, fightDetailFragment)
                .commit();
    }
    @Override
    public void onClick(View v) {

        Fragment fragment = null;
        switch (v.getId()){
            case R.id.button:
              flightDetial();
                break;


        }
    }


}
