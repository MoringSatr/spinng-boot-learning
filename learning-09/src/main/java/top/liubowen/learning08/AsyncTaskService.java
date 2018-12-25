package top.liubowen.learning08;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncTaskService {

    @Async
    public void executeAsyncTask(Integer n) {
        log.info("executeAsyncTask 异步任务执行：" + n);
        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Async
    public void executeAsyncTaskPlus(Integer n) {
        log.info("executeAsyncTask 异步任务执行+1：" + (n + 1));
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}