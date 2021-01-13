package com.example.gcek;

public class UserData {
    String GCEKID,Name,PhoneNo,ProfileImage,branch,batch;

    public UserData(String GCEKID, String name, String phoneNo, String profileImage, String branch, String batch) {
        this.GCEKID = GCEKID;
        Name = name;
        PhoneNo = phoneNo;
        ProfileImage = profileImage;
        this.branch = branch;
        this.batch = batch;
    }

    public UserData() {
    }

    @Override
    public String toString() {
        return "UserData{" +
                "GCEKID='" + GCEKID + '\'' +
                ", Name='" + Name + '\'' +
                ", PhoneNo='" + PhoneNo + '\'' +
                ", ProfileImage='" + ProfileImage + '\'' +
                ", branch='" + branch + '\'' +
                ", batch='" + batch + '\'' +
                '}';
    }

    public String getGCEKID() {
        return GCEKID;
    }

    public void setGCEKID(String GCEKID) {
        this.GCEKID = GCEKID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
