package android.vericoin.info.veriumminer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.vericoin.info.veriumminer.adapter.PoolsAdapter;
import android.vericoin.info.veriumminer.api.ApiClient;
import android.vericoin.info.veriumminer.api.ApiInterface;
import android.vericoin.info.veriumminer.models.PoolItem;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoolsActivity extends AppCompatActivity {
    private static final String TAG = PoolsActivity.class.getSimpleName();

    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mRecyclerView;
    private PoolsAdapter mAdapter;
    private PoolsAdapter.OnClickListener mOnClickListener = new PoolsAdapter.OnClickListener() {
        @Override
        public void onClick(String pool, PoolsAdapter.PoolItemViewHolder viewHolder) {
            Intent intent = new Intent(PoolsActivity.this, PoolCredentialsActivity.class);
            // FIXME: Add pool id
            intent.putExtra(PoolCredentialsActivity.POOL_NAME_KEY, pool);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pools);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSwipeRefresh = findViewById(R.id.swiperefresh);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPools();
            }
        });

        View emptyView = findViewById(R.id.empty_pool_list);
        mAdapter = new PoolsAdapter(mOnClickListener, emptyView);

        mRecyclerView = findViewById(R.id.pool_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPools();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pools, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_refresh:
                getPools();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getPools() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<List<PoolItem>> call = apiService.getPools(false, true);

        mSwipeRefresh.setRefreshing(true);

        call.enqueue(new Callback<List<PoolItem>>() {
            @Override
            public void onResponse(Call<List<PoolItem>> call, Response<List<PoolItem>> response) {

                if (response.isSuccessful()) {
                    List<PoolItem> pools = response.body();
                    mAdapter.swapDataSet(pools);
                } else {
                    mAdapter.swapDataSet(null);
                    Log.d(TAG, "---- Response error: " + response.message());
                }
                mSwipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<PoolItem>> call, Throwable t) {
                mAdapter.swapDataSet(null);
                Log.d(TAG, "---- Call failed: " + t.getMessage());
                mSwipeRefresh.setRefreshing(false);
            }
        });
    }
}