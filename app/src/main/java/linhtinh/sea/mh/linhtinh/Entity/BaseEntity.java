package linhtinh.sea.mh.linhtinh.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WIN-HAIVM on 11/21/17.
 */

public class BaseEntity {

    @SerializedName("success")
    public boolean success;

    @SerializedName("error")
    public BaseError error;

    public class BaseError{
        @SerializedName("code")
        public int code;

        @SerializedName("message")
        public String message;
    }
}