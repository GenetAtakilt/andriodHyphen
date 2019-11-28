package com.birdview.hyphen.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.birdview.hyphen.R;
import com.mapbox.mapboxsdk.maps.Style;

/**
 * Custom class which creates marker icons and colors based on the selected theme
 */
public class CustomThemeManager {


    private int selectedTheme;
    private Context context;
    private Bitmap unselectedMarkerIcon;
    private Bitmap selectedMarkerIcon;
    private Bitmap mockLocationIcon;
    private int navigationLineColor;
    private String mapStyle;

    public CustomThemeManager(int selectedTheme, Context context) {
        this.selectedTheme = selectedTheme;
        this.context = context;
        initializeTheme();
    }

    private void initializeTheme() {


                mapStyle = Style.MAPBOX_STREETS;
                navigationLineColor = context.getResources().getColor(R.color.navigationRouteLine_neutral, context.getTheme());
                unselectedMarkerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.white_unselected_house);
                selectedMarkerIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.gray_selected_house);
                mockLocationIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue_user_location);

    }

    public Bitmap getUnselectedMarkerIcon() {
        return unselectedMarkerIcon;
    }

    public Bitmap getMockLocationIcon() {
        return mockLocationIcon;
    }

    public Bitmap getSelectedMarkerIcon() {
        return selectedMarkerIcon;
    }

    public int getNavigationLineColor() {
        return navigationLineColor;
    }

    public String getMapStyle() {
        return mapStyle;
    }

}
