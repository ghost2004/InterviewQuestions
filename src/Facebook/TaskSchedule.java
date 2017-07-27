package Facebook;
/*
 * We have a list of tasks, they will be run in sequence. For each same tasks, there is a cool down time in the CPU
 * 
 * Given a task sequence and interval between 2 same tasks, return the running time of the tasks
 * 
 * For Example , the interval for task B is 3,  the running time of task list BB will be 5 ( 1+ 3(cool down) + 1)
 * 
 * Follow up: Given a task list and interval, return the sequence that requires minimum time.
 */
import java.util.*;
public class TaskSchedule {
    // Implementation with Hash Map
    public int runningTime(int tasks[], int interval[]) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer> ();
        int time = 1;
        map.put(tasks[0], 1);
        for (int i = 1; i < tasks.length; i++) {
            Integer last = map.get(tasks[i]);
            if (last == null || last < time - interval[tasks[i]]) {
                time++;
                
            } else {
                time = last + interval[tasks[i]] + 1;
                
            }
            map.put(tasks[i], time);
            
        }
        return time;
    }
    
    /* 
     * a little bit changes
     * 
     * The interval for all tasks are same, implemented in linked list
     * 
     */
    
    private class TaskPair{
        int timestamp;
        int taskId;
        
        public TaskPair(int id, int time) {
            timestamp = time;
            taskId = id;
        }
    }
    
    public int runTime(int tasks[], int interval) {
        int time =1;
        LinkedList<TaskPair> cur = new LinkedList<TaskPair>();
        LinkedList<TaskPair> next;
        cur.add(new TaskPair(tasks[0], time));
        
        for (int i = 1; i < tasks.length; i++) {
            next = new LinkedList<TaskPair>();
            boolean matched = false;
            while (!cur.isEmpty()) {
                TaskPair t = cur.poll();
                if (t.timestamp + interval > time)  {
                    if (t.taskId == tasks[i]) {
                        time = t.timestamp + interval + 1;
                        matched = true;
                    } else {
                        next.offer(t);
                    }
                }
            }
            
            if (!matched) {
                time++;
            }
            
            next.offer(new TaskPair(tasks[i],time));
            
            cur = next;
            
        }
        return time;
    }
    public static void main(String args[]) {
        TaskSchedule sch = new TaskSchedule();
        int task1[] = {1, 1, 2, 1};
        int interval1 = 2;
        
        System.out.println(sch.runTime(task1, interval1));
    }
}
