package pl.droidsonroids.charts.ui.charts;

import java.util.List;
import pl.droidsonroids.charts.model.Item;

/**
 * Created by grzegorzmatyszczak on 06/07/16.
 */

public interface ChartsView {
    void setupRecyclerView(final List<Item> items);
}
