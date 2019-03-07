package com.fzy.libs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


/**
 * autour : openXu
 * date : 2017/9/7 19:05
 * className : CommandRecyclerAdapter
 * version : 1.0
 * description : 通用的CommandRecyclerAdapter
 */
public abstract class CommandRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected boolean longClickAble = false;   //是否支持长点击

    public void setLongClickAble(boolean longClickAble) {
        this.longClickAble = longClickAble;
    }

    public CommandRecyclerAdapter(Context context, int layoutId, List<T> datas) {
        mDatas = new ArrayList<>();
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        if (datas != null)
            mDatas.addAll(datas);
    }

    public void setData(List<T> datas) {
        mDatas.clear();
        if (datas != null)
            mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        return ViewHolder.get(mContext, parent, mLayoutId);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        holder.getBinding().setVariable(BR.data, mDatas.get(position));
        convert(holder, mDatas.get(position), position);
        holder.setOnClickListener(-1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(mDatas.get(position), position);
            }
        });
        if(longClickAble){
            holder.setOnLongClickListener(-1, new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClick(mDatas.get(position), position);
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public List<T> getDatas() {
        return mDatas;
    }
    public T getItem(int poisiotn) {
        return mDatas==null||mDatas.size()<=0? null : mDatas.get(0);
    }
    /**
     * 重写此方法，将数据绑定到控件上
     *
     * @param holder
     * @param t
     */
    public abstract void convert(ViewHolder holder, T t, int position) ;

    /***
     * item点击
     * @param data
     */
    public abstract void onItemClick(T data, int position);

    /***
     * item点击
     * @param data
     */
    public void onItemLongClick(T data, int position){}

}