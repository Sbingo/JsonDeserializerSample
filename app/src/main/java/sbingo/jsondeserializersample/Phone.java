package sbingo.jsondeserializersample;

import java.util.List;

/**
 * Author: Sbingo
 * Date:   2017/04/23
 */

public class Phone {

    /**
     * 手机大小
     */
    private Size size;
    /**
     * 手机内app列表
     */
    private List<App> apps;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }
}
