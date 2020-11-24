package pho.formulaone.batch.core.reader;

import pho.formulaone.batch.core.beans.F1InputData;

import java.util.function.Function;

public class F1InputDataMapper implements Function<String, F1InputData> {
    @Override
    public F1InputData apply(String data) {
        String[] splitted = data.split(";");

        return new F1InputData(
                Integer.parseInt(splitted[0]),
                Integer.parseInt(splitted[1])
        );
    }
}
