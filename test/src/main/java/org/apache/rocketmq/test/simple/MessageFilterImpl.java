package org.apache.rocketmq.test.simple;

import org.apache.rocketmq.common.filter.FilterContext;
import org.apache.rocketmq.common.filter.MessageFilter;
import org.apache.rocketmq.common.message.MessageExt;

public class MessageFilterImpl implements MessageFilter {
    @Override
    public boolean match(MessageExt msg, FilterContext context) {
        String a = msg.getUserProperty("a");
        if (a != null) {
            int i = Integer.parseInt(a);
            if (i >= 0 && i <= 3) {
                return true;
            }
        }
        return false;
    }
}
