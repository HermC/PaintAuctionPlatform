package edu.nju.ise.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@ToString(exclude = {"user"}, callSuper = false, includeFieldNames = true)
@Entity
@Table(name = "role")
public class Role {

    public enum Type {
        ADMIN {
            @Override
            public String toString() {
                return "ADMIN";
            }
        }, USER {
            @Override
            public String toString() {
                return "USER";
            }
        }
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JsonIgnore
    @JoinColumn(name = "userid")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Type role;

}
