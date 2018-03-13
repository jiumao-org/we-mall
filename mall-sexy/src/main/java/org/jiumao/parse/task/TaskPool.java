package org.jiumao.parse.task;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 任务源应该持久化
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/03/11
 */
public class TaskPool {

    /** 任务源URL:是否被执行 */
    private static final ConcurrentHashMap<Task, AtomicBoolean> TASKS = new ConcurrentHashMap<>();

    public static void addTask(Task t) {
        TASKS.put(t, new AtomicBoolean(false));
    }
    
    public static Task pop() {
        return null;
    }

}
