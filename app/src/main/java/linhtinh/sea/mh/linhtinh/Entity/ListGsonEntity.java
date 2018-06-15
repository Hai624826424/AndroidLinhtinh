package linhtinh.sea.mh.linhtinh.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by WIN-HAIVM on 11/21/17.
 */

public class ListGsonEntity extends BaseEntity {
    @SerializedName("data")
    public ArrayList<ItemEntity> data;


}
