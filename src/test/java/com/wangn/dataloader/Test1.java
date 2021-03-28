package com.wangn.dataloader;

import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author wangxiongfei
 * @version 1.0.0
 * @create 2021/3/27
 **/
public class Test1 {

    private UserDao userDao = new UserDao();

    @Test
    void four_around() {
        User user1 = userDao.findById(1);
        User user1By = userDao.findById(user1.getInvitedBy());
        User user2 = userDao.findById(2);
        User user2By = userDao.findById(user1.getInvitedBy());
    }

    @Test
    void four_around_by_data_loader() {
        BatchLoader<Integer, User> userBatchLoader = userIds ->
                CompletableFuture.supplyAsync(() -> userDao.findById(userIds));
        DataLoader<Integer, User> userLoader = DataLoader.newDataLoader(userBatchLoader);
        userLoader.load(1).thenAccept(user -> {
            System.out.println(Thread.currentThread() + "load: " + user.getId());
            userLoader.load(user.getInvitedBy()).thenAccept(byUser -> {
                System.out.println(Thread.currentThread() + "by: " + byUser.getId());
            });
        });
        userLoader.load(4).thenAccept(user -> {
            System.out.println(Thread.currentThread() + "load: " + user.getId());
            userLoader.load(user.getInvitedBy()).thenAccept(byUser -> {
                System.out.println(Thread.currentThread() + "by: " + byUser.getId());
            });
        });
        userLoader.dispatchAndJoin();
    }
}
