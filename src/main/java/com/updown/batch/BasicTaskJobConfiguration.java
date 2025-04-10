package com.updown.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class BasicTaskJobConfiguration {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Bean
    public Tasklet greetingTasklet() {
        return new TestTask();
    }

    /**
     * 위 코드는 step 빈을 등록했다. JobRepository와 PlatformTransactionManager 를 파라미터로 받는다.
     * 스프랭 배치는 보통 데이터소스와 함께 작업하므로 PlatformTransactionManager이 필요하다.
     * StepBuilder를 생성하고, 스텝의 이름을 myStep으로 지정했다.
     * 그리고 이 스텝은 jobRepository에 등록된다.
     * tasklet을 스텝에 추가하고, greetingTasklet() 을 통해서 스탭내 태스크릿을 주입했다.
     * build를 통해서 스텝을 생성하고 빈으로 등록하도록 return한다. .
     * @param jobRepository
     * @param transactionManager
     * @return
     */
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        log.info("------------------ Init myStep -----------------");

        return new StepBuilder("myStep", jobRepository)
                .tasklet(greetingTasklet(), transactionManager)
                .build();
    }

    /**
     * Job을 생성한다. Job은 Step이 필요하며, JobRepository 역시 필요하다.
     * Job은 JobRepository에 등록되게 된다.
     * JobBuilder를 동해서 이름이 myJob인 잡을 생성했다.
     * incrementer은 잡이 지속적으로 실행될때, 잡의 유니크성을 구분할 수 있는 방법을 설정한다.
     * RunIdIncrementer는 잡의 아이디를 실행할때 지속적으로 증가시키면서 유니크한 잡을 실행하게 된다.
     * start(step) 을 통해서 잡의 시작 포인트를 잡는다. 처음시작하는 스텝은 우리가 파라미터로 받은 step을 등록했다.
     * build를 통해서 잡생성하고 빈으로 등록하도록 return한다.
     * @param step
     * @param jobRepository
     * @return
     */
    @Bean
    public Job testJob(Step step, JobRepository jobRepository) {
        log.info("------------------ Init myJob -----------------");
        return new JobBuilder("testJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

}