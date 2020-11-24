package pho.formulaone.batch.integration.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Location {
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("long")
    private String longitude;
    private String locality;
    private String country;
}
