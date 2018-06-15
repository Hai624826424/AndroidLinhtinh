package linhtinh.sea.mh.linhtinh.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import linhtinh.sea.mh.linhtinh.Adapter.LeftAdapter;
import linhtinh.sea.mh.linhtinh.Entity.LeftEntity;
import linhtinh.sea.mh.linhtinh.Interface.ClickInterface;
import linhtinh.sea.mh.linhtinh.R;

/**
 * Created by WIN-HAIVM on 1/12/18.
 */

public class LeftFragment extends Fragment {
    View mRoot = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRoot = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_left, null, false);
        initView();
        return mRoot;
    }

    RecyclerView rcl_left = null;
    LeftAdapter mAdapter = null;
    private ArrayList<LeftEntity> mList = new ArrayList<>();
    LinearLayoutManager lm;

    private void initView() {
        rcl_left = mRoot.findViewById(R.id.rcl_left);

        lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcl_left.setLayoutManager(lm);

        getList();
        mAdapter = new LeftAdapter(mList, mClick);
        rcl_left.setAdapter(mAdapter);

    }

    private int prevPosition = -1;
    private ClickInterface mClick = new ClickInterface() {
        @Override
        public void onClick(int position) {
            mList.get(position).selected = true;
            if (prevPosition != -1) {
                mList.get(prevPosition).selected = false;
                View v = lm.findViewByPosition(prevPosition);
                if (v != null) {
                    v.findViewById(R.id.rlt_click).setSelected(false);
                }
            }
            prevPosition = position;
        }
    };

    public void notifyOnSizeChange() {
        mAdapter.notifyDataSetChanged();
    }

    private void getList() {
        for (int i = 0; i < 125; i++) {
            LeftEntity left = new LeftEntity();
            left.title = "Title for item № " + i;
            left.selected = false;
            mList.add(left);
        }
    }
}
