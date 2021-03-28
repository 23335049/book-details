package com.wangn.dataloader;

/**
 * @author wangxiongfei
 * @version 1.0.0
 * @create 2021/3/27
 **/
public class User {

    private int id;

    private int invitedBy;

    public User(int id, int invitedBy) {
        this.id = id;
        this.invitedBy = invitedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(int invitedBy) {
        this.invitedBy = invitedBy;
    }
}
