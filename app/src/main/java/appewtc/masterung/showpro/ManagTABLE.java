package appewtc.masterung.showpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 1/5/16 AD.
 */
public class ManagTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String TABLE_USER = "userTABLE";
    public static final String TABLE_promotion = "promotionTABLE";
    public static final String COLUMN_id = "_id";
    public static final String COLUMN_User = "User";
    public static final String COLUMN_Password = "Password";
    public static final String COLUMN_Name = "Name";
    public static final String COLUMN_Surname = "Surname";
    public static final String COLUMN_Address = "Address";
    public static final String COLUMN_Email = "Email";
    public static final String COLUMN_Point = "Point";
    public static final String COLUMN_NamePromotion = "NamePromotion";
    public static final String COLUMN_Condition = "Condition";
    public static final String COLUMN_TimeStart = "TimeStart";
    public static final String COLUMN_TimeEnd = "TimeEnd";
    public static final String COLUMN_Place = "Place";
    public static final String COLUMN_Lat = "Lat";
    public static final String COLUMN_Lng = "Lng";
    public static final String COLUMN_Reward = "Reward";


    public ManagTABLE(Context context) {

        //Connected Database
        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public long addNewValueToUser(String strUser,
                                  String strPassword,
                                  String strName,
                                  String strSurname,
                                  String strAddress,
                                  String strEmail,
                                  String strPoint) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_User, strUser);
        objContentValues.put(COLUMN_Password, strPassword);
        objContentValues.put(COLUMN_Name, strName);
        objContentValues.put(COLUMN_Surname, strSurname);
        objContentValues.put(COLUMN_Address, strAddress);
        objContentValues.put(COLUMN_Email, strEmail);
        objContentValues.put(COLUMN_Point, strPoint);

        return writeSqLiteDatabase.insert(TABLE_USER, null, objContentValues);
    }

}   // Main Class
