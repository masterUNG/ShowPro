package appewtc.masterung.showpro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText,
            nameEditText, surnameEditText, addressEditText,
            emailEditText;
    private String userString, passwordString, nameString,
            surnameString, addressString, emailString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Bind Widget
        bindWidget();

    }   // Main Method

    public void clickSaveData(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        addressString = addressEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();

        if (checkSpace() || checkUser()) {
            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(RegisterActivity.this, R.drawable.icon_question,
                    "มีช่องว่าง หรือ User ซ้ำ", "กรุณากรองให้ครบ ทุกช่อง หรือ User ซ้ำ");

        } else {
            //No Space
            confirmRegister();

        }   // if

    }   // clickSaveData

    private boolean checkUser() {

        boolean bolStatus;

        try {

            ManagTABLE objManagTABLE = new ManagTABLE(this);
            String[] myResultStrings = objManagTABLE.searchUser(userString);
            Log.d("showPro", "Name = " + myResultStrings[3]);
            bolStatus = true;

        } catch (Exception e) {
            bolStatus = false;
        }


        return bolStatus;
    }

    private void confirmRegister() {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon_myaccount);
        objBuilder.setTitle("โปรดตรวจสอบข้อมูล");
        objBuilder.setMessage("User = " + userString + "\n" +
                                "Password = " + passwordString + "\n" +
                                "Name = " + nameString + "\n" +
                                "Surname = " + surnameString + "\n" +
                                "Address = " + addressString + "\n" +
                                "Email = " + emailString);
        objBuilder.setPositiveButton("Comfirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Update to mySQL
                updateToMySQL();
                dialogInterface.dismiss();
            }
        });
        objBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        objBuilder.show();

    }   // confirmRegister

    private void updateToMySQL() {

        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        try {

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            objNameValuePairs.add(new BasicNameValuePair(ManagTABLE.COLUMN_User, userString));
            objNameValuePairs.add(new BasicNameValuePair(ManagTABLE.COLUMN_Password, passwordString));
            objNameValuePairs.add(new BasicNameValuePair(ManagTABLE.COLUMN_Name, nameString));
            objNameValuePairs.add(new BasicNameValuePair(ManagTABLE.COLUMN_Surname, surnameString));
            objNameValuePairs.add(new BasicNameValuePair(ManagTABLE.COLUMN_Address, addressString));
            objNameValuePairs.add(new BasicNameValuePair(ManagTABLE.COLUMN_Email, emailString));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/mac/php_add_data_master.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);

            MyAlertDialog obj2MyAlertDialog = new MyAlertDialog();
            obj2MyAlertDialog.myDialog(RegisterActivity.this, R.drawable.icon_myaccount,
                    "อัพโหลดได้แล้ว",
                    "ข้อมูลได้ขึ้นไปบน Sever เรียบร้อยแล้ว");

            finish();

        } catch (Exception e) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(RegisterActivity.this,
                    R.drawable.icon_myaccount,
                    "ไม่สามารถอัพข้อมูลได้",
                    "เกิดความผิดปกติ ไม่สามารถอัพข้อมูล ขึ้น Server เพราะ " + e.toString());
        }

    }   // updateToMySQL

    private boolean checkSpace() {
        return userString.equals("") ||
                passwordString.equals("") ||
                nameString.equals("") ||
                surnameString.equals("") ||
                addressString.equals("") ||
                emailString.equals("");
    }


    private void bindWidget() {

        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        nameEditText = (EditText) findViewById(R.id.editText5);
        surnameEditText = (EditText) findViewById(R.id.editText6);
        addressEditText = (EditText) findViewById(R.id.editText7);
        emailEditText = (EditText) findViewById(R.id.editText8);

    }   // bindWidget

}   // Main Class
