package linhtinh.sea.mh.linhtinh.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import linhtinh.sea.mh.linhtinh.Entity.ItemEntity;
import linhtinh.sea.mh.linhtinh.Interface.ClickInterface;
import linhtinh.sea.mh.linhtinh.R;

/**
 * Created by WIN-HAIVM on 6/29/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private ArrayList<String> mList = null;
    private Activity mActivity = null;
    ClickInterface mClick;


    public ChatAdapter(ArrayList<String> list) {
        mList = list;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_chat, null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        setDataType(holder, position);
    }

    private void setDataType(MyViewHolder holder, final int position) {
        String currentItem = mList.get(position);
        holder.txt_message.setText(currentItem);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_message = null;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_message = (TextView) itemView.findViewById(R.id.txt_message);
        }
    }
}
