-- UTF-8 설정
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
SET collation_connection = 'utf8mb4_unicode_ci';

-- -------------------------------------------------------------------
-- ROLE 테이블
-- -------------------------------------------------------------------
CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- -------------------------------------------------------------------
-- MEMBER 테이블
-- -------------------------------------------------------------------
CREATE TABLE member (
    member_id VARCHAR(255) PRIMARY KEY NOT NULL UNIQUE,
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

    CONSTRAINT fk_member_role FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_member_id ON member (member_id);

-- -------------------------------------------------------------------
-- DEPARTMENT 테이블
-- -------------------------------------------------------------------
CREATE TABLE department (
    code VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    parent_department_code VARCHAR(10),  -- self-reference FK
    leader_id VARCHAR(50),             -- MemberEntity PK 타입에 따라 조정
    description VARCHAR(255),
    PRIMARY KEY (code)
);

-- 인덱스
CREATE INDEX idx_departmentId ON department (code);

-- FK 제약
ALTER TABLE department
    ADD CONSTRAINT fk_department_parent
        FOREIGN KEY (parent_department_id)
            REFERENCES department(code);

ALTER TABLE department
    ADD CONSTRAINT fk_department_leader
        FOREIGN KEY (leader_id)
            REFERENCES member(member_id);



-- -------------------------------------------------------------------
-- DEPARTMENT_MEMBER 테이블
-- -------------------------------------------------------------------
CREATE TABLE department_member (
    member_id VARCHAR(50) NOT NULL,
    department_code VARCHAR(10) NOT NULL,
    PRIMARY KEY (member_id, department_code)
);

-- FK 제약
ALTER TABLE department_member
    ADD CONSTRAINT fk_department_member_member
        FOREIGN KEY (member_id)
            REFERENCES member(member_id);

ALTER TABLE department_member
    ADD CONSTRAINT fk_department_member_department
        FOREIGN KEY (department_code)
            REFERENCES department(code);


-- -------------------------------------------------------------------
-- MENU 테이블
-- -------------------------------------------------------------------
CREATE TABLE menu (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
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
    PRIMARY KEY (group_code, code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

