package com.hackathon.cfutility.Models;

public class Contestobj {
    public String name,type,phase,starttime,duration;
    public boolean frozen;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPhase() {
        return phase;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getDuration() {
        return duration;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
