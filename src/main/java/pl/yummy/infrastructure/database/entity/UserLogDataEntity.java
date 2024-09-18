package pl.yummy.infrastructure.database.entity;

import java.time.OffsetDateTime;

public class UserLogDataEntity {

    private Integer userLogDataEntityId;
    private String passwordHash;
    private String salt;
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime lastLogin;
    private OffsetDateTime failedLoginAttempts;
    private OffsetDateTime lockedUntil;
    private String passwordResetToken;
    private String passwordResetExpiration;

}
