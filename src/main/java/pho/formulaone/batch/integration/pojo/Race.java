package pho.formulaone.batch.integration.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Race {
    private String season;
    private String round;
    private String url;
    private String raceName;
    @JsonProperty("Circuit")
    private Circuit circuit;
    private String date;
    private String time;
    @JsonProperty("Results")
    private List<Result> results;
}
