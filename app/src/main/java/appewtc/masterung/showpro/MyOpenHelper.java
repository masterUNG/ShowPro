package appewtc.masterung.showpro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 1/5/16 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String DATABASE_NAME = "ShowPro.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_USER_TABLE = "create table userTABLE (" +
            "_id integer primary key, " +
            "User text, " +
            "Password text, " +
            "Name text, " +
            "Surname text, " +
            "Address text, " +
            "Email text, " +
            "Point text);";
    private static final String CREATE_PROMOTION_TABLE = "create table promotionTABLE (" +
            "_id integer primary key, " +
            "NamePromotion text, " +
            "Condition text, " +
            "TimeStart text, " +
            "TimeEnd text, " +
            "Place text, " +
            "Lat text, " +
            "Lng text, " +
            "Reward text);";

    private static final String CREATE_REWARD_TABLE = "create table rewardTABLE (" +
            "_id integer primary key, " +
            "Reward_Name text, " +
            "Use_Point text, " +
            "Pict_Reward);";


    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }   // Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_PROMOTION_TABLE);
        sqLiteDatabase.execSQL(CREATE_REWARD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}   // Main Class
