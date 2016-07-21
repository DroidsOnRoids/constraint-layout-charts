package pl.droidsonroids.charts.ui.main;

import java.util.List;
import pl.droidsonroids.charts.model.Item;

/**
 * Created by grzegorzmatyszczak on 06/07/16.
 */

public interface MainView {
    void addItem(final Item item);
    void showCharts(final List<Item> items);
    void showInvalidValuesMessage();
}
