package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;


@Data @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BillLine {
    private Long visitId;
    private float amount;
}
