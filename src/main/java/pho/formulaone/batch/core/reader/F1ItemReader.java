package pho.formulaone.batch.core.reader;

import lombok.extern.slf4j.Slf4j;
import pho.formulaone.batch.core.beans.F1InputData;

import org.springframework.batch.item.ItemReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class F1ItemReader implements ItemReader<F1InputData> {

    private List<F1InputData> data;
    private int index;
    private static final String IGNORE_PATTERN = "#";

    public F1ItemReader() {
        try {
            URI uri = getClass().getResource("/allf1seasons.csv").toURI();

            data = Files.lines(Paths.get(uri))
                    .filter(line -> !line.startsWith(IGNORE_PATTERN))
                    .map(new F1InputDataMapper())
                    .collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public F1InputData read() throws Exception {
        F1InputData nextData = null;

        log.info("=[READER]=======================================================");
        if(index < data.size()) {
            nextData = data.get(index);
            log.info("Reading input file for season {} with {} races", nextData.getSeason(), nextData.getRaces());
            index++;
        }

        if(nextData == null) {
            log.info("Data retrieval complete");
        }

        return nextData;
    }
}
