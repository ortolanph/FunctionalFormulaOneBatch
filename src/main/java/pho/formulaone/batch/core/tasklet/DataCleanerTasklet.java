package pho.formulaone.batch.core.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pho.formulaone.batch.persistence.repositories.RaceDataRepository;

@Service
@Slf4j
public class DataCleanerTasklet implements Tasklet {

    @Autowired
    private RaceDataRepository repository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Cleaning all the data");
        repository.deleteAll();
        return RepeatStatus.FINISHED;
    }
}
