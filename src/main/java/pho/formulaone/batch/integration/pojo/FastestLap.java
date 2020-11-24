package pho.formulaone.batch.integration.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class FastestLap {
    private String rank;
    private String lap;
    @JsonProperty("Time")
    private Time time;
    @JsonProperty("AverageSpeed")
    private AverageSpeed averageSpeed;
}
