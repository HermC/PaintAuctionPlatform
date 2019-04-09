package edu.nju.ise.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@ToString(exclude = { "lot" }, callSuper = false, includeFieldNames = true)
@Entity
@Table(name = "lot_img")
public class LotImg {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JsonIgnore
    @JoinColumn(name = "lotid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lot lot;

    @Column(name = "img_src", nullable = false)
    private String imgSrc;

}
