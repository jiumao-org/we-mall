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
    
    static {
        int nThreads = Runtime.getRuntime().availableProcessors();
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

}
