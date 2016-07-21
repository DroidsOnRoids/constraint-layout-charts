package pl.droidsonroids.charts.ui.charts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
import org.parceler.Parcels;
import pl.droidsonroids.charts.R;
import pl.droidsonroids.charts.model.Item;

/**
 * Created by grzegorzmatyszczak on 06/07/16.
 */

public class ChartsActivity extends AppCompatActivity implements ChartsView {

    private static final String EXTRA_ITEMS = "EXTRA_ITEMS";

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private ChartsPresenter mPresenter = new ChartsPresenter(this);
    private ChartsAdapter mAdapter;

    public static Intent getStartIntent(final Context context, final List<Item> items) {
        Intent intent = new Intent(context, ChartsActivity.class);
        intent.putExtra(EXTRA_ITEMS, Parcels.wrap(items));
        return intent;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        ButterKnife.bind(this);
        mPresenter.onCreated(Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_ITEMS)));
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setupRecyclerView(final List<Item> items) {
        mAdapter = new ChartsAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }
}
