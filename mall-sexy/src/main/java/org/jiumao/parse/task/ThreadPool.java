package org.jiumao.parse.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 执行任务，不需要新开线程
 */
public class ThreadPool {

    private static final ExecutorService THREAD_POOL;
    private static final Thread[] RUN_TASKS;// 执行程序主要任务线程
    
    static {
        int nThreads = Runtime.getRuntime().availableProcessors();
        RUN_TASKS = new Thread[nThreads*2];
        THREAD_POOL = Executors.newFixedThreadPool(nThreads);
    }
    
 
    public static void exec(Runnable command) {
        THREAD_POOL.execute(command);
    }
    
    public static <T> Future<T> exec(Callable<T> task) {
        return THREAD_POOL.submit(task);
    }

    public static ExecutorService getThreadPool() {
        return THREAD_POOL;
    }

    public static Thread[] getRunTasks() {
        return RUN_TASKS;
    }
    
    
}
