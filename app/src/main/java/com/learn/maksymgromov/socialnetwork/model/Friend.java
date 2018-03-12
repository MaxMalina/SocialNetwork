package com.learn.maksymgromov.socialnetwork.model;

public class Friend {
    private String mName;
    private String mSurname;
    private String mPhoneNumber;

    public Friend(String mName, String mSurname, String mPhoneNumber) {
        this.mName = mName;
        this.mSurname = mSurname;
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSurname() {
        return mSurname;
    }

    public void setmSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }
}
