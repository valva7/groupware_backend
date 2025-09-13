insert into role(role_name, reg_dt, upd_dt) values('ROLE_USER', now(), now());
insert into role(role_name, reg_dt, upd_dt) values('ROLE_ADMIN'), (now(), now());

CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    memberId VARCHAR(255) NOT NULL UNIQUE,
    memberName VARCHAR(255) NOT NULL,
    rankName VARCHAR(50),
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address VARCHAR(255),
    emergency_name VARCHAR(50),
    emergency_phone VARCHAR(20),
    project_active_yn BIT NOT NULL,
    push_token VARCHAR(255),
    push_yn BIT NOT NULL,
    status VARCHAR(20) NOT NULL,
    hire_dt DATE NOT NULL,
    expiration_dt DATE,
    password VARCHAR(255) NOT NULL,
    profileImageUrl VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    INDEX idx_memberId(memberId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
