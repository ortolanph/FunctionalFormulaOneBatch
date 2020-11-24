package pho.formulaone.batch.integration.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class RaceTable {
    private String season;
    private String round;
    @JsonProperty("Races")
    private List<Race> races;
}
