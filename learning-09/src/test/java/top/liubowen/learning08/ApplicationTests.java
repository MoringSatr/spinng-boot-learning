package top.liubowen.learning08;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private AsyncTaskService asyncTaskService;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 100; i++) {
            long befro = new Date().getTime();
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
            long after = new Date().getTime();
            System.err.println("exc time : " + (after - befro) + " ms");
        }
    }

}
