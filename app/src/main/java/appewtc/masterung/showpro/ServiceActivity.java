package appewtc.masterung.showpro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private TextView showNameTextView, showPointTextView;
    private ListView rewardListView;
    private int myScoreAnInt;


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

    public void clickMenu(View view) {
        startActivity(new Intent(ServiceActivity.this, MenuActivity.class));
    }

    private void createListView() {

        //Connected Database
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ManagTABLE.TABLE_REWARD,
                null);
        cursor.moveToFirst();
        int[] iconSmallInts = new int[cursor.getCount()];
        String[] nameRewardStrings = new String[cursor.getCount()];
        String[] pointRewardStrings = new String[cursor.getCount()];
        String[] iconStrings = new String[cursor.getCount()];

        for (int i=0;i<cursor.getCount();i++) {

            nameRewardStrings[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_Reward_Name));
            pointRewardStrings[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_Use_Point));

            iconSmallInts[i] = checkReward(pointRewardStrings[i]);

            iconStrings[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_Pict_Reward));

            cursor.moveToNext();
        }   // for
        cursor.close();

        RewardAdapter rewardAdapter = new RewardAdapter(ServiceActivity.this, iconStrings,
                nameRewardStrings, pointRewardStrings, iconSmallInts);
        rewardListView.setAdapter(rewardAdapter);

    }   // createListView

    private int checkReward(String pointRewardString) {

        int intIconSmall = R.drawable.true1;

        if (myScoreAnInt < Integer.parseInt(pointRewardString)) {
            intIconSmall = R.drawable.false1;
        }

        return intIconSmall;
    }

    private void showView() {

        String[] resultStrings = getIntent().getStringArrayExtra("Result");
        showNameTextView.setText(resultStrings[3] + " " + resultStrings[4]);
        showPointTextView.setText(resultStrings[7] + " คะแนน");
        myScoreAnInt = Integer.parseInt(resultStrings[7]);

    }   // showView

    private void bindWidget() {
        showNameTextView = (TextView) findViewById(R.id.textView9);
        showPointTextView = (TextView) findViewById(R.id.textView10);
        rewardListView = (ListView) findViewById(R.id.listView);
    }

}   // Main Class
