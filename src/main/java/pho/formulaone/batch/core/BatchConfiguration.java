package pho.formulaone.batch.core;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pho.formulaone.batch.core.beans.F1InputData;
import pho.formulaone.batch.core.processor.F1ItemProcessor;
import pho.formulaone.batch.core.reader.F1ItemReader;
import pho.formulaone.batch.core.tasklet.DataCleanerTasklet;
import pho.formulaone.batch.core.tasklet.FinishingStatusTransformationTasklet;
import pho.formulaone.batch.core.writer.F1ItemWriter;
import pho.formulaone.batch.integration.pojo.ErgastResult;

import java.util.List;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataCleanerTasklet dataCleanerTasklet;

    @Autowired
    private FinishingStatusTransformationTasklet finishingStatusTransformationTasklet;

    @Autowired
    private F1ItemProcessor processor;

    @Autowired
    private F1ItemWriter writer;

    public F1ItemReader reader() {
        return new F1ItemReader();
    }

    @Bean
    public Job f1BatchJob() {
        return this.jobBuilderFactory
                .get("F1BatchJob")
                .incrementer(new RunIdIncrementer())
                .start(cleanOldDataStep())
                .next(gatherDataStep())
                .next(finishingStatusTransformationStep())
                .build();
    }

    public Step cleanOldDataStep() {
        return stepBuilderFactory
                .get("DataCleaner")
                .tasklet(dataCleanerTasklet)
                .build();
    }

    public Step gatherDataStep() {
        return stepBuilderFactory
                .get("F1DataGatherer")
                .<F1InputData, List<ErgastResult>>chunk(10)
                .reader(reader())
                .processor(processor)
                .writer(writer)
                .build();
    }

    private Step finishingStatusTransformationStep() {
        return stepBuilderFactory
                .get("FisinhingStatusTranformationStep")
                .tasklet(finishingStatusTransformationTasklet)
                .build();
    }
}
