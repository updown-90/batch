package com.updown.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobScheduler {

    private final JobLauncher jobLauncher;
    private final Job testJob;

    @Scheduled(cron = "*/10 * * * * ?")
    public void runJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis()) // 유니크 파라미터로 매번 실행되도록
                    .toJobParameters();

            jobLauncher.run(testJob, params);
            log.info("Job 실행 완료");
        } catch (Exception e) {
            log.error("Job 실행 실패", e);
        }
    }
}
