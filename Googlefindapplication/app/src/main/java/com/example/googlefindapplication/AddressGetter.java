package com.example.googlefindapplication;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

public class AddressGetter implements Callable<List<String>> {
    private Context context;
    private LatLng latLng;

    public AddressGetter(Context context, LatLng latLng) {
        this.context = context;
        this.latLng = latLng;
    }

    @Override
    public List<String> call() throws Exception {
        return getAddress();
    }

    private List<String> getAddress() {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<String> result = new ArrayList<String>();
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            StringBuilder stringBuilder = new StringBuilder();
            int maxIndex = addresses.get(0).getMaxAddressLineIndex();
            for(int i = 0; i < maxIndex; i++) {
                stringBuilder.append(addresses.get(0).getAddressLine(i));
                stringBuilder.append(" ");
            }
            result.add(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        } catch (IOException e) {
            result.add(" ");
            e.printStackTrace();
        }
        result.add(" ");
        return result;
    }
}
