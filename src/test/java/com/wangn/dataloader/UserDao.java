package com.wangn.dataloader;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangxiongfei
 * @version 1.0.0
 * @create 2021/3/27
 **/
public class UserDao {

    public static final User[] users = new User[10];

    public static final int STATIC_COST = 30;
    public static final int PER_COST = 1;

    static {
        for (int i = 0; i < users.length; i++) {
            int id = i + 1, byId = id > 1 ? id - 1 : users.length;
            users[i] = new User(id, byId);
        }
    }

    public User findById(int userId) {
        cost(STATIC_COST);
        return getUserById(userId);
    }

    public List<User> findById(List<Integer> userIds) {
        cost(STATIC_COST);
        return userIds.stream().map(this::getUserById).collect(Collectors.toList());
    }

    private User getUserById(int userId) {
        cost(PER_COST);
        return userId > users.length ? null : users[userId - 1];
    }

    private void cost(int millis) {
        try {
            Thread.sleep(millis);
            System.out.println(Thread.currentThread() + "cost:" + millis + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
