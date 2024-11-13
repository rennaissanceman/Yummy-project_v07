package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "userLogDataId")
@ToString(of = {"userLogDataId", "passwordHash", "salt", "isActive", "createdAt", "updatedAt", "lastLogin",
        "failedLoginAttempts", "lockedUntil", "passwordResetToken", "passwordResetExpiration"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_log_data")
public class UserLogDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_log_data_id")
    private Integer userLogDataId;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "salt")
    private String salt;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "last_login")
    private OffsetDateTime lastLogin;

    @Column(name = "failed_login_attempts")
    private OffsetDateTime failedLoginAttempts;

    @Column(name = "locked_until")
    private OffsetDateTime lockedUntil;

    @Column(name = "password_reset_token")
    private String passwordResetToken;

    @Column(name = "password_reset_expiration")
    private String passwordResetExpiration;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user_log_data")
    private OwnerEntity owner;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user_log_data")
    private CustomerEntity customer;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user_log_data")
    private CourierEntity courier;


}
