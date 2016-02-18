package appewtc.masterung.showpro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private TextView showNameTextView, showPointTextView;
    private ListView rewardListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Bind Widget
        bindWidget();

        //Show View
        showView();

        //Create ListView
        createListView();

    }   // Main Method

    private void createListView() {

        //Connected Database
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ManagTABLE.TABLE_REWARD, null);
        cursor.moveToFirst();
        int[] iconInts = new int[cursor.getCount()];
        String[] nameRewardStrings = new String[cursor.getCount()];
        String[] pointRewardStrings = new String[cursor.getCount()];

        for (int i=0;i<cursor.getCount();i++) {

            

        }   // for

    }   // createListView

    private void showView() {

        String[] resultStrings = getIntent().getStringArrayExtra("Result");
        showNameTextView.setText(resultStrings[3] + " " + resultStrings[4]);
        showPointTextView.setText(resultStrings[7] + " คะแนน");

    }   // showView

    private void bindWidget() {
        showNameTextView = (TextView) findViewById(R.id.textView9);
        showPointTextView = (TextView) findViewById(R.id.textView10);
        rewardListView = (ListView) findViewById(R.id.listView);
    }

}   // Main Class
