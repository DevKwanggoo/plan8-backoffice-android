package io.plan8.business.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SSozi on 2017. 9. 15..
 */

public abstract class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<BindingRecyclerViewAdapter.BindingViewHolder> {
    protected List<T> data = new ArrayList<>();

    abstract protected int selectViewLayoutType(T data);

    abstract protected void bindVariables(ViewDataBinding binding, T data);

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        if (data != null) this.data = data;
        else this.data = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public BindingRecyclerViewAdapter.BindingViewHolder onCreateViewHolder(ViewGroup parent, int layoutId) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layoutId, parent, false);
        return new BindingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BindingRecyclerViewAdapter.BindingViewHolder viewHolder, int position) {
        bindVariables(viewHolder.getBinding(), data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return selectViewLayoutType(data.get(position));
    }

    public class BindingViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingViewHolder(View view) {
            super(view);
            this.binding = DataBindingUtil.bind(view);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}