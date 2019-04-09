package edu.nju.ise.auction.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@ToString(callSuper = false, includeFieldNames = true)
@Entity
@Table(name = "user_lot")
public class UserLot {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "userid", nullable = false)
    private Long userid;

    @Column(name = "lotid", nullable = false)
    private Long lotid;

}
