package pl.droidsonroids.charts.ui.main;

import java.util.List;
import pl.droidsonroids.charts.model.Item;
import pl.droidsonroids.charts.util.ValueValidatorUtil;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by grzegorzmatyszczak on 06/07/16.
 */

public class MainPresenter {

    private MainView mView;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public MainPresenter(final MainView mainView) {
        mView = mainView;
    }

    public void onAddClick() {
        mView.addItem(new Item("", ""));
    }

    public void onLabelChanged(final Item item, final String text) {
        item.setLabel(text);
    }

    public boolean onValueChanged(final Item item, final String text) {
        item.setPercentString(text);
        return ValueValidatorUtil.isValid(text);
    }

    public void onBindItem(final Subscription labelSubscription, final Subscription numberSubscription) {
        mSubscriptions.unsubscribe();
        mSubscriptions = new CompositeSubscription();
        mSubscriptions.add(labelSubscription);
        mSubscriptions.add(numberSubscription);
    }

    public void onDestroyed() {
        mSubscriptions.unsubscribe();
    }

    public void onChartsClick(final List<Item> items) {
        if (ValueValidatorUtil.isValid(items)) {
            mView.showCharts(items);
        } else {
            mView.showInvalidValuesMessage();
        }
    }

}
