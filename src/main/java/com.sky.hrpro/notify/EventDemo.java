package com.sky.hrpro.notify;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Map;

/**
 *  事件定义类模板
 * @Author: CarryJey
 * @Date: 2018/10/1 21:55:20
 */
public class EventDemo extends AbstractEvent<EventDemo>{
    private static final Logger logger=  LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private String eventName;

    public EventDemo() {}


    public EventDemo(String eventName){
        this.eventName = eventName;
    }

    @Override
    public String getType() {
        return EVENT_TYPE_DEMO;
    }

    @JsonGetter("dt")
    public String  getEventName() {
        return eventName;
    }

    @JsonSetter("dt")
    public EventDemo setEventName(String  deviceType) {
        this.eventName = eventName;
        return this;
    }

    @Override
    public String toString() {
        return "EventDemo{" + "eventName=" + eventName + " } " + super.toString();
    }

    @Override
    protected void excludeFields(Map<String, ?> map) {
        super.excludeFields(map);
        map.remove("fid");
    }
}
