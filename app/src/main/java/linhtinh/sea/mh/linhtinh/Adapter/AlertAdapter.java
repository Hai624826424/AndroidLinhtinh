package linhtinh.sea.mh.linhtinh.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import linhtinh.sea.mh.linhtinh.Entity.ItemEntity;
import linhtinh.sea.mh.linhtinh.Entity.ListGsonEntity;
import linhtinh.sea.mh.linhtinh.Interface.ClickInterface;
import linhtinh.sea.mh.linhtinh.R;

/**
 * Created by WIN-HAIVM on 6/29/17.
 */

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.MyViewHolder> {
    private ArrayList<ItemEntity> mList = null;
    private Activity mActivity = null;
    ClickInterface mClick;


    public AlertAdapter(Activity activity, ArrayList<ItemEntity> list, ClickInterface click) {
        mList = list;
        mActivity = activity;
        mClick = click;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_alert, null);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        setDataType(holder, position);
        holder.rlt_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClick != null) {
                    mClick.onClick(position);
                }
            }
        });
    }

    private void setDataType(MyViewHolder holder, final int position) {
        ItemEntity currentItem = mList.get(position);
        holder.txt_title.setText(currentItem.title);
        holder.txt_count.setText(currentItem.content);
        holder.txt_unit.setText(currentItem.link);
        int col = mActivity.getResources().getInteger(R.integer.num_column);
        col = 1;
        if (((position - position % col) / col) % 2 == 1) {
            holder.rlt_click.setBackgroundColor(mActivity.getResources().getColor(R.color.gray));
        } else {
            holder.rlt_click.setBackgroundColor(mActivity.getResources().getColor(R.color.gray_light));
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View rlt_click = null;
        public TextView txt_title = null;
        public TextView txt_count = null;
        public TextView txt_unit = null;

        public MyViewHolder(View itemView) {
            super(itemView);
            rlt_click = itemView.findViewById(R.id.rlt_click);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_count = (TextView) itemView.findViewById(R.id.txt_count);
            txt_unit = (TextView) itemView.findViewById(R.id.txt_unit);
        }
    }
}
