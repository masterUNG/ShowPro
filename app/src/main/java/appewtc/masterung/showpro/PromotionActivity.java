package appewtc.masterung.showpro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PromotionActivity extends AppCompatActivity {

    //Explicit
    private ListView promotionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        //Bind Widget
        promotionListView = (ListView) findViewById(R.id.listView2);

        //Create ListView
        createListView();

    }   // Main Method

    private void createListView() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ManagTABLE.TABLE_promotion + " ORDER BY _id DESC",
                null);
        cursor.moveToFirst();

        String[] namePromotion = new String[cursor.getCount()];
        String[] picPromotion = new String[cursor.getCount()];
        String[] startPromotion = new String[cursor.getCount()];
        String[] endPromotion = new String[cursor.getCount()];

        for (int i=0;i<cursor.getCount();i++) {

            namePromotion[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_NamePromotion));
            picPromotion[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_PictPromotion));
            startPromotion[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_TimeStart));
            endPromotion[i] = cursor.getString(cursor.getColumnIndex(ManagTABLE.COLUMN_TimeEnd));

            cursor.moveToNext();
        }   // for
        cursor.close();

        //Create ListView
        PromoteAdapter promoteAdapter = new PromoteAdapter(PromotionActivity.this,
                picPromotion, namePromotion, startPromotion, endPromotion);
        promotionListView.setAdapter(promoteAdapter);


    }   // createListView

    public void clickBackPromotion(View view) {
        finish();
    }

}   // Main Class
