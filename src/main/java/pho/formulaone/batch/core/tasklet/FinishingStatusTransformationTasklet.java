package pho.formulaone.batch.core.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pho.formulaone.batch.persistence.entities.RaceData;
import pho.formulaone.batch.persistence.repositories.RaceDataRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FinishingStatusTransformationTasklet implements Tasklet {

    @Autowired
    private RaceDataRepository repository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Retrieving latecomers");
        List<RaceData> latecomers = repository.findAllByStatusContaining("+");

        LatecomerConverter converter = new LatecomerConverter();

        log.info("Changing Status for {} records", latecomers.size());
        List<RaceData> modified = latecomers
                .stream()
                .map(converter::apply)
                .collect(Collectors.toList());

        log.info("Saving");
        repository.saveAll(modified);

        return RepeatStatus.FINISHED;
    }

}
