package pho.formulaone.batch.core.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pho.formulaone.batch.integration.pojo.ErgastResult;
import pho.formulaone.batch.integration.pojo.FastestLap;
import pho.formulaone.batch.integration.pojo.Race;
import pho.formulaone.batch.integration.pojo.Result;
import pho.formulaone.batch.persistence.repositories.RaceDataRepository;
import pho.formulaone.batch.persistence.utils.RaceDataBuilder;

import java.util.List;

@Component
@Slf4j
public class F1ItemWriter implements ItemWriter<List<ErgastResult>> {

    @Autowired
    RaceDataRepository repository;

    @Override
    public void write(List<? extends List<ErgastResult>> list) {
        RaceDataBuilder builder = new RaceDataBuilder();

        log.info("=[WRITER]=======================================================");

        for (List<ErgastResult> resultList : list) {
            for (ErgastResult ergastResult : resultList) {
                String season = ergastResult.getMrData().getRaceTable().getSeason();
                String round = ergastResult.getMrData().getRaceTable().getRound();

                log.info("Writing season {} race {} data", season, round);

                for (Race race : ergastResult.getMrData().getRaceTable().getRaces()) {

                    String raceName = race.getRaceName();
                    String circuit = race.getCircuit().getCircuitName();
                    String locality = race.getCircuit().getLocation().getLocality();
                    String country = race.getCircuit().getLocation().getCountry();

                    log.info(" - Getting {} - {} at {} ({}) results informaton",
                            raceName, circuit, locality, country);

                    for (Result result : race.getResults()) {
                        String pilotNumber = result.getNumber();
                        String givenName = result.getDriver().getGivenName();
                        String familyName = result.getDriver().getFamilyName();
                        String constructor = result.getConstructor().getName();
                        String grid = result.getGrid();
                        String position = result.getPosition();
                        String status = result.getStatus();
                        int fastestLapRank = getFastestLapRank(result.getFastestLap());

                        log.info("    - [{}] {} {}",
                                pilotNumber, givenName, familyName);

                        builder
                                .newRaceData()
                                .seasonAndRound(season, round)
                                .circuit(raceName, circuit, locality, country)
                                .pilot(pilotNumber, givenName, familyName)
                                .constructor(constructor)
                                .results(grid, position)
                                .fastestLap(fastestLapRank)
                                .withStatus(status);

                        repository.save(builder.build());
                    }
                }
            }
        }
    }

    private int getFastestLapRank(FastestLap fastestLap) {
        return (fastestLap == null)?
                -1 :
                Integer.parseInt(fastestLap.getRank());
    }
}
