package linhtinh.sea.mh.linhtinh.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by WIN-HAIVM-NEW on 11/15/2016.
 */

public class TimelineGsonEntity extends BaseEntity {
    @SerializedName("body")
    public TimelineGsonData body;

    public class TimelineGsonData {
        @SerializedName("total")
        public int total;

        @SerializedName("data")
        public ArrayList<TimelineEntity> data;

        @SerializedName("point")
        public String point;
    }

}
