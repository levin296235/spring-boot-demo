package com.megvii.springboot.entity;

public class BaseData {

    private String udpData;
    private String deviceIdPk;
    private String devsignature;
    private String packetType;
    private String udpPacketType;
    private String deviceId;
    private String eventTime;

    public String getUdpData() {
        return udpData;
    }

    public void setUdpData(String udpData) {
        this.udpData = udpData;
    }

    public String getDeviceIdPk() {
        return deviceIdPk;
    }

    public void setDeviceIdPk(String deviceIdPk) {
        this.deviceIdPk = deviceIdPk;
    }

    public String getDevsignature() {
        return devsignature;
    }

    public void setDevsignature(String devsignature) {
        this.devsignature = devsignature;
    }

    public String getPacketType() {
        return packetType;
    }

    public void setPacketType(String packetType) {
        this.packetType = packetType;
    }

    public String getUdpPacketType() {
        return udpPacketType;
    }

    public void setUdpPacketType(String udpPacketType) {
        this.udpPacketType = udpPacketType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
