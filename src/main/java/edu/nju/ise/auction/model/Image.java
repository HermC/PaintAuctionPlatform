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
@Table(name = "image")
public class Image {

    public static final Integer AVAILABLE = 0;
    public static final Integer ORDERED = 1;
    public static final Integer SAILED = 2;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String src;

    @Column(nullable = false)
    private Integer available;

}
