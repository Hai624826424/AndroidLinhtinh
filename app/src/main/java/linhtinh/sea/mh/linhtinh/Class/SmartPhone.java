package linhtinh.sea.mh.linhtinh.Class;

/**
 * Created by WIN-HAIVM on 1/9/18.
 */

public abstract class SmartPhone {
    public static final String OS_ANDROID = "android";
    public static final String OS_IOS = "ios";
    public static final String OS_WINDOW = "window";

    private int version = 0;
    private String cpu = "";

    public SmartPhone() {

    }

    public void setCPU(String cpu) {
        this.cpu = cpu;
    }

    public String getCPU() {
        return cpu;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }
    //-------------------------------------------

    public String getPrintAction(){
        return "I print to screen";
    }
    public abstract String getOS();
}
