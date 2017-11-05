package android.vericoin.info.veriumminer.adapter;

import android.support.v7.widget.RecyclerView;
import android.vericoin.info.veriumminer.R;
import android.vericoin.info.veriumminer.models.PoolItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PoolsAdapter extends RecyclerView.Adapter<PoolsAdapter.PoolItemViewHolder> {

    private List<PoolItem> mDataSet = new ArrayList<>();
    private final OnClickListener mOnClickListener;
    private final View mEmptyView;

    public interface OnClickListener {
        void onClick(String source, PoolItemViewHolder viewHolder);
    }

    public class PoolItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private final TextView mPoolName;
        private final TextView mPoolFee;

        public PoolItemViewHolder(View itemView) {
            super(itemView);

            mPoolName = itemView.findViewById(R.id.pool_name);
            mPoolFee = itemView.findViewById(R.id.pool_fee);
            itemView.setOnClickListener(this);
        }

        public void setSourceName(String name) {
            mPoolName.setText(name);
        }

        public void setPoolFee(double fee) {
            String sFee =  String.format ("Fee: %f", fee);
            mPoolFee.setText(sFee);
        }

        @Override
        public void onClick(View view) {
            TextView poolName = view.findViewById(R.id.pool_name);

            mOnClickListener.onClick(poolName.getText().toString(), this);
        }
    }

    public PoolsAdapter(OnClickListener onClickListener, View emptyView) {
        mOnClickListener = onClickListener;
        mEmptyView = emptyView;
    }

    @Override
    public PoolItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.pool_item;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);

        return new PoolItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PoolItemViewHolder holder, int position) {
        holder.setSourceName(mDataSet.get(position).getName());
        holder.setPoolFee(mDataSet.get(position).getLastStatistic().getFee());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void swapDataSet(List<PoolItem> dataSet) {
        if(dataSet == null)
            mDataSet = new ArrayList<>();
        else
            mDataSet = dataSet;

        notifyDataSetChanged();
        mEmptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
