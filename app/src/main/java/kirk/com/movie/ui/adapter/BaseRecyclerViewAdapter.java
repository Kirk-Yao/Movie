package kirk.com.movie.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.List;

import kirk.com.movie.R;

/**
 * Created by admin on 2017/11/25.
 */

public abstract class BaseRecyclerViewAdapter<T extends BaseRecyclerViewAdapter.Item> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected Context context;
    protected List<T> list;

    protected boolean isFooterShowing = false;

    private final int FOOTER_TYPE = 1;
    private final int NORMAL_TYPE = 0;

    protected BaseRecyclerViewAdapter() {
    }


    public BaseRecyclerViewAdapter(Context context,List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.view_footer,
                    parent,false);
            return new FooterHolder(view);
        } else {
            return createHolder(parent,viewType);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooterShowing && position == list.size()){
            return FOOTER_TYPE;
        } else {
            return NORMAL_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        if (list == null){
            return 0;
        } else {
            // 需要结合此时是否有footer来判断，否则直接返回list.size()的话footer不会显示出来
            return isFooterShowing() ? list.size() + 1 : list.size();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == FOOTER_TYPE){
            FooterHolder footerHolder = (FooterHolder) holder;
            onBindFooterView(footerHolder.itemView);
        } else {
            if (position >= 0 && position < list.size()){
                onBindItemView(holder,list.get(position));
            }
        }
    }

    protected abstract RecyclerView.ViewHolder createHolder(ViewGroup parent,int viewType);

    protected abstract void onBindFooterView(View footerView);

    protected abstract void onBindItemView(RecyclerView.ViewHolder holder,T item);

    public interface Item{

    }

    static class FooterHolder extends RecyclerView.ViewHolder{

        public FooterHolder(View itemView) {
            super(itemView);
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void showFooter(){
        if (isFooterShowing){
            return;
        }
        isFooterShowing = true;
        notifyItemInserted(list.size());
        notifyDataSetChanged();
    }

    public void hideFooter(){
        if (!isFooterShowing){
            return;
        }
        isFooterShowing = false;
        notifyItemRemoved(list.size());
    }

    public boolean isFooterShowing() {
        return isFooterShowing;
    }

    public void setFooterShowing(boolean footerShowing) {
        isFooterShowing = footerShowing;
    }

    protected boolean isFooter(int position){
        return getItemViewType(position) == FOOTER_TYPE;
    }
}
