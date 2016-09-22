package com.example.jinbailiang.application;

import android.app.Application;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yess on 2016/6/15.
 */


//corePoolSize： 线程池维护线程的最少数量
//        maximumPoolSize：线程池维护线程的最大数量
//        keepAliveTime： 线程池维护线程所允许的空闲时间
//        unit： 线程池维护线程所允许的空闲时间的单位
//        workQueue： 线程池所使用的缓冲队列
//        handler： 线程池对拒绝任务的处理策略
public class MyApplication extends Application {
    private  static ThreadPoolExecutor threadPoolExecutor;
    @Override
    public void onCreate() {
        super.onCreate();
        threadPoolExecutor = new ThreadPoolExecutor(2, 4, 3,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.DiscardOldestPolicy());
}

    public  static ThreadPoolExecutor getThreadPoolExecutor(){
        synchronized (MyApplication.class) {
            if (threadPoolExecutor == null) {
                threadPoolExecutor = new ThreadPoolExecutor(2, 4, 3,
                        TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
                        new ThreadPoolExecutor.DiscardOldestPolicy());
            }
        }
        return threadPoolExecutor;
    }


}

