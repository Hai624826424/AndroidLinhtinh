package linhtinh.sea.mh.linhtinh.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WIN-HAIVM on 11/23/17.
 */

public class ItemEntity {
    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;
    @SerializedName("content")
    public String content;
    @SerializedName("link")
    public String link;
    @SerializedName("apk")
    public String apk;
}
