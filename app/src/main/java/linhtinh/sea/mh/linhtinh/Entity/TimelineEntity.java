package linhtinh.sea.mh.linhtinh.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WIN-HAIVM on 6/29/17.
 */

public class TimelineEntity implements Parcelable {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("image_url")
    public String image_url;

    @SerializedName("point")
    public int point;

    @SerializedName("created")
    public long created;

    @SerializedName("display_end_date")
    public long display_end_date;

    @SerializedName("type")
    public String type;

    public static final String TIMELINE_TYPE_1 = "exchange";
    public static final String TIMELINE_TYPE_2 = "getpoint";


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.image_url);
        dest.writeInt(this.point);
        dest.writeLong(this.created);
        dest.writeLong(this.display_end_date);
        dest.writeString(this.type);
    }

    public TimelineEntity() {
    }

    protected TimelineEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.image_url = in.readString();
        this.point = in.readInt();
        this.created = in.readLong();
        this.display_end_date = in.readLong();
        this.type = in.readString();
    }

    public static final Creator<TimelineEntity> CREATOR = new Creator<TimelineEntity>() {
        @Override
        public TimelineEntity createFromParcel(Parcel source) {
            return new TimelineEntity(source);
        }

        @Override
        public TimelineEntity[] newArray(int size) {
            return new TimelineEntity[size];
        }
    };
}
