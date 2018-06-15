package linhtinh.sea.mh.linhtinh.Adapter;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import linhtinh.sea.mh.linhtinh.Class.MyAppInfo;
import linhtinh.sea.mh.linhtinh.Entity.TimelineEntity;
import linhtinh.sea.mh.linhtinh.R;
import linhtinh.sea.mh.linhtinh.Utility.Utils;


/**
 * Created by WIN-HAIVM on 6/29/17.
 */

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.MyViewHolder> {
    private ArrayList<MyAppInfo> mList = null;
    private Activity mActivity = null;

//    public static final boolean isPresentation = false;

    public AppAdapter(Activity activity, ArrayList<MyAppInfo> list) {
        mList = list;
        mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_timeline, null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final MyAppInfo currentItem = mList.get(position);
        //--------------New design-------------------
        holder.txt_title.setText(currentItem.packageName + "");
        holder.txt_content.setText(Utils.getStringData(currentItem.sourceDir + ""));
        holder.txt_date.setText("name = "+currentItem.name + "");
//        if (!isPresentation) {


        if (currentItem.mIcon != null) {
//            Glide.with(mActivity).load(currentItem.mIcon).into(holder.img_timeline);
            holder.img_timeline.setImageDrawable(currentItem.mIcon);
        } else {
        }
    }
// Log.d(TAG, "Installed package :" + packageInfo.packageName);
//            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
//            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //---------------------
        public ImageView img_timeline = null;

        public TextView txt_date = null;
        public TextView txt_title = null;
        public TextView txt_content = null;

        public MyViewHolder(View itemView) {
            super(itemView);
            //---------New timeline design------------
            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_content = (TextView) itemView.findViewById(R.id.txt_content);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            img_timeline = itemView.findViewById(R.id.img_timeline);
        }
    }
}
