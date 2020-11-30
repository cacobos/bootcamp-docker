package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import java.util.List;


@Document("bill") @Data
public class Bill {
    final public static int PAYMENT_1=1;
    final public static int PAYMENT_2=2;
    final public static int PAYMENT_3=3;
    final public static int PENDIENTE_PAGO=1;
    final public static int PAGADA_PARCIAL=2;
    final public static int PAGADA=3;
    final public static int IMPAGADA=4;
    private @Id String id;
    private String comment;
    @Column(precision = 2, length = 5)
    private float amount;
    private int payment;
    private Long idCliente;
    private int status;
    private List<BillLine> billLines;
}
