package linhtinh.sea.mh.linhtinh.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import linhtinh.sea.mh.linhtinh.Entity.LeftEntity;
import linhtinh.sea.mh.linhtinh.Interface.ClickInterface;
import linhtinh.sea.mh.linhtinh.R;

/**
 * Created by WIN-HAIVM on 6/29/17.
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {
    private ArrayList<LeftEntity> mList = null;
    ClickInterface mClick;


    public LeftAdapter(ArrayList<LeftEntity> list, ClickInterface click) {
        mList = list;
        mClick = click;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_left, null);

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
                    view.setSelected(true);
                }
            }
        });
    }

    private void setDataType(MyViewHolder holder, final int position) {
        LeftEntity currentItem = mList.get(position);
        holder.txt_title.setText(currentItem.title);
        holder.rlt_click.setSelected(currentItem.selected);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View rlt_click = null;
        public TextView txt_title = null;

        public MyViewHolder(View itemView) {
            super(itemView);
            rlt_click = itemView.findViewById(R.id.rlt_click);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
        }
    }
}
