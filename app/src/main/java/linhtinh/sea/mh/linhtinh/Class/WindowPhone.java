package linhtinh.sea.mh.linhtinh.Class;

/**
 * Created by WIN-HAIVM on 1/9/18.
 */

public class WindowPhone extends SmartPhone {
    @Override
    public String getOS() {

        return OS_WINDOW;
    }

    public WindowPhone(int version, String cpu) {
        setCPU(cpu);
        setVersion(version);
    }
}
