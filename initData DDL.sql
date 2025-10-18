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


-- ========================
-- 세부 권한
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('DETAIL_ROLE', 'PRJ_MGMT', '프로젝트 관리 권한', '프로젝트 관리 관련 권한', TRUE, 1);

-- ========================
-- 전자결재
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('APPROVAL_TYPE', 'VAC', '휴가', '휴가 결재', TRUE, 1),
    ('APPROVAL_TYPE', 'PUR_REQ', '구매요청서', '구매 요청서 결재', TRUE, 2);

-- ========================
-- 결재 상태
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('APPROVAL_STATUS', 'WAIT', '대기중', '결재 대기 상태', TRUE, 1),
    ('APPROVAL_STATUS', 'PROG', '진행중', '결재 진행 중', TRUE, 2),
    ('APPROVAL_STATUS', 'APPR', '승인', '결재 승인', TRUE, 3),
    ('APPROVAL_STATUS', 'REJT', '반려', '결재 반려', TRUE, 4),
    ('APPROVAL_STATUS', 'TEMP', '임시저장', '결재 임시 저장', TRUE, 5);

-- ========================
-- 결재선 종류
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('APPROVAL_LINE', 'APRV', '결재자', '실제 결재 담당자', TRUE, 1),
    ('APPROVAL_LINE', 'REF', '참조자', '참조만 하는 결재선', TRUE, 2);

-- ========================
-- 휴가 유형
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('VACATION_TYPE', 'ANL', '연차', '연차 휴가', TRUE, 1),
    ('VACATION_TYPE', 'SCK', '병가', '병가', TRUE, 2),
    ('VACATION_TYPE', 'ALT', '대체 휴가', '대체 휴가', TRUE, 3),
    ('VACATION_TYPE', 'MAT', '출산휴가', '출산 휴가', TRUE, 4);

-- ========================
-- 게시물 상태
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('POST_STATUS', 'NORM', '정상', '정상 게시물', TRUE, 1),
    ('POST_STATUS', 'REPT', '신고', '신고된 게시물', TRUE, 2),
    ('POST_STATUS', 'RESL', '해결', '문제 해결된 게시물', TRUE, 3),
    ('POST_STATUS', 'DEL', '삭제', '삭제된 게시물', TRUE, 4);

-- ========================
-- 메뉴얼 카테고리
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('MANUAL', 'PROC', '업무 프로세스', '업무 프로세스 관련 메뉴얼', TRUE, 1),
    ('MANUAL', 'SYS', '시스템 사용법', '시스템 사용법 메뉴얼', TRUE, 2),
    ('MANUAL', 'ETC', '기타', '기타 메뉴얼', TRUE, 3);

-- ========================
-- 사내 위키 카테고리
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('WIKI', 'POL', '정책 및 규정', '회사 정책 및 규정', TRUE, 1),
    ('WIKI', 'TECH', '기술 문서', '기술 관련 문서', TRUE, 2),
    ('WIKI', 'COMP', '회사 소개', '회사 소개 문서', TRUE, 3);

-- ========================
-- 부서
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('DEPT', 'EXEC', '경영진', '경영진 부서', TRUE, 1),
    ('DEPT', 'DEV', '개발팀', '개발팀', TRUE, 2);

-- 부서 하위
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('DEV', 'LEAD', '리더팀', '리더팀', TRUE, 1),
    ('DEV', 'ENG', '엔지니어팀', '엔지니어팀', TRUE, 2);

-- ========================
-- 비품/자산
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('ASSET', 'EQUIP', '장비', '장비 분류', TRUE, 1);

-- 장비 하위
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('EQUIP', 'PC', 'PC', 'PC 장비', TRUE, 1),
    ('EQUIP', 'KEYB', '키보드', '키보드 장비', TRUE, 2),
    ('EQUIP', 'MOUSE', '마우스', '마우스 장비', TRUE, 3),
    ('EQUIP', 'CABLE', '연결 부품', '연결 부품 장비', TRUE, 4);

-- ========================
-- 자산 상태
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('ASSET_STATUS', 'USE', '사용중', '사용중 상태', TRUE, 1),
    ('ASSET_STATUS', 'AVL', '사용 가능', '사용 가능한 상태', TRUE, 2),
    ('ASSET_STATUS', 'FIX', '수리중', '수리중 상태', TRUE, 3);

-- ========================
-- 프로젝트 상태
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('PROJECT_STATUS', 'END', '종료', '프로젝트 종료', TRUE, 1),
    ('PROJECT_STATUS', 'PROG', '진행중', '프로젝트 진행중', TRUE, 2);

-- ========================
-- 재직 상태
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('EMPLOYMENT', 'WORK', '재직중', '재직중 상태', TRUE, 1),
    ('EMPLOYMENT', 'MATLV', '출산 휴가', '출산 휴가 상태', TRUE, 2),
    ('EMPLOYMENT', 'RET', '퇴사', '퇴사 상태', TRUE, 3);

-- ========================
-- 투표 유형
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('VOTE', 'SINGLE', '단일 선택', '단일 선택 투표', TRUE, 1),
    ('VOTE', 'MULTI', '복수 선택', '복수 선택 투표', TRUE, 2);

-- ========================
-- 알림 유형
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('NOTIFY', 'APPR', '결재', '결재 알림', TRUE, 1),
    ('NOTIFY', 'PRJ', '프로젝트', '프로젝트 알림', TRUE, 2),
    ('NOTIFY', 'BOARD', '게시판', '게시판 알림', TRUE, 3),
    ('NOTIFY', 'VOTE', '투표', '투표 알림', TRUE, 4);

-- ========================
-- 직급 유형
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('POSITION', 'CEO', '대표이사', '회사의 최고 경영자', TRUE, 1),
    ('POSITION', 'CFO', '재무이사', '재무 관련 책임자', TRUE, 2),
    ('POSITION', 'EXD', '상무이사', '상무이사 직급', TRUE, 3),
    ('POSITION', 'EXV', '전무이사', '전무이사 직급', TRUE, 4),
    ('POSITION', 'CTO', '기술이사', '기술 관련 책임자', TRUE, 5),
    ('POSITION', 'BD', '부장', '부서 책임자', TRUE, 6),
    ('POSITION', 'CJ', '차장', '팀 부책임자', TRUE, 7),
    ('POSITION', 'GJ', '과장', '팀 담당 관리자', TRUE, 8),
    ('POSITION', 'DR', '대리', '업무 담당자', TRUE, 9),
    ('POSITION', 'ENG', '사원', '일반 직원', TRUE, 10),
    ('POSITION', 'INT', '인턴', '인턴 사원', TRUE, 11);
