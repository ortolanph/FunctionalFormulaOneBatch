package pho.formulaone.batch.core.tasklet;

import pho.formulaone.batch.persistence.entities.RaceData;

import java.util.function.Function;

public class LatecomerConverter implements Function<RaceData, RaceData> {

    @Override
    public RaceData apply(RaceData raceData) {
        RaceData modified = raceData;

        modified.setFinishStatus(raceData.getStatus());
        modified.setStatus("Finished");

        return modified;
    }
}

