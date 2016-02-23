package appewtc.masterung.showpro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class DetailPromotionActivity extends FragmentActivity implements OnMapReadyCallback {

    //Explicit
    private GoogleMap mMap;
    private String idString;
    private String[] resultStrings;
    private TextView namePromoteTextView, placeTextView,
            startTextView, endTextView, conditionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_detail_activity);

        //Bind Widget
        bindWidget();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Receive ID
        receiveID();

        //Show TextView
        showTextView();


    }   // Main Method

    private void showTextView() {

        namePromoteTextView.setText(resultStrings[1]);
        placeTextView.setText(resultStrings[6]);
        startTextView.setText(resultStrings[4]);
        endTextView.setText(resultStrings[5]);

    }   // showTextView

    private void bindWidget() {

        namePromoteTextView = (TextView) findViewById(R.id.textView24);
        placeTextView = (TextView) findViewById(R.id.textView25);
        startTextView = (TextView) findViewById(R.id.textView26);
        endTextView = (TextView) findViewById(R.id.textView27);


    }   // bindWidget

    private void receiveID() {

        idString = getIntent().getStringExtra("ID");
        Log.d("23Feb", "ID => " + idString);

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM promotionTABLE WHERE _id = " + "'" + idString + "'",
                null);
        cursor.moveToFirst();
        resultStrings = new String[cursor.getColumnCount()];
        for (int i = 0; i < cursor.getColumnCount(); i++) {

            resultStrings[i] = cursor.getString(i);
            Log.d("23Feb", "resultStrings[" + i + "] = " + resultStrings[i]);

        }   // for
        cursor.close();


    }   // receiveID

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double douLatCenter = Double.parseDouble(resultStrings[7]);
        double douLngCenter = Double.parseDouble(resultStrings[8]);

        LatLng centerLatLng = new LatLng(douLatCenter, douLngCenter);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 15));


    }   // onMapReady

}   // Main Class
