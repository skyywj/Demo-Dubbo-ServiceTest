package com.sky.hrpro.notify;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.ImmutableMap;
import com.sky.hrpro.util.JsonUtils;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 事件抽象类
 * @Author: CarryJey
 * @Date: 2018/10/1 21:59:14
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "java_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ServerChangeEvent.class),
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractEvent <T extends AbstractEvent<T>>{

    //事件发起人ID
    private long fromUserId;
    //事件接受人ID
    private long toUserId;

    //事件变更id
    private String eventId ;

    /**
     * DEMO事件类型
     * 常量定义注意要大写
     */
    static final String EVENT_TYPE_DEMO = "demo";
    /**
     * “服务端数据发生变更” 的事件类型
     */
    static final String EVENT_TYPE_SERVER_CHANGE = "sc";

    /**
     * 消息生命周期，单位：秒
     *
     * <p>只有系统 push 支持持久化消息
     */
    private int lifetime;

    private Map<String, Object> systemPushMessageCache;

    public AbstractEvent(){
        //暂时先写死，应该写成动态监听获取
        this.fromUserId = 001;
        this.eventId = "event1";
    }

    /**
     * 事件类型
     */
    public abstract String getType();

    @JsonGetter("eid")
    public String getEventId() {
        return eventId;
    }

    @JsonSetter("eid")
    public T setEventId(String eventId) {
        this.eventId = eventId;
        return thisT();
    }

    @JsonGetter("fid")
    public long getFromUserId() {
        return fromUserId;
    }

    @JsonSetter("fid")
    public T setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
        return thisT();
    }

    @JsonGetter("tid")
    public long getToUserId() {
        return toUserId;
    }

    @JsonSetter("tid")
    public T setToUserId(long toUserId) {
        this.toUserId = toUserId;
        return thisT();
    }


    public int getLifetime() {
        return lifetime;
    }

    public T setLifetime(int lifetime) {
        this.lifetime = lifetime;
        return thisT();
    }

    protected T thisT() {
        @SuppressWarnings("unchecked")
        T thisT = (T) this;
        return thisT;
    }

    public Map<String, Object> toSystemPushMessage() {
        if (systemPushMessageCache != null) {
            return systemPushMessageCache;
        }

        Map<String, String> map = toMap();

        systemPushMessageCache = ImmutableMap.copyOf(map);
        return systemPushMessageCache;
    }

    public Map<String, String> toMap() {
        Map<String, String> stringMap =
                JsonUtils.beanToMap(this)
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(it -> String.valueOf(it.getKey()), it -> String.valueOf(it.getValue())));

        excludeFields(stringMap);
        return stringMap;
    }

    /**
     * 移除无用字段
     */
    protected void excludeFields(Map<String, ?> map) {
        map.remove("lifetime");
        map.remove("java_type");
        map.remove("accid");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractEvent{");
        sb.append("eventId='").append(eventId).append("/'");
        sb.append(", fromUserId=").append(fromUserId);
        sb.append(", toUserId=").append(toUserId);
        sb.append(", lifetime=").append(lifetime);
        sb.append('}');
        return sb.toString();

    }
}
