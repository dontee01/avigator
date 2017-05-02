package com.avigator.avigator.activity;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.location.Address;
        import android.location.Geocoder;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.widget.Toast;

        import com.avigator.avigator.R;
        import com.avigator.avigator.activity.model.Message;
        import com.avigator.avigator.activity.network.ApiClient;
        import com.avigator.avigator.activity.network.ApiInterface;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.CustomCap;
        import com.google.android.gms.maps.model.Dash;
        import com.google.android.gms.maps.model.Dot;
        import com.google.android.gms.maps.model.Gap;
        import com.google.android.gms.maps.model.JointType;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.maps.model.PatternItem;
        import com.google.android.gms.maps.model.Polygon;
        import com.google.android.gms.maps.model.PolygonOptions;
        import com.google.android.gms.maps.model.Polyline;
        import com.google.android.gms.maps.model.PolylineOptions;
        import com.google.android.gms.maps.model.RoundCap;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

        import static com.avigator.avigator.R.id.map;


/**
 * An activity that displays a Google map with polylines to represent paths or routes,
 * and polygons to represent areas.
 */
public class MapsActivity extends AppCompatActivity
        implements GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener {

    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    private static final int POLYLINE_STROKE_WIDTH_PX = 12;
    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    // Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);

    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private static final List<PatternItem> PATTERN_POLYGON_BETA =
            Arrays.asList(DOT, GAP, DASH, GAP);
    List<String> init = new ArrayList<>();

    private String address, city, state, country, postalCode, knownName;
    private Double lat, lng;
    private Geocoder geocoder;
    private List<Address> addresses =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
//        init = populate();
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this tutorial, we add polylines and polygons to represent routes and areas on the map.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Add polylines to the map.
        // Polylines are useful to show a route or some other connection between points.
        Polyline polyline1;
//        polyline1 = googleMap.addPolyline(new PolylineOptions()
//                .clickable(true)
//                .add(
//                        new LatLng(5.7070, 6.7909),
//                        new LatLng(5.7360, 6.8834),
//                        new LatLng(6.1111, 6.5421)
//                ));
        PolylineOptions polylineOptions = new PolylineOptions();
//        for (po : init){
            polylineOptions.add(new LatLng(6.572997708, 3.319332056)).clickable(true);
//            polylineOptions.add(new LatLng(5.7070, 6.7909)).clickable(true);
//            polylineOptions.add(new LatLng(5.7360, 6.8834)).clickable(true);
//            polylineOptions.add(new LatLng(6.1111, 6.5421)).clickable(true);
            polylineOptions.add(new LatLng(4.9690, 8.3470)).clickable(true);

//        }

        polyline1 = googleMap.addPolyline(polylineOptions);

//        List<String> ll = new ArrayList<>();
//        ll.add("5.7070, 6.7909");
//        ll.add("5.7360, 6.8834");
//        ll.add("6.1111, 6.5421");
//        mSydney = mMap.addMarker(new MarkerOptions()
//                .position(SYDNEY)
//                .title("Sydney");

        // Store a data object with the polyline, used here to indicate an arbitrary type.
        polyline1.setTag("A");

        MarkerOptions options = new MarkerOptions();
//        options.position(new LatLng(5.7070, 6.7909));
//        options.position(new LatLng(5.7360, 6.8834));
//        options.position(new LatLng(6.1111, 6.5421));

        googleMap.addMarker(options.position(new LatLng(5.7070, 6.7909)));
        googleMap.addMarker(options.position(new LatLng(5.7360, 6.8834)));
        googleMap.addMarker(options.position(new LatLng(6.1111, 6.5421)));
        // Style the polyline.
        stylePolyline(polyline1);

//        Polyline polyline2 = googleMap.addPolyline(new PolylineOptions()
//                .clickable(true)
//                .add(
//                        new LatLng(-29.501, 119.700),
//                        new LatLng(-27.456, 119.672),
//                        new LatLng(-25.971, 124.187),
//                        new LatLng(-28.081, 126.555),
//                        new LatLng(-28.848, 124.229),
//                        new LatLng(-28.215, 123.938)));
//        polyline2.setTag("B");
//        stylePolyline(polyline2);

        // Add polygons to indicate areas on the map.
//        Polygon polygon1 = googleMap.addPolygon(new PolygonOptions()
//                .clickable(true)
//                .add(
//                        new LatLng(-27.457, 153.040),
//                        new LatLng(-33.852, 151.211),
//                        new LatLng(-37.813, 144.962),
//                        new LatLng(-34.928, 138.599)));
        // Store a data object with the polygon, used here to indicate an arbitrary type.
//        polygon1.setTag("alpha");
        // Style the polygon.
//        stylePolygon(polygon1);

//        Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
//                .clickable(true)
//                .add(
//                        new LatLng(-31.673, 128.892),
//                        new LatLng(-31.952, 115.857),
//                        new LatLng(-17.785, 122.258),
//                        new LatLng(-12.4258, 130.7932)));
//        polygon2.setTag("beta");
//        stylePolygon(polygon2);

        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(5.7070, 6.7909), 4));

        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);

        // Set a listener for marker click.
        googleMap.setOnMarkerClickListener(this);

    }

    /**
     * Styles the polyline, based on type.
     * @param polyline The polyline object that needs styling.
     */
    private void stylePolyline(Polyline polyline) {
        String type = "";
        // Get the data object stored with the polyline.
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "A":
                // Use a custom bitmap as the cap at the start of the line.
                polyline.setStartCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.ic_star_black_24dp), 10));
                break;
            case "B":
                // Use a round cap at the start of the line.
                polyline.setStartCap(new RoundCap());
                break;
        }

        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);
    }

    /**
     * Styles the polygon, based on type.
     * @param polygon The polygon object that needs styling.
     */
    private void stylePolygon(Polygon polygon) {
        String type = "";
        // Get the data object stored with the polygon.
        if (polygon.getTag() != null) {
            type = polygon.getTag().toString();
        }

        List<PatternItem> pattern = null;
        int strokeColor = COLOR_BLACK_ARGB;
        int fillColor = COLOR_WHITE_ARGB;

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "alpha":
                // Apply a stroke pattern to render a dashed line, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_GREEN_ARGB;
                fillColor = COLOR_PURPLE_ARGB;
                break;
            case "beta":
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_BETA;
                strokeColor = COLOR_ORANGE_ARGB;
                fillColor = COLOR_BLUE_ARGB;
                break;
        }

        polygon.setStrokePattern(pattern);
        polygon.setStrokeWidth(POLYGON_STROKE_WIDTH_PX);
        polygon.setStrokeColor(strokeColor);
        polygon.setFillColor(fillColor);
    }

    /**
     * Listens for clicks on a polyline.
     * @param polyline The polyline object that the user has clicked.
     */
    @Override
    public void onPolylineClick(Polyline polyline) {
        // Flip from solid stroke to dotted stroke pattern.
        if ((polyline.getPattern() == null) || (!polyline.getPattern().contains(DOT))) {
            polyline.setPattern(PATTERN_POLYLINE_DOTTED);
        } else {
            // The default pattern is a solid stroke.
            polyline.setPattern(null);
        }

        Toast.makeText(this, "Route type " + polyline.getTag().toString(),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Listens for clicks on a polygon.
     * @param polygon The polygon object that the user has clicked.
     */
    @Override
    public void onPolygonClick(Polygon polygon) {
        // Flip the values of the red, green, and blue components of the polygon's color.
        int color = polygon.getStrokeColor() ^ 0x00ffffff;
        polygon.setStrokeColor(color);
        color = polygon.getFillColor() ^ 0x00ffffff;
        polygon.setFillColor(color);

        Toast.makeText(this, "Area type " + polygon.getTag().toString(), Toast.LENGTH_SHORT).show();
    }

    public List<String> populate(){
        List<String> ll = new ArrayList<>();
        ll.add("5.7070, 6.7909");
        ll.add("5.7360, 6.8834");
        ll.add("6.1111, 6.5421");

        return ll;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        LatLng markerPosition = marker.getPosition();
        Double lat = markerPosition.latitude;
        Double lng = markerPosition.longitude;
//        Toast.makeText(this, "Testing : "+lat+":"+lng, Toast.LENGTH_LONG).show();

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("");
        alertDialog.setMessage("Obudu Mountain Resort (formerly known as the Obudu Cattle Ranch) is a ranch\n" +
                "        and resort on the Obudu Plateau in Cross River State, Nigeria. It was developed in 1951 by Mr. McCaughley,\n" +
                "        a Scot who first explored the mountain ranges in 1949. He camped on the mountaintop of the Oshie Ridge on\n" +
                "        the Sankwala Mountains for a month before returning with Mr. Hugh Jones a fellow rancher who, in 1951,\n" +
                "        together with Dr Crawfeild developed the Obudu Cattle Ranch. Although the ranch has been through troubles\n" +
                "        since, it has very recently been rehabilitated to its former glory. A recently added cable car from the\n" +
                "        base to the top of the plateau gives visitors a scenic view while bypassing the extremely winding road to\n" +
                "        the top");
        // Alert dialog button
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Alert dialog action goes here
                        // onClick button code here
                        dialog.dismiss();// use dismiss to cancel alert dialog
                    }
                });
        alertDialog.show();

//        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
//        intent.putExtra("EXTRA_INFO", R.string.info_obudu);
////        intent.putExtra("EXTRA_LONGITUDE", lng);
//        startActivity(intent);

        return false;
    }

}
