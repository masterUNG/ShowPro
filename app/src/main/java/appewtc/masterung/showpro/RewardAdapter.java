package appewtc.masterung.showpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by masterUNG on 2/18/16 AD.
 */
public class RewardAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private int[] iconInts;
    private String[] nameRewardStrings, pointRewardStrings;
    private int[] iconSmallInts;

    public RewardAdapter(Context context,
                         int[] iconInts,
                         String[] nameRewardStrings,
                         String[] pointRewardStrings,
                         int[] iconSmallInts) {
        this.context = context;
        this.iconInts = iconInts;
        this.nameRewardStrings = nameRewardStrings;
        this.pointRewardStrings = pointRewardStrings;
        this.iconSmallInts = iconSmallInts;

    }   // Constructor

    @Override
    public int getCount() {
        return nameRewardStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.reward_listview, viewGroup, false);

        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imageView2);
        iconImageView.setImageResource(iconInts[i]);

        TextView nameRewardTextView = (TextView) view1.findViewById(R.id.textView12);
        nameRewardTextView.setText(nameRewardStrings[i]);

        TextView pointRewardTextView = (TextView) view1.findViewById(R.id.textView13);
        pointRewardTextView.setText(pointRewardStrings[i]);

        ImageView iconSmallImageView = (ImageView) view1.findViewById(R.id.imageView3);
        iconSmallImageView.setImageResource(iconSmallInts[i]);

        return view1;
    }
}   // Main Class
