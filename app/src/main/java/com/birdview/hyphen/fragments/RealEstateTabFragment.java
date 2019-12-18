package com.birdview.hyphen.fragments;

import android.content.Context;
import android.graphics.PointF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.birdview.hyphen.R;
import com.birdview.hyphen.adapters.LocationRecyclerViewAdapter;
import com.birdview.hyphen.models.IndividualLocation;
import com.birdview.hyphen.utils.CustomThemeManager;
import com.birdview.hyphen.utils.LinearLayoutManagerWithSmoothScroller;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.MapboxDirections;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.turf.TurfConstants;
import com.mapbox.turf.TurfConversion;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mapbox.core.constants.Constants.PRECISION_6;
import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;
import static com.mapbox.mapboxsdk.style.expressions.Expression.eq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineWidth;

public class RealEstateTabFragment extends Fragment implements LocationRecyclerViewAdapter.ClickListener, PermissionsListener, MapboxMap.OnMapClickListener {

    //Camera bounded to device mock location
    private static final LatLngBounds LOCKED_MAP_CAMERA_BOUNDS = new LatLngBounds.Builder()
            .include(new LatLng(40.1746, -83.1426))
            .include(new LatLng(39.9278, -82.8814))
            .build();
    private static final LatLng MOCK_DEVICE_LOCATION_LAT_LNG = new LatLng(40, -83);
    private static final int CAMERA_MOVEMENT_SPEED_IN_MILSECS = 1200;
    private static final float NAVIGATION_LINE_WIDTH = 9;
    private static final String PROPERTY_SELECTED = "selected";
    private PermissionsManager permissionsManager;
    private DirectionsRoute currentRoute;
    private FeatureCollection featureCollection;
    private MapboxMap mapboxMap;
    private MapView mapView;
    private RecyclerView locationsRecyclerView;
    private CardView filterCardView;
    private ArrayList<IndividualLocation> listOfIndividualLocations;
    private CustomThemeManager customThemeManager;
    private LocationRecyclerViewAdapter styleRvAdapter;
    private int chosenTheme;
    private String TAG = "RealEstateTabFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_real_estate, container, false);
        filterCardView = view.findViewById(R.id.filter_layout_re);
        filterCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFilterFrag();
                Log.d(TAG, "Clicked");
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Create a GeoJSON feature collection from the GeoJSON file in the assets folder.
        try {
            getFeatureCollectionFromJson();
        } catch (Exception exception) {
            Log.e(TAG, "onViewCreated: " + exception);
            Toast.makeText(getContext(), R.string.failure_to_load_file, Toast.LENGTH_LONG).show();
        }

        // Initialize a list of IndividualLocation objects for future use with recyclerview
        listOfIndividualLocations = new ArrayList<>();


