package cysewska.com.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ola on 2016-08-30.
 */

@Getter
@Setter
@Entity
@Table(name = "INVOICE")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "INVOICE_ID")
    Long id;
    @Column(name = "INVOICE_NAME")
    String name;
    @Column(name = "LANGUAGE_OF_INVOICE")
    String language;
    @Column(name = "DATE_OF_EXHIBIT")
    Date date;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    OrderEntity orderEntity;


}
