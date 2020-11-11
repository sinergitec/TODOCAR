package com.sienrgitec.navegacionapp.actividades;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sienrgitec.navegacionapp.R;
import com.sienrgitec.navegacionapp.configuracion.Globales;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private Globales globales;
    private GoogleMap mMap;
    String vcRazonS = "", vcDom = "";
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        vcDom = getIntent().getExtras().getString("ipcDom");
        vcRazonS = getIntent().getExtras().getString("ipcProv");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Geocoder geocoder=new Geocoder(MapsActivity.this);
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(vcDom, 1);
            if(addressList!=null){
                Address singleaddress=addressList.get(0);
                latLng =new LatLng(singleaddress.getLatitude(),singleaddress.getLongitude());
                Log.e("Home-->", " Abriendo mapas" + latLng);


                mMap = googleMap;

                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(singleaddress.getLatitude(),singleaddress.getLongitude());
                mM