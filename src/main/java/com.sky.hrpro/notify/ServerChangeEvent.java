package com.sky.hrpro.notify;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.sky.hrpro.entity.ObjectType;

import java.util.Map;

/**
 * Server 端数据变更事件
 *
 * @author wangjunwei
 * @since 2017-09-21
 */
public class ServerChangeEvent extends AbstractEvent<ServerChangeEvent> {

    private String objectType;

    public ServerChangeEvent() {}

    /**
     * server change 事件都是用户自己行为产生的，所以无需关注 from_user_id
     */
    public ServerChangeEvent(long toUserId, ObjectType objectType) {
        super.setToUserId(toUserId);
        this.objectType = objectType.toString();
    }

    @Override
    public String getType() {
        return EVENT_TYPE_SERVER_CHANGE;
    }

    @JsonGetter("ot")
    public String getObjectType() {
        return objectType;
    }

    @JsonSetter("ot")
    public ServerChangeEvent setObjectType(String objectType) {
        this.objectType = objectType;
        return this;
    }

    @Override
    protected void excludeFields(Map<String, ?> map) {
        super.excludeFields(map);
        map.remove("fid");
    }

    @Override
    public String toString() {
        return "ServerChangeEvent{" + "objectType='" + objectType + '\'' + "} " + super.toString();
    }
}
