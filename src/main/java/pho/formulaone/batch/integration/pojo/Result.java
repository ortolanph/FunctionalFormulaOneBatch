package pho.formulaone.batch.integration.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Result {
    private String number;
    private String position;
    private String positionText;
    private String points;
    @JsonProperty("Driver")
    private Driver driver;
    @JsonProperty("Constructor")
    private Constructor constructor;
    private String grid;
    private String laps;
    private String status;
    @JsonProperty("Time")
    private Time time;
    @JsonProperty("FastestLap")
    private FastestLap fastestLap;

}
