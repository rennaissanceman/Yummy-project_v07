package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "userAuthId")
@ToString(of = {"userAuthId", "phone", "email", "passwordHash", "salt", "createdAt", "updatedAt"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_auth")
public class UserAuthEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_auth_id", nullable = false, unique = true)
    private Integer userAuthId;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "salt")
    private String salt;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
    

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user_auth", cascade = CascadeType.ALL)
    private OwnerEntity owner;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user_auth", cascade = CascadeType.ALL)
    private CustomerEntity customer;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user_auth", cascade = CascadeType.ALL)
    private CourierEntity courier;


}
