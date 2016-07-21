package pl.droidsonroids.charts.ui.main;

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

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private List<Item> mItemList = new ArrayList<>();
    private MainItemListener mClickListener;

    public MainAdapter(final MainItemListener clickListener) {
        mClickListener = clickListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chart_bar_edit, parent, false);
        return new MainViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, final int position) {
        holder.bind(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void addItem(final Item item) {
        mItemList.add(item);
        notifyItemInserted(mItemList.size());
    }

    public List<Item> getItems() {
        return mItemList;
    }

    public void removeItem(final int position) {
        mItemList.remove(position);
        notifyItemRemoved(position);
    }

    public Item getItem(final int position) {
        return mItemList.get(position);
    }

    public void setItems(final List<Item> items) {
        mItemList = items;
    }
}
