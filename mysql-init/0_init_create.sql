-- UTF-8 설정
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
SET collation_connection = 'utf8mb4_unicode_ci';

-- -------------------------------------------------------------------
-- ROLE 테이블
-- -------------------------------------------------------------------
CREATE TABLE roles (
    role_id VARCHAR(50) PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL,
    description VARCHAR(1000)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- -------------------------------------------------------------------
-- MEMBER 테이블
-- -------------------------------------------------------------------
CREATE TABLE member (
    member_id VARCHAR(255) PRIMARY KEY,
    member_name VARCHAR(255) NOT NULL,
    rank_cd VARCHAR(50),

    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address VARCHAR(255),

    emergency_name VARCHAR(50),
    emergency_phone VARCHAR(20),

    status VARCHAR(20) NOT NULL,
    hire_dt DATE NOT NULL,
    expiration_dt DATE,

    password VARCHAR(255) NOT NULL,
    profile_image_url VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- -------------------------------------------------------------------
-- MEMBER_ROLES 테이블
-- -------------------------------------------------------------------
CREATE TABLE member_roles (
    member_id  VARCHAR(255) NOT NULL,
    role_id    VARCHAR(255) NOT NULL,

    PRIMARY KEY (member_id, role_id),
    FOREIGN KEY (member_id) REFERENCES member(member_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;;


-- -------------------------------------------------------------------
-- DEPARTMENT 테이블
-- -------------------------------------------------------------------
CREATE TABLE department (
    code VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    parent_department_code VARCHAR(10),   -- self-reference FK
    leader_id VARCHAR(50),                -- MemberEntity PK 타입
    description VARCHAR(255),

    PRIMARY KEY (code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;;

-- Self Reference FK: parent_department_code → department.code
ALTER TABLE department
    ADD CONSTRAINT fk_department_parent
        FOREIGN KEY (parent_department_code)
            REFERENCES department(code);

-- Leader FK: leader_id → member.member_id
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
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;;

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
    menu_id     VARCHAR(255) PRIMARY KEY,
    menu_name   VARCHAR(255) NOT NULL,
    path        VARCHAR(255) NOT NULL,
    sequence    INT NOT NULL,
    active_yn   TINYINT(1) NOT NULL DEFAULT 1,
    parent_id   VARCHAR(255),

    FOREIGN KEY (parent_id) REFERENCES menu(menu_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;;


-- -------------------------------------------------------------------
-- SCREEN 테이블
-- -------------------------------------------------------------------
CREATE TABLE screen (
    screen_id VARCHAR(255) NOT NULL,
    screen_name VARCHAR(255) NOT NULL,
    path VARCHAR(255) NOT NULL,
    menu_id VARCHAR(255),

    PRIMARY KEY (screen_id),
    CONSTRAINT fk_screen_menu FOREIGN KEY (menu_id) REFERENCES menu(menu_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;;


-- -------------------------------------------------------------------
-- MENU_ROLES 테이블
-- -------------------------------------------------------------------
CREATE TABLE menu_roles (
    menu_id VARCHAR(255) NOT NULL,
    role_id VARCHAR(255) NOT NULL,

    PRIMARY KEY (menu_id, role_id),
    CONSTRAINT fk_menu_roles_menu FOREIGN KEY (menu_id) REFERENCES menu(menu_id),
    CONSTRAINT fk_menu_roles_role FOREIGN KEY (role_id) REFERENCES roles(role_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;;


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
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;;

