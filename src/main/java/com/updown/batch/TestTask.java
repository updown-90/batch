package com.updown.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
public class TestTask implements Tasklet, InitializingBean {

    /**
     * execute메소드는 StepContributioin 과 ChunkContext 를 파라미터로 받는다.
     * 최종적으로 RepeatStatus 를 반환하며 이 값은 다음과 같다.
     * FINISHED: 태스크릿이 종료되었음을 나타낸다.
     * CONTINUABLE: 계속해서 태스크를 수행하도록한다.
     * continueIf(condition): 조건에 따라 종료할지 지속할지 결정하는 메소드에 따라 종료/지속을 결정한다.
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        log.info("------------------ Task Execute -----------------");
        log.info("GreetingTask: {}, {}", contribution, chunkContext);

        return RepeatStatus.FINISHED;
    }

    /**
     * 태스크를 수행할때 프로퍼티를 설정하고 난 뒤에 수행되는 메소드
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("----------------- After Properites Sets() --------------");
    }
}