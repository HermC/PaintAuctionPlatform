package edu.nju.ise.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ToString(exclude = {"roles"}, callSuper = false, includeFieldNames = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(length = 128, nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(length = 256, nullable = false)
    private String password;

    @Column(length = 128, nullable = false)
    private String email;

    @Column(length = 128, nullable = true)
    private String avatar;

    @JsonIgnore
    @Column(length = 128, nullable = false)
    private String salt;

    @Column(nullable = false)
    private Long ban;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE}, mappedBy = "user")
    private List<Role> roles;

    @JsonIgnore
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

}
