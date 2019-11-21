package com.birdview.hyphen.fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.birdview.hyphen.R;
import com.birdview.hyphen.adapters.LocationRecyclerViewAdapter;
import com.birdview.hyphen.models.SingleRecyclerViewLocation;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.utils.MapFragmentUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

public class HotelsTabFragment extends Fragment {
    private static final String TAG ="Hotels";
    private static final String SYMBOL_ICON_ID = "SYMBOL_ICON_ID";
    private static final String SOURCE_ID = "SOURCE_ID";
    private static final String LAYER_ID = "LAYER_ID";

    private MapboxMap mapboxMap;
    private MapView mapView;
    private FeatureCollection featureCollection;

    private LatLng[] coordinates = new LatLng[] {
            new LatLng(-34.6054099, -58.363654800000006),
            new LatLng(-34.6041508, -58.38555650000001),
            new LatLng(-34.6114412, -58.37808899999999),
            new LatLng(-34.6097604, -58.382064000000014),
            new LatLng(-34.596636, -58.373077999999964),
            new LatLng(-34.590548, -58.38256609999996),
            new LatLng(-34.5982127, -58.38110440000003)
    };
/*    public static HotelsTabFragment newInstance(Context context, int position) {
        HotelsTabFragment mapFragment = new HotelsTabFragment();
        Bundle args = new Bundle();
        args.putInt("ARGUMENT_POSITION", position);
        mapFragment.setArguments(args);
        MapboxMapOptions mapboxMapOptions = MapboxMapOptions.createFromAttributes(context);
        mapFragment.setArguments(MapFragmentUtils.createFragmentArgs(mapboxMapOptions));
        return mapFragment;
    }*/


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Context context = inflater.getContext();
    View view = inflater.inflate(R.layout.fragment_tab_hotels, container, false);
    return view;

}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.hotels_mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                HotelsTabFragment.this.mapboxMap = mapboxMap;
                mapboxMap.setStyle(
                        Style.MAPBOX_STREETS,
                        new Style.OnStyleLoaded(){

                            @Override
                            public void onStyleLoaded(@NonNull Style style) {
                                initRecyclerView();
                                initFeatureCollection();
                                initMarkerIcons(style);
                                Toast.makeText(getContext(), R.string.toast_instruction, Toast.LENGTH_SHORT).show();
                            }
                        }

                );
            }
        });
    }
    private void initFeatureCollection() {
        featureCollection = FeatureCollection.fromFeatures(new Feature[] {});
        List<Feature> featureList = new ArrayList<>();
        if (featureCollection != null) {
            for (LatLng latLng : coordinates) {
                featureList.add(Feature.fromGeometry(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude())));
            }
            featureCollection = FeatureCollection.fromFeatures(featureList);
        }
    }

    private void initRecyclerView() {
        RecyclerView reRecyclerView = getActivity().findViewById(R.id.rv_on_top_of_hotel_map);
        final LocationRecyclerViewAdapter locationAdapter =
                new LocationRecyclerViewAdapter(createRecyclerViewLocations(), mapboxMap);
        reRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        reRecyclerView.setItemAnimator(new DefaultItemAnimator());
        reRecyclerView.setAdapter(locationAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(reRecyclerView);
    }
    private void initMarkerIcons(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addImage(SYMBOL_ICON_ID, BitmapFactory.decodeResource(
                this.getResources(), R.drawable.red_marker));
        loadedMapStyle.addSource(new GeoJsonSource(SOURCE_ID, featureCollection));
        loadedMapStyle.addLayer(new SymbolLayer(LAYER_ID, SOURCE_ID).withProperties(
                iconImage(SYMBOL_ICON_ID),
                iconAllowOverlap(true),
                iconOffset(new Float[] {0f, -4f})
        ));
    }

    private List<SingleRecyclerViewLocation> createRecyclerViewLocations() {
        ArrayList<SingleRecyclerViewLocation> locationList = new ArrayList<>();
        for (int x = 0; x < coordinates.length; x++) {
            SingleRecyclerViewLocation singleLocation = new SingleRecyclerViewLocation();
            singleLocation.setName(String.format(getString(R.string.rv_card_name), x));
            singleLocation.setBedInfo(String.format(getString(R.string.rv_card_bed_info),
                    new Random().nextInt(coordinates.length)));
            singleLocation.setLocationCoordinates(coordinates[x]);
            locationList.add(singleLocation);
        }
        return locationList;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }
}
