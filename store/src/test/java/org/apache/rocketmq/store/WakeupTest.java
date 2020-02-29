package org.apache.rocketmq.store;

import java.util.HashMap;
import java.util.Map;

public class WakeupTest {

    public static void main(String[] args) {
        Map<String, Boolean> waitingThreadTable = new HashMap<>();
        waitingThreadTable.put("1", false);
        waitingThreadTable.put("2", true);
        boolean needNotify = false;

        for (Boolean value : waitingThreadTable.values()) {
            needNotify = needNotify || !value;
            value = true;
        }

        if (needNotify) {

        }
    }
}
