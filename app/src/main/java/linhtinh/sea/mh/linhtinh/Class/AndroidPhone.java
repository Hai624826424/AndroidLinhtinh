package linhtinh.sea.mh.linhtinh.Class;

import android.util.Log;

/**
 * Created by WIN-HAIVM on 1/9/18.
 */

public class AndroidPhone extends SmartPhone {

    @Override
    public String getOS() {

        return OS_ANDROID;
    }

    public AndroidPhone(int version, String cpu) {
        setCPU(cpu);
        setVersion(version);
    }
    public float value;

    public void setValue(float value){
        this.value = value;
        getValue();
    }

    public float getValue(){
        return value;
    }
    public void  print(){
        Log.e("print", value+"");
    }

    public String getPrintAction() {
        return "I print over JVM";
    }

}
