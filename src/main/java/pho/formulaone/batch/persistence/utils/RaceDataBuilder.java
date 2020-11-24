package pho.formulaone.batch.persistence.utils;

import pho.formulaone.batch.persistence.entities.RaceData;

public final class RaceDataBuilder {

    private static final RaceDataBuilder INSTANCE = new RaceDataBuilder();
    private RaceData raceData;

    public static RaceDataBuilder getInstance() {
        return INSTANCE;
    }

    public RaceDataBuilder newRaceData() {
        raceData = new RaceData();
        return this;
    }

    public RaceDataBuilder seasonAndRound(String season, String round) {
        raceData.setSeasonNumber(Integer.parseInt(season));
        raceData.setSeasonRound(Integer.parseInt(round));
        return this;
    }

    public RaceDataBuilder circuit(String raceName, String circuitName, String locality, String country) {
        raceData.setRaceName(raceName);
        raceData.setRaceCircuitName(circuitName);
        raceData.setRaceLocality(locality);
        raceData.setRaceCountry(country);
        return this;
    }

    public RaceDataBuilder pilot(String number, String givenName, String familyName) {
        raceData.setPilotNumber((number == null || number.isEmpty()) ? 0 : Integer.parseInt(number));
        raceData.setPilotName(givenName + " " + familyName);
        return this;
    }

    public RaceDataBuilder results(String grid, String position) {
        raceData.setPilotGrid(Integer.parseInt(grid));
        raceData.setPilotPosition(Integer.parseInt(position));
        return this;
    }

    public RaceDataBuilder constructor(String constructorName) {
        raceData.setConstructorName(constructorName);
        return this;
    }

    public RaceDataBuilder fastestLap(int fastestLapRank) {
        raceData.setFastestLapRank(fastestLapRank);
        return this;
    }

    public RaceDataBuilder withStatus(String status) {
        raceData.setStatus(status);
        return this;
    }

    public RaceData build() {
        return raceData;
    }

}
