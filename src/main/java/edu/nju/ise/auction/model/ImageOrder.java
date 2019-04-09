package edu.nju.ise.auction.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@NoArgsConstructor
@ToString(callSuper = false, includeFieldNames = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "image_order")
public class ImageOrder {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long userid;

    @Column(nullable = false)
    private Long imageid;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

}
