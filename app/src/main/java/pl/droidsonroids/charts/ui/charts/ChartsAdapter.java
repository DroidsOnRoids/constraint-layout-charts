package pl.droidsonroids.charts.ui.charts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import pl.droidsonroids.charts.R;
import pl.droidsonroids.charts.model.Item;

/**
 * Created by grzegorzmatyszczak on 06/07/16.
 */

public class ChartsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADERS_COUNT = 1;

    private static final int VIEW_HOLDER_HEADER = 0;
    private static final int VIEW_HOLDER_DEFAULT = 1;

    private List<Item> mItemList = new ArrayList<>();

    public ChartsAdapter(final List<Item> itemList) {
        mItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        if (viewType == VIEW_HOLDER_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chart_bar_header, parent, false);
            return new ChartsHeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chart_bar, parent, false);
            return new ChartsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == VIEW_HOLDER_DEFAULT) {
            ((ChartsViewHolder) holder).bind(mItemList.get(position - HEADERS_COUNT));
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size() + HEADERS_COUNT;
    }

    @Override
    public int getItemViewType(final int position) {
        if (position == 0) {
            return VIEW_HOLDER_HEADER;
        } else {
            return VIEW_HOLDER_DEFAULT;
        }
    }
}
