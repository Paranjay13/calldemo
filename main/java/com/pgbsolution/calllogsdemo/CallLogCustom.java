package com.pgbsolution.calllogsdemo;

/**
 * Created by paran on 5/22/2017.
 */

public class CallLogCustom {


    String id;
    String name;
    long timestamp;
    long duration;
    String filePath;

    @Override
    public String toString() {
        return "CallLogCustom{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", timestamp=" + timestamp +
                ", duration=" + duration +
                ", filePath='" + filePath + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public CallLogCustom() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CallLogCustom(String id, String name, long timestamp, long duration, String filePath, String state) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.duration = duration;
        this.filePath = filePath;
        this.state = state;
    }
}
