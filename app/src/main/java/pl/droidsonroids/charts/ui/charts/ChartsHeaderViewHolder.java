package pl.droidsonroids.charts.ui.charts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import java.util.List;
import pl.droidsonroids.charts.R;

/**
 * Created by grzegorzmatyszczak on 10/07/16.
 */

public class ChartsHeaderViewHolder extends RecyclerView.ViewHolder {

    @BindViews({R.id.text_label_20, R.id.text_label_40, R.id.text_label_60, R.id.text_label_80, R.id.text_label_100}) List<TextView> mTextLabels;

    private static final ButterKnife.Action<View> VISIBLE = new ButterKnife.Action<View>() {

        @Override public void apply(View view, int index) {
            view.setVisibility(View.VISIBLE);
        }

    };

    public ChartsHeaderViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ButterKnife.apply(mTextLabels, VISIBLE);
    }

}
