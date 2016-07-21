package pl.droidsonroids.charts.ui.main;

import rx.Subscription;

/**
 * Created by grzegorzmatyszczak on 08/07/16.
 */

public interface MainItemListener {
    void onDeleteClicked(final int position);
    void onLabelChanged(final int position, final String text);
    boolean onValueChanged(final int position, final String text);
    void onBindItem(final Subscription labelSubscription, final Subscription valueSubscription);
}
