package pl.droidsonroids.charts.ui.charts;

import java.util.ArrayList;
import java.util.List;
import pl.droidsonroids.charts.model.Item;

/**
 * Created by grzegorzmatyszczak on 06/07/16.
 */

public class ChartsPresenter {

    private ChartsView mView;

    public ChartsPresenter(final ChartsView chartsView) {
        mView = chartsView;
    }

    public void onCreated(final List<Item> items) {
        ArrayList<Item> resultItems = new ArrayList<>();
        for (Item item : items) {
            if (!item.getPercentString().isEmpty()) {
                item.setPercent(100 - Integer.parseInt(item.getPercentString()));
                resultItems.add(item);
            }
        }
        mView.setupRecyclerView(resultItems);
    }
}
