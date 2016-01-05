package appewtc.masterung.showpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private ManagTABLE objManagTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connected Database
        objManagTABLE = new ManagTABLE(this);

    }   // Main Method

}   // Main Class
