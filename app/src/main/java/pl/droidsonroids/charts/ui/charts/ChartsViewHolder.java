package pl.droidsonroids.charts.ui.charts;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.charts.R;
import pl.droidsonroids.charts.model.Item;

/**
 * Created by grzegorzmatyszczak on 06/07/16.
 */

public class ChartsViewHolder extends RecyclerView.ViewHolder {

    //percent for guideline, when the position of label should be changed
    private static final int LABEL_POSITION_CHANGE_PERCENT = 4;

    @BindView(R.id.guideline_top) Guideline mGuidelineTop;
    @BindView(R.id.text_label) TextView mTextLabel;
    @BindDimen(R.dimen.margin_small) int mLabelMargin;


    public ChartsViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Item item) {
        mTextLabel.setText(item.getLabel());
        ConstraintLayout.LayoutParams layoutParamsTextLabel = (ConstraintLayout.LayoutParams) mTextLabel.getLayoutParams();
        if (item.getPercent() > LABEL_POSITION_CHANGE_PERCENT) {
            // move label above guideline
            layoutParamsTextLabel.bottomToTop = mGuidelineTop.getId();
            layoutParamsTextLabel.topToTop = -1;
            layoutParamsTextLabel.bottomMargin = mLabelMargin;
            layoutParamsTextLabel.topMargin = 0;

        } else {
            // move label below guideline
            layoutParamsTextLabel.topToTop = mGuidelineTop.getId();
            layoutParamsTextLabel.bottomToTop = -1;
            layoutParamsTextLabel.bottomMargin = 0;
            layoutParamsTextLabel.topMargin = mLabelMargin;
        }
        mTextLabel.setLayoutParams(layoutParamsTextLabel);

        ConstraintLayout.LayoutParams layoutParamsGuideline = (ConstraintLayout.LayoutParams) mGuidelineTop.getLayoutParams();
        layoutParamsGuideline.guidePercent = item.getPercent();
        mGuidelineTop.setLayoutParams(layoutParamsGuideline);

    }
}
