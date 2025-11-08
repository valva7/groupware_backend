-- UTF-8 설정
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
SET collation_connection = 'utf8mb4_unicode_ci';

-- -------------------------------------------------------------------
-- ROLE 테이블
-- -------------------------------------------------------------------
CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL,
    created_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- -------------------------------------------------------------------
-- MEMBER 테이블
-- -------------------------------------------------------------------
CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    member_id VARCHAR(255) NOT NULL UNIQUE,
    member_name VARCHAR(255) NOT NULL,
    rank_cd VARCHAR(50),

    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address VARCHAR(255),

    emergency_name VARCHAR(50),
    emergency_phone VARCHAR(20),

    role_id BIGINT,

    project_active_yn TINYINT(1) NOT NULL DEFAULT 0,
    status VARCHAR(20) NOT NULL,
    hire_dt DATE NOT NULL,
    expiration_dt DATE,

    password VARCHAR(255) NOT NULL,
    profile_image_url VARCHAR(255),

    created_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_member_role FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_member_id ON member (member_id);

-- -------------------------------------------------------------------
-- DEPARTMENT 테이블
-- -------------------------------------------------------------------
CREATE TABLE department (
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_dt           DATETIME(6) NULL,
    updated_dt           DATETIME(6) NULL,
    code                 VARCHAR(10) NOT NULL,
    description          VARCHAR(255),
    name                 VARCHAR(100) NOT NULL,
    leader_id            BIGINT,
    parent_department_id BIGINT,

    CONSTRAINT uk_department_code UNIQUE (code),
    CONSTRAINT uk_department_leader_id UNIQUE (leader_id),
    CONSTRAINT fk_department_parent FOREIGN KEY (parent_department_id) REFERENCES department (id),
    CONSTRAINT fk_department_leader FOREIGN KEY (leader_id) REFERENCES member (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_department_code ON department (code);

-- -------------------------------------------------------------------
-- DEPARTMENT_MEMBER 테이블
-- -------------------------------------------------------------------
CREATE TABLE department_member (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_dt    DATETIME(6) NULL,
    updated_dt    DATETIME(6) NULL,
    member_id     BIGINT NOT NULL,
    department_id BIGINT,

    CONSTRAINT uk_department_member_member_id UNIQUE (member_id),
    CONSTRAINT fk_department_member_department FOREIGN KEY (department_id) REFERENCES department (id),
    CONSTRAINT fk_department_member_member FOREIGN KEY (member_id) REFERENCES member (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- -------------------------------------------------------------------
-- MENU 테이블
-- -------------------------------------------------------------------
CREATE TABLE menu (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_dt DATETIME(6) NULL,
    updated_dt DATETIME(6) NULL,
    active_yn  BIT NOT NULL,
    base_role  VARCHAR(100) NOT NULL,
    icon       VARCHAR(100),
    name       VARCHAR(100) NOT NULL,
    path       VARCHAR(200),
    sequence   INT NOT NULL,
    parent_id  BIGINT,

    CONSTRAINT fk_menu_parent FOREIGN KEY (parent_id) REFERENCES menu (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- -------------------------------------------------------------------
-- COMMON_CODE 테이블
-- -------------------------------------------------------------------
CREATE TABLE common_code (
    group_code  VARCHAR(100) NOT NULL,
    code        VARCHAR(50) NOT NULL,
    code_name   VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    active_yn   BIT NOT NULL,
    sequence    INT NOT NULL,
    created_dt  DATETIME(6) NULL,
    updated_dt  DATETIME(6) NULL,
    PRIMARY KEY (group_code, code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

