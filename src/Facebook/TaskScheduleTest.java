package Facebook;

import static org.junit.Assert.*;

import org.junit.Test;

public class TaskScheduleTest {
    public TaskSchedule sch = new TaskSchedule();
    @Test
    public void testRunningTime() {
        int task1[] = {1, 1, 2, 1};
        int interval1[] = { 2, 2 ,2};
        int itvl1 =2;
        int task2[] = {1,2,3,1,2,3};
        int interval2[] = { 3, 3 ,3, 3};
        int itvl2 = 3;
        assertEquals(7, sch.runningTime(task1, interval1));
        assertEquals(7, sch.runningTime(task2, interval2));
        assertEquals(7, sch.runTime(task1, itvl1));
        assertEquals(7, sch.runTime(task2, itvl2));
        
        
    }

}
