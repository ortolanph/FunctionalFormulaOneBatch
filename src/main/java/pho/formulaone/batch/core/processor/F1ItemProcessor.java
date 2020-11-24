package pho.formulaone.batch.core.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pho.formulaone.batch.core.beans.F1InputData;
import pho.formulaone.batch.integration.ErgastIntegration;
import pho.formulaone.batch.integration.pojo.ErgastResult;

import org.springframework.batch.item.ItemProcessor;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class F1ItemProcessor implements ItemProcessor<F1InputData, List<ErgastResult>> {

    @Autowired
    private ErgastIntegration ergastIntegration;

    @Override
    public List<ErgastResult> process(F1InputData input) throws Exception {
        List<ErgastResult> results = new ArrayList<>();
        log.info("=[PROCESSOR]====================================================");
        log.info("Processing season {}", input.getSeason());

        for(int race = 1; race <= input.getRaces(); race++) {
            log.info(" - Retrieving data for race {}", race);
            results.add(ergastIntegration.getRaceInformation(input.getSeason(), race));
        }

        return results;
    }
}
