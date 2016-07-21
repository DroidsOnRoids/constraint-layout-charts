package pl.droidsonroids.charts.ui.main;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jakewharton.rxbinding.widget.RxTextView;
import pl.droidsonroids.charts.R;
import pl.droidsonroids.charts.model.Item;
import rx.Subscription;

/**
 * Created by grzegorzmatyszczak on 06/07/16.
 */

public class MainViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.edit_label) EditText mEditLabel;
    @BindView(R.id.edit_value) EditText mEditValue;
    @BindView(R.id.layout_input_value) TextInputLayout mLayoutInputValue;
    @BindString(R.string.error_value) String mErrorMessage;

    private MainItemListener mMainItemListener;

    public MainViewHolder(final View itemView, final MainItemListener mainItemListener) {
        super(itemView);
        mMainItemListener = mainItemListener;
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Item item) {
        mEditValue.setText(item.getPercentString());
        mEditLabel.setText(item.getLabel());
        Subscription labelSubscription = RxTextView.textChanges(mEditLabel)
                .skip(1)
                .subscribe(text -> mMainItemListener.onLabelChanged(getAdapterPosition(), text.toString()));
        Subscription valueSubscription = RxTextView.textChanges(mEditValue)
                .skip(1)
                .subscribe(text ->  {
                    boolean isValid = mMainItemListener.onValueChanged(getAdapterPosition(), text.toString());
                    mLayoutInputValue.setError(isValid ? null : mErrorMessage);
                });
        mMainItemListener.onBindItem(labelSubscription, valueSubscription);
    }

    @OnClick(R.id.image_delete)
    public void onDeleteClick() {
        mMainItemListener.onDeleteClicked(getAdapterPosition());
    }
}
