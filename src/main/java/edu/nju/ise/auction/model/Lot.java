package edu.nju.ise.auction.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ToString(exclude = { "lotImgs" }, callSuper = false, includeFieldNames = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "lot")
public class Lot {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(name = "starting_price", nullable = false)
    private Double startingPrice;   // 起拍价

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "starting_time", nullable = false)
    private Date startingTime;      // 开始时间

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "ending_time", nullable = false)
    private Date endingTime;        // 结束时间

    @Column(name = "ending_price", nullable = true)
    private Double endingPrice;     // 成交价

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE}, mappedBy = "lot")
    private List<LotImg> lotImgs;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

}
