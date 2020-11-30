package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Document("payment") @Data
public class Payment {
    final public static int PENDIENTE=1;
    final public static int PAGADO=2;
    final public static int IMPAGADO=3;
    private @Id String id;
    @Column(precision = 2, length = 5)
    private float amount;
    private String billId;
    @Temporal(value = TemporalType.DATE)
    private Date expiring;
    private int status;
}
