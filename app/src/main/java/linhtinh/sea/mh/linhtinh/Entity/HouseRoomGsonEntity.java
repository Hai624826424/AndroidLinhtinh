package linhtinh.sea.mh.linhtinh.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by WIN-HAIVM-NEW on 11/15/2016.
 */

public class HouseRoomGsonEntity extends BaseEntity {
    @SerializedName("data")
    public ArrayList<HouseData> data;

    public class HouseData {
        @SerializedName("house_id")
        public int house_id;

        @SerializedName("house_name")
        public String house_name;

        @SerializedName("rooms")
        public ArrayList<RoomData> rooms;
    }
    public class RoomData {
        @SerializedName("room_id")
        public int room_id;

        @SerializedName("room_name")
        public String room_name;
    }

}
