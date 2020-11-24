package pho.formulaone.batch.integration.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Circuit {
    private String circuitId;
    private String url;
    private String circuitName;
    @JsonProperty("Location")
    private Location location;

}
