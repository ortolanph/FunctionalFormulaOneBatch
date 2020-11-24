package pho.formulaone.batch.integration.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Constructor {
    private String constructorId;
    private String url;
    private String name;
    private String nationality;
}
