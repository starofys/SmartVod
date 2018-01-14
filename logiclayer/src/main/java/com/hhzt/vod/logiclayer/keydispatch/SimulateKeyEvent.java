package com.hhzt.vod.logiclayer.keydispatch;

import android.app.Instrumentation;

/**
 * Created by wujichang on 2018/1/14.
 */

public class SimulateKeyEvent {

    /**
     * 模拟系统按键。
     *
     * @param keyCode
     */
    public static void onKeyEvent(final int keyCode) {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
