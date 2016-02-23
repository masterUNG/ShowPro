package appewtc.masterung.showpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private ImageView promotionImageView, locationImageView,
            rewardImageView, aboutImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Bind Widget
        bindWidget();

        //Image Controller
        imageController();

    }   // Main Method

    private void imageController() {

        promotionImageView.setOnClickListener(this);
        locationImageView.setOnClickListener(this);
        rewardImageView.setOnClickListener(this);
        aboutImageView.setOnClickListener(this);

    }   // imageController

    private void bindWidget() {
        promotionImageView = (ImageView) findViewById(R.id.imageView4);
        locationImageView = (ImageView) findViewById(R.id.imageView5);
        rewardImageView = (ImageView) findViewById(R.id.imageView6);
        aboutImageView = (ImageView) findViewById(R.id.imageView7);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imageView4:
                startActivity(new Intent(MenuActivity.this, PromotionActivity.class));
                break;
            case R.id.imageView5:
                break;
            case R.id.imageView6:
                break;
            case R.id.imageView7:
                break;

        }   // switch

    }   // onClick

}   // Main Class
