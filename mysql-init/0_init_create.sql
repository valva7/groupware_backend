CREATE TABLE role (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL,
    created_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


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

    role_id SMALLINT, -- RoleEntity FK

    project_active_yn TINYINT(1) NOT NULL DEFAULT 0,
    status VARCHAR(20) NOT NULL,
    hire_dt DATE NOT NULL,
    expiration_dt DATE,

    password VARCHAR(255) NOT NULL,
    profile_image_url VARCHAR(255),

    created_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE INDEX idx_member_id ON member (member_id);
