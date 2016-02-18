package appewtc.masterung.showpro;

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

    }   // Main Method

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
