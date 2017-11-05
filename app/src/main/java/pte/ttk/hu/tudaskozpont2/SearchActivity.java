package pte.ttk.hu.tudaskozpont2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.estimote.cloud_plugin.common.EstimoteCloudCredentials;
import com.estimote.indoorsdk.IndoorLocationManagerBuilder;
import com.estimote.indoorsdk_module.algorithm.OnPositionUpdateListener;
import com.estimote.indoorsdk_module.algorithm.ScanningIndoorLocationManager;
import com.estimote.indoorsdk_module.cloud.CloudCallback;
import com.estimote.indoorsdk_module.cloud.EstimoteCloudException;
import com.estimote.indoorsdk_module.cloud.IndoorCloudManager;
import com.estimote.indoorsdk_module.cloud.IndoorCloudManagerFactory;
import com.estimote.indoorsdk_module.cloud.Location;
import com.estimote.indoorsdk_module.cloud.LocationPosition;
import com.estimote.indoorsdk_module.view.IndoorLocationView;
import com.estimote.internal_plugins_api.cloud.CloudCredentials;

public class SearchActivity extends AppCompatActivity {

    IndoorLocationView indoorLocationView;

    IndoorCloudManager cloudManager;

    ScanningIndoorLocationManager indoorLocationManager;

    CloudCredentials cloudCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            String id = intent.getStringExtra("id");
            Toast.makeText(this, "Your id is: "+id, Toast.LENGTH_SHORT).show();
        }

        cloudCredentials = new EstimoteCloudCredentials("locationbuilder-project-hd6", "faae6c28864872153523cdb072e4cc70");

        cloudManager = new IndoorCloudManagerFactory().create(this, cloudCredentials);
        cloudManager.getLocation("kicsibarna", new CloudCallback<Location>() {
            @Override
            public void success(Location location) {

                indoorLocationView = (IndoorLocationView) findViewById(R.id.indoor_view);
                indoorLocationView.setLocation(location);

                indoorLocationManager =
                        new IndoorLocationManagerBuilder(getApplicationContext(), location, cloudCredentials)
                                .withDefaultScanner()
                                .build();

                indoorLocationManager.startPositioning();

                indoorLocationManager.setOnPositionUpdateListener(new OnPositionUpdateListener() {
                    @Override
                    public void onPositionUpdate(LocationPosition locationPosition) {
                        indoorLocationView.updatePosition(locationPosition);
                        System.out.println("MEGFUTOTT");
                    }

                    @Override
                    public void onPositionOutsideLocation() {
                        indoorLocationView.hidePosition();
                    }
                });


            }

            @Override
            public void failure(EstimoteCloudException e) {
                // oops!
                if (1==1)  {

                }
            }

        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (indoorLocationManager != null) {


        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}
