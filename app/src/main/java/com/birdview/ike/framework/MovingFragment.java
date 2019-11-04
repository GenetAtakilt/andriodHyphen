package com.birdview.ike.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MovingFragment extends Fragment implements View.OnClickListener {
    private Button house_item;
    private Button car;
    private MViewPagerAdapter adapter;
    public MovingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.moving_fragment,container,false);
        house_item = (Button) rootView.findViewById(R.id.house_item);
        car = (Button) rootView.findViewById(R.id.car);

        house_item.setOnClickListener(this);
        car.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.house_item:
                fragment = new HouseFragment();
                replaceFragment(fragment);
                break;

            case R.id.car:
                fragment = new CarFragment();
                replaceFragment(fragment);
                break;

        }

    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.veiwpager_id, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
