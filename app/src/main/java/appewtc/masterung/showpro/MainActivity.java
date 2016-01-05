package appewtc.masterung.showpro;

import android.database.sqlite.SQLiteDatabase;
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

        //Test Add Value
        //testAddValue();

        //Delete All SQLite
        deleteAllSQLite();

    }   // Main Method

    private void deleteAllSQLite() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        objSqLiteDatabase.delete(ManagTABLE.TABLE_USER, null, null);
        objSqLiteDatabase.delete(ManagTABLE.TABLE_promotion, null, null);
    }

    private void testAddValue() {
        objManagTABLE.addNewValueToUser("testUser", "testPassword",
                "testName", "testSurname", "testAddress", "testEmail", "testPoint");
        objManagTABLE.addNewValueToPromotion("Promote", "Condition", "Start", "End",
                "Place", "Lat", "Lng", "Reward");
    }

}   // Main Class
