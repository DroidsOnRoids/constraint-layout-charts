package pl.droidsonroids.charts.ui.main;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.List;
import org.parceler.Parcels;
import pl.droidsonroids.charts.R;
import pl.droidsonroids.charts.model.Item;
import pl.droidsonroids.charts.ui.charts.ChartsActivity;
import rx.Subscription;

import static pl.droidsonroids.charts.util.Constants.EXTRA_ITEMS;

public class MainActivity extends AppCompatActivity implements MainView, MainItemListener {

    @BindView(R.id.constraint_layout) ConstraintLayout mConstraintLayout;
    @BindView(R.id.recycler_chart_edit) RecyclerView mRecyclerView;
    @BindView(R.id.fab_add) FloatingActionButton mFabAdd;
    @BindView(R.id.fab_charts) FloatingActionButton mFabCharts;
    @BindString(R.string.error_invalid_values) String mInvalidValuesMessage;

    private MainPresenter mPresenter = new MainPresenter(this);

    private MainAdapter mMainAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupRecyclerView(savedInstanceState);
    }

    private void setupRecyclerView(final Bundle savedInstanceState) {
        mMainAdapter = new MainAdapter(this);
        mRecyclerView.setAdapter(mMainAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        if (savedInstanceState != null) {
            mMainAdapter.setItems(Parcels.unwrap(savedInstanceState.getParcelable(EXTRA_ITEMS)));
        }
    }

    @OnClick(R.id.fab_charts)
    public void onChartsClick() {
        mPresenter.onChartsClick(mMainAdapter.getItems());
    }

    @OnClick(R.id.fab_add)
    public void onAddClick() {
        mPresenter.onAddClick();
    }

    @Override
    public void addItem(final Item item) {
        mMainAdapter.addItem(item);
        mRecyclerView.smoothScrollToPosition(mMainAdapter.getItemCount() - 1);
    }

    @Override
    public void showCharts(final List<Item> items) {
        startActivity(ChartsActivity.getStartIntent(this, items));
    }

    @Override
    public void showInvalidValuesMessage() {
        Snackbar.make(mConstraintLayout, mInvalidValuesMessage, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClicked(final int position) {
        mMainAdapter.removeItem(position);
    }

    @Override
    public void onLabelChanged(final int position, final String text) {
        mPresenter.onLabelChanged(mMainAdapter.getItem(position), text);
    }

    @Override
    public boolean onValueChanged(final int position, final String text) {
        return mPresenter.onValueChanged(mMainAdapter.getItem(position), text);
    }

    @Override
    public void onBindItem(final Subscription labelSubscription, final Subscription valueSubscription) {
        mPresenter.onBindItem(labelSubscription, valueSubscription);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_ITEMS, Parcels.wrap(mMainAdapter.getItems()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroyed();
    }
}