        // Initialize the theme that was selected in the previous activity. The blue theme is set as the backup default.
        chosenTheme = R.style.AppTheme_Neutral;
            // Set up the Mapbox map
        mapView = view.findViewById(R.id.re_mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                customThemeManager = new CustomThemeManager(chosenTheme, getContext());
                mapboxMap.setStyle(new Style.Builder().fromUri(customThemeManager.getMapStyle()), new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        // Setting the returned mapboxMap object (directly above) equal to the "globally declared" one
                        RealEstateTabFragment.this.mapboxMap = mapboxMap;
                        // Set bounds for the map camera so that the user can't pan the map outside of the NYC area
                        mapboxMap.setLatLngBoundsForCameraTarget(LOCKED_MAP_CAMERA_BOUNDS);

                        // Set up the SymbolLayer which will show the icons for each store location
                        initStoreLocationIconSymbolLayer();

                        // Set up the SymbolLayer which will show the selected store icon
                        initSelectedStoreSymbolLayer();

                        // Set up the LineLayer which will show the navigation route line to a particular store location
                        initNavigationPolylineLineLayer();

                        // Create a list of features from the feature collection
                        List<Feature> featureList = featureCollection.features();

                        // Retrieve and update the source designated for showing the store location icons
                        GeoJsonSource source = mapboxMap.getStyle().getSourceAs("store-location-source-id");
                        if (source != null) {
                            source.setGeoJson(FeatureCollection.fromFeatures(featureList));
                        }

                        if (featureList != null) {

                            for (int x = 0; x < featureList.size(); x++) {

                                Feature singleLocation = featureList.get(x);

                                // Get the single location's String properties to place in its map marker
                                String singleLocationName = singleLocation.getStringProperty("name");
                                String singleLocationHours = singleLocation.getStringProperty("hours");
                                String singleLocationDescription = singleLocation.getStringProperty("description");
                                String singleLocationPhoneNum = singleLocation.getStringProperty("phone");


                                // Add a boolean property to use for adjusting the icon of the selected store location
                                singleLocation.addBooleanProperty(PROPERTY_SELECTED, false);

                                // Get the single location's LatLng coordinates
                                Point singleLocationPosition = (Point) singleLocation.geometry();

                                // Create a new LatLng object with the Position object created above
                                LatLng singleLocationLatLng = new LatLng(singleLocationPosition.latitude(),
                                        singleLocationPosition.longitude());

                                // Add the location to the Arraylist of locations for later use in the recyclerview
                                listOfIndividualLocations.add(new IndividualLocation(
                                        singleLocationName,
                                        singleLocationDescription,
                                        singleLocationHours,
                                        singleLocationPhoneNum,
                                        singleLocationLatLng
                                ));

                                // Call getInformationFromDirectionsApi() to eventually display the location's
                                // distance from mocked device location
                                getInformationFromDirectionsApi(singleLocationPosition, false, x);
                            }
                            // Add the fake device location marker to the map. In a real use case scenario,
                            // the Maps SDK's LocationComponent can be used to easily display and customize
                            // the device location's puck
                            addMockDeviceLocationMarkerToMap();
                            // enableLocationComponent(style);

                            setUpRecyclerViewOfLocationCards(chosenTheme);

                            mapboxMap.addOnMapClickListener(RealEstateTabFragment.this);

                            Toast.makeText(getContext(), "Click on a card", Toast.LENGTH_SHORT).show();

                        }



                    }
                });
            }
        });

    }

    private void toFilterFrag() {
        Fragment fragment = new RealEstateFilterFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_tab_real_estate, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @SuppressWarnings({"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(getContext())) {

            // Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

            // Activate with options
            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(getContext(), loadedMapStyle).build());

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(getActivity());
        }
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(getActivity(), R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            Toast.makeText(getActivity(), R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
    }
    @Override
    public boolean onMapClick(@NonNull LatLng point) {
        handleClickIcon(mapboxMap.getProjection().toScreenLocation(point));
        return true;
    }

    private boolean handleClickIcon(PointF screenPoint) {
        List<Feature> features = mapboxMap.queryRenderedFeatures(screenPoint, "store-location-layer-id");
        if (!features.isEmpty()) {
            String name = features.get(0).getStringProperty("name");
            List<Feature> featureList = featureCollection.features();
            for (int i = 0; i < featureList.size(); i++) {

                if (featureList.get(i).getStringProperty("name").equals(name)) {
                    Point selectedFeaturePoint = (Point) featureList.get(i).geometry();

                    if (featureSelectStatus(i)) {
                        setFeatureSelectState(featureList.get(i), false);
                    } else {
                        setSelected(i);
                    }
                    if (selectedFeaturePoint.latitude() != MOCK_DEVICE_LOCATION_LAT_LNG.getLatitude()) {
                        for (int x = 0; x < featureCollection.features().size(); x++) {

                            if (listOfIndividualLocations.get(x).getLocation().getLatitude() == selectedFeaturePoint.latitude()) {
                                // Scroll the recyclerview to the selected marker's card. It's "x-1" below because
                                // the mock device location marker is part of the marker list but doesn't have its own card
                                // in the actual recyclerview.
                                locationsRecyclerView.smoothScrollToPosition(x);
                            }
                        }
                    }
                } else {
                    setFeatureSelectState(featureList.get(i), false);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * The LocationRecyclerViewAdapter's interface which listens to clicks on each location's card
     *
     * @param position the clicked card's position/index in the overall list of cards
     */
    @Override
    public void onItemClick(int position) {
        // Get the selected individual location via its card's position in the recyclerview of cards
        IndividualLocation selectedLocation = listOfIndividualLocations.get(position);

        // Evaluate each Feature's "select state" to appropriately style the location's icon
        List<Feature> featureList = featureCollection.features();
        Point selectedLocationPoint = (Point) featureCollection.features().get(position).geometry();
        for (int i = 0; i < featureList.size(); i++) {
            if (featureList.get(i).getStringProperty("name").equals(selectedLocation.getName())) {
                if (featureSelectStatus(i)) {
                    setFeatureSelectState(featureList.get(i), false);
                } else {
                    setSelected(i);
                }
            } else {
                setFeatureSelectState(featureList.get(i), false);
            }
        }

        // Reposition the map camera target to the selected marker
        if (selectedLocation != null) {
            repositionMapCamera(selectedLocationPoint);
        }

        // Check for an internet connection before making the call to Mapbox Directions API
        if (deviceHasInternetConnection()) {
            // Start call to the Mapbox Directions API
            if (selectedLocation != null) {
                getInformationFromDirectionsApi(selectedLocationPoint, true, null);
            }
        } else {
            Toast.makeText(getActivity(), R.string.no_internet_message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Adds a SymbolLayer which will show all of the location's icons
     */
    private void initStoreLocationIconSymbolLayer() {
        Style style = mapboxMap.getStyle();
        if (style != null) {
            // Add the icon image to the map
            style.addImage("store-location-icon-id", customThemeManager.getUnselectedMarkerIcon());

            // Create and add the GeoJsonSource to the map
            GeoJsonSource storeLocationGeoJsonSource = new GeoJsonSource("store-location-source-id");
            style.addSource(storeLocationGeoJsonSource);

            // Create and add the store location icon SymbolLayer to the map
            SymbolLayer storeLocationSymbolLayer = new SymbolLayer("store-location-layer-id",
                    "store-location-source-id");
            storeLocationSymbolLayer.withProperties(
                    iconImage("store-location-icon-id"),
                    iconAllowOverlap(true),
                    iconIgnorePlacement(true)
            );
            style.addLayer(storeLocationSymbolLayer);

        } else {
            Log.d("StoreFinderActivity", "initStoreLocationIconSymbolLayer: Style isn't ready yet.");

            throw new IllegalStateException("Style isn't ready yet.");
        }
    }

    /**
     * Adds a SymbolLayer which will show the selected location's icon
     */
    private void initSelectedStoreSymbolLayer() {
        Style style = mapboxMap.getStyle();
        if (style != null) {

            // Add the icon image to the map
            style.addImage("selected-store-location-icon-id", customThemeManager.getSelectedMarkerIcon());

            // Create and add the store location icon SymbolLayer to the map
            SymbolLayer selectedStoreLocationSymbolLayer = new SymbolLayer("selected-store-location-layer-id",
                    "store-location-source-id");
            selectedStoreLocationSymbolLayer.withProperties(
                    iconImage("selected-store-location-icon-id"),
                    iconAllowOverlap(true)
            );
            selectedStoreLocationSymbolLayer.withFilter(eq((get(PROPERTY_SELECTED)), literal(true)));
            style.addLayer(selectedStoreLocationSymbolLayer);
        } else {
            Log.d("StoreFinderActivity", "initSelectedStoreSymbolLayer: Style isn't ready yet.");
            throw new IllegalStateException("Style isn't ready yet.");
        }
    }

    /**
     * Checks whether a Feature's boolean "selected" property is true or false
     *
     * @param index the specific Feature's index position in the FeatureCollection's list of Features.
     * @return true if "selected" is true. False if the boolean property is false.
     */
    private boolean featureSelectStatus(int index) {
        if (featureCollection == null) {
            return false;
        }
        return featureCollection.features().get(index).getBooleanProperty(PROPERTY_SELECTED);
    }

    /**
     * Set a feature selected state.
     *
     * @param index the index of selected feature
     */
    private void setSelected(int index) {
        Feature feature = featureCollection.features().get(index);
        setFeatureSelectState(feature, true);
        refreshSource();
    }

    /**
     * Selects the state of a feature
     *
     * @param feature the feature to be selected.
     */
    private void setFeatureSelectState(Feature feature, boolean selectedState) {
        feature.properties().addProperty(PROPERTY_SELECTED, selectedState);
        refreshSource();
    }


    /**
     * Updates the display of data on the map after the FeatureCollection has been modified
     */
    private void refreshSource() {
        GeoJsonSource source = mapboxMap.getStyle().getSourceAs("store-location-source-id");
        if (source != null && featureCollection != null) {
            source.setGeoJson(featureCollection);
        }
    }


    private void getInformationFromDirectionsApi(Point destinationPoint,
                                                 final boolean fromMarkerClick, @Nullable final Integer listIndex) {
        // Set up origin and destination coordinates for the call to the Mapbox Directions API
        Point mockCurrentLocation = Point.fromLngLat(MOCK_DEVICE_LOCATION_LAT_LNG.getLongitude(),
                MOCK_DEVICE_LOCATION_LAT_LNG.getLatitude());

        Point destinationMarker = Point.fromLngLat(destinationPoint.longitude(), destinationPoint.latitude());

        // Initialize the directionsApiClient object for eventually drawing a navigation route on the map
        MapboxDirections directionsApiClient = MapboxDirections.builder()
                .origin(mockCurrentLocation)
                .destination(destinationMarker)
                .overview(DirectionsCriteria.OVERVIEW_FULL)
                .profile(DirectionsCriteria.PROFILE_DRIVING)
                .accessToken(getString(R.string.access_token))
                .build();

        directionsApiClient.enqueueCall(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                // Check that the response isn't null and that the response has a route
                if (response.body() == null) {
                    Log.e(TAG, "No routes found, make sure you set the right user and access token.");
                } else if (response.body().routes().size() < 1) {
                    Log.e(TAG, "No routes found");
                } else {
                    if (fromMarkerClick) {
                        // Retrieve and draw the navigation route on the map
                        currentRoute = response.body().routes().get(0);
                        drawNavigationPolylineRoute(currentRoute);
                    } else {
                        // Use Mapbox Turf helper method to convert meters to miles and then format the mileage number
                        DecimalFormat df = new DecimalFormat("#.#");
                        String finalConvertedFormattedDistance = df.format(TurfConversion.convertLength(
                                response.body().routes().get(0).distance(), TurfConstants.UNIT_METERS,
                                TurfConstants.UNIT_MILES));

                        // Set the distance for each location object in the list of locations
                        if (listIndex != null) {
                            listOfIndividualLocations.get(listIndex).setDistance(finalConvertedFormattedDistance);
                            // Refresh the displayed recyclerview when the location's distance is set
                            styleRvAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable throwable) {
                Toast.makeText(getActivity(), R.string.failure_to_retrieve, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void repositionMapCamera(Point newTarget) {
        CameraPosition newCameraPosition = new CameraPosition.Builder()
                .target(new LatLng(newTarget.latitude(), newTarget.longitude()))
                .build();
        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition), CAMERA_MOVEMENT_SPEED_IN_MILSECS);
    }

    private void addMockDeviceLocationMarkerToMap() {
        // Add the fake user location marker to the map
        Style style = mapboxMap.getStyle();
        if (style != null) {
            // Add the icon image to the map
            style.addImage("mock-device-location-icon-id", customThemeManager.getMockLocationIcon());

            style.addSource(new GeoJsonSource("mock-device-location-source-id", Feature.fromGeometry(
                    Point.fromLngLat(MOCK_DEVICE_LOCATION_LAT_LNG.getLongitude(), MOCK_DEVICE_LOCATION_LAT_LNG.getLatitude()))));

            style.addLayer(new SymbolLayer("mock-device-location-layer-id",
                    "mock-device-location-source-id").withProperties(
                    iconImage("mock-device-location-icon-id"),
                    iconAllowOverlap(true),
                    iconIgnorePlacement(true)
            ));
        } else {
            throw new IllegalStateException("Style isn't ready yet.");
        }
    }

    private void getFeatureCollectionFromJson() throws IOException {
        try {
            // Use fromJson() method to convert the GeoJSON file into a usable FeatureCollection object
            featureCollection = FeatureCollection.fromJson(loadGeoJsonFromAsset("list_of_locations.geojson"));

        } catch (Exception exception) {
            Log.e(TAG, "getFeatureCollectionFromJson: " + exception);
        }
    }

    private String loadGeoJsonFromAsset(String filename) {
        try {
            // Load the GeoJSON file from the local asset folder
            InputStream is = getActivity().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception exception) {
            Log.e(TAG, "Exception Loading GeoJSON: " + exception.toString());
            exception.printStackTrace();
            return null;
        }
    }

    private void setUpRecyclerViewOfLocationCards(int chosenTheme) {
        // Initialize the recyclerview of location cards and a custom class for automatic card scrolling
        locationsRecyclerView = getView().findViewById(R.id.rv_on_top_of_map);
        locationsRecyclerView.setHasFixedSize(true);
        locationsRecyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(getContext()));
        styleRvAdapter = new LocationRecyclerViewAdapter(listOfIndividualLocations,
                getApplicationContext(), this, chosenTheme);
        locationsRecyclerView.setAdapter(styleRvAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(locationsRecyclerView);
    }

    private void drawNavigationPolylineRoute(DirectionsRoute route) {
        // Retrieve and update the source designated for showing the store location icons
        GeoJsonSource source = mapboxMap.getStyle().getSourceAs("navigation-route-source-id");
        if (source != null) {
            source.setGeoJson(FeatureCollection.fromFeature(Feature.fromGeometry(
                    LineString.fromPolyline(route.geometry(), PRECISION_6))));
        }
    }

    private void initNavigationPolylineLineLayer() {
        // Create and add the GeoJsonSource to the map
        GeoJsonSource navigationLineLayerGeoJsonSource = new GeoJsonSource("navigation-route-source-id");
        mapboxMap.getStyle().addSource(navigationLineLayerGeoJsonSource);

        // Create and add the LineLayer to the map to show the navigation route line
        LineLayer navigationRouteLineLayer = new LineLayer("navigation-route-layer-id",
                navigationLineLayerGeoJsonSource.getId());
        navigationRouteLineLayer.withProperties(
                lineColor(customThemeManager.getNavigationLineColor()),
                lineWidth(NAVIGATION_LINE_WIDTH)
        );
        mapboxMap.getStyle().addLayerBelow(navigationRouteLineLayer, "store-location-layer-id");
    }

    // Add the mapView's lifecycle to the activity's lifecycle methods
    @Override
    @SuppressWarnings({"MissingPermission"})
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private boolean deviceHasInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}