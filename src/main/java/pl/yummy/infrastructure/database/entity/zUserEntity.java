package pl.yummy.infrastructure.database.entity;

import java.time.OffsetDateTime;

public class zUserEntity {

    private Integer userId;
    private String email;
    private String salt;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime lastLogin;
    private OffsetDateTime failedLoginAttempts;
    private OffsetDateTime lockedUntil;
    private String passwordResetToken;
    private String passwordResetExpiration;
}
