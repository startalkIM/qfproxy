package com.qunar.qfproxy.model;

/**
 * Author : mingxing.shao
 * Date : 16-2-19
 * email : mingxing.shao@qunar.com
 */
public class Emotion {
    private String packageName;
    private String shortcut;
    private String orgName;
    private byte[] orgFile;
    private String fixedName;
    private byte[] fixedFile;

    public Emotion() {
    }

    public Emotion(String packageName, String shortcut, String orgName, String fixedName) {
        this.packageName = packageName;
        this.shortcut = shortcut;
        this.orgName = orgName;
        this.fixedName = fixedName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public byte[] getOrgFile() {
        return orgFile;
    }

    public void setOrgFile(byte[] orgFile) {
        this.orgFile = orgFile;
    }

    public String getFixedName() {
        return fixedName;
    }

    public void setFixedName(String fixedName) {
        this.fixedName = fixedName;
    }

    public byte[] getFixedFile() {
        return fixedFile;
    }

    public void setFixedFile(byte[] fixedFile) {
        this.fixedFile = fixedFile;
    }
}
