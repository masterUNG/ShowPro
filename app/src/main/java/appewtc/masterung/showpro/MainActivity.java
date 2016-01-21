package appewtc.masterung.showpro;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private ManagTABLE objManagTABLE;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Connected Database
        objManagTABLE = new ManagTABLE(this);

        //Test Add Value
        //testAddValue();

        //Delete All SQLite
        deleteAllSQLite();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

    }   // Main Method

    public void clickLogin(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainActivity.this,
                    R.drawable.icon_question, "มีช่องว่าง", "กรุณากรอกทุกช่อง คะ");
        } else {
            //No Space
            checkUser();

        }

    }   // clickLogin

    private void checkUser() {

        try {

            String[] myResultStrings = objManagTABLE.searchUser(userString);
            Log.d("showPro", "Welcome ==> " + myResultStrings[3]);

            //Check Password
            if (passwordString.equals(myResultStrings[2])) {
                //Password True
                Intent objIntent = new Intent(MainActivity.this, ServiceActivity.class);
                objIntent.putExtra("Result", myResultStrings);
                startActivity(objIntent);


            } else {
                //Password False
                MyAlertDialog objMyAlertDialog = new MyAlertDialog();
                objMyAlertDialog.myDialog(MainActivity.this, R.drawable.icon_myaccount,
                        "Password False", "กรุณาลองใหม่ คุณพิมพ์ Password ผิด");


            }   // if

        } catch (Exception e) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainActivity.this,
                    R.drawable.icon_myaccount,
                    "ไม่มี User", "ไม่มี " + userString + " ใน ฐานข้อมูล ของเรา") ;
        }

    }   // checkUser


    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
    }

    private void synJSONtoSQLite() {

        StrictMode.ThreadPolicy myThreadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myThreadPolicy);

        int intTimes = 1;
        while (intTimes <= 2) {

            //1. Create InputStream
            InputStream objInputStream = null;
            String strURLuser = "http://swiftcodingthai.com/mac/php_get_data_master.php";
            String strURLpromote = "http://swiftcodingthai.com/mac/php_get_promote_master.php";
            HttpPost objHttpPost = null;
            String tag = "showPro";

            try {

                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTimes) {
                    case 1:
                        objHttpPost = new HttpPost(strURLuser);
                        break;
                    case 2:
                        objHttpPost = new HttpPost(strURLpromote);
                        break;
                }   // switch

                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

            } catch (Exception e) {
                Log.d(tag, "InputStream ==> " + e.toString());
            }

            //2. Ctrate JSON Sting
            String strJSON = null;

            try {

                BufferedReader objBufferedReader = new BufferedReader(
                        new InputStreamReader(objInputStream, "UTF-8")
                );
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = objBufferedReader.readLine()) != null) {
                    objStringBuilder.append(strLine);
                }   // while
                objInputStream.close();
                strJSON = objStringBuilder.toString();

            } catch (Exception e) {
                Log.d(tag, "strJSON ==> " + e.toString());
            }

            //3. Update SQLite
            try {

                JSONArray objJsonArray = new JSONArray(strJSON);
                for (int i = 0; i < objJsonArray.length(); i++) {
                    JSONObject jsonObject = objJsonArray.getJSONObject(i);
                switch (intTimes) {
                    case 1:
                        //For userTABLE
                            String strUser = jsonObject.getString(ManagTABLE.COLUMN_User);
                            String strPassword = jsonObject.getString(ManagTABLE.COLUMN_Password);
                            String strName = jsonObject.getString(ManagTABLE.COLUMN_Name);
                            String strSurname = jsonObject.getString(ManagTABLE.COLUMN_Surname);
                            String strAddress = jsonObject.getString(ManagTABLE.COLUMN_Address);
                            String strEmail = jsonObject.getString(ManagTABLE.COLUMN_Email);
                            String strPoint = jsonObject.getString(ManagTABLE.COLUMN_Point);
                            objManagTABLE.addNewValueToUser(strUser, strPassword,
                                    strName, strSurname, strAddress, strEmail, strPoint);

                        break;
                    case 2:
                        //For promotionTABLE
                        String strPromotion = jsonObject.getString(ManagTABLE.COLUMN_NamePromotion);
                        String strCondition = jsonObject.getString(ManagTABLE.COLUMN_Condition);
                        String strStart = jsonObject.getString(ManagTABLE.COLUMN_TimeStart);
                        String strEnd = jsonObject.getString(ManagTABLE.COLUMN_TimeEnd);
                        String strPlace = jsonObject.getString(ManagTABLE.COLUMN_Place);
                        String strLat = jsonObject.getString(ManagTABLE.COLUMN_Lat);
                        String strLng = jsonObject.getString(ManagTABLE.COLUMN_Lng);
                        String strReward = jsonObject.getString(ManagTABLE.COLUMN_Reward);
                        objManagTABLE.addNewValueToPromotion(strPromotion, strCondition,
                                strStart, strEnd, strPlace, strLat, strLng, strReward);

                        break;
                }   // switch
                }   // for


            } catch (Exception e) {
                Log.d(tag, "upData ==> " + e.toString());
            }


            intTimes += 1;
        }   // while


    }   // synJSONtoSQLite

    public void clickRegister(View view) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }


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
