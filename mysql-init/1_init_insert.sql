-- role 샘플 데이터
insert into role(role_name, created_dt, updated_dt) values('ROLE_USER', now(), now());
insert into role(role_name, created_dt, updated_dt) values('ROLE_ADMIN' ,now(), now());

-- member 샘플 데이터
INSERT INTO member (
    member_id, member_name, rank_cd,
    email, phone, address,
    emergency_name, emergency_phone,
    role_id, project_active_yn, status,
    hire_dt, expiration_dt,
    password, profile_image_url
) VALUES
      ('kimgleam', '김태욱', 'R002', 'kimgleam@example.com', '010-2345-6789', '서울시 서초구', '이응급', '010-8765-4321', 1, 0, 'ACTIVE', '2023-02-01', NULL, 'password2', 'https://example.com/profile2.png'),
      ('eunHyukChoi', '최은혁', 'R002', '1@example.com', '010-2345-6789', '서울시 서초구', '이응급', '010-8765-4321', 1, 0, 'ACTIVE', '2023-02-01', NULL, 'password2', 'https://example.com/profile2.png'),
      ('heeCheolShin', '신희철', 'R003', '2@example.com', '010-3456-7890', '서울시 송파구', '박응급', '010-7654-3210', 1, 1, 'ACTIVE', '2023-03-01', NULL, 'password3', 'https://example.com/profile3.png'),
      ('dongGeonPark', '박동건', 'R002', '3@example.com', '010-2345-6789', '서울시 서초구', '이응급', '010-8765-4321', 1, 0, 'ACTIVE', '2023-02-01', NULL, 'password2', 'https://example.com/profile2.png'),
      ('junHyukLee', '이준혁', 'R001', '4@example.com', '010-4567-8901', '서울시 강북구', '최응급', '010-6543-2109', 1, 0, 'INACTIVE', '2022-12-01', '2023-12-01', 'password4', 'https://example.com/profile4.png'),
      ('namSooPark', '박남수', 'R002', '5@example.com', '010-5678-9012', '서울시 마포구', '정응급', '010-5432-1098', 1, 1, 'ACTIVE', '2023-04-01', NULL, 'password5', 'https://example.com/profile5.png');


-- 부서 정보
INSERT INTO department (id, parent_department_id, code, name, description, leader_id)
VALUES(1, null, 'EXEC', '경영팀', '경영팀', null),
      (2, null, 'DEV', '개발팀', '개발팀', null),
      (3, 2, 'LEAD', '리더팀', '리더팀', null),
      (4, 2, 'ENG', '엔지니어팀', '엔지니어팀', null);

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
-- 게시물 카테고리
-- ========================
INSERT INTO common_code (group_code, code, code_name, description, active_yn, sequence)
VALUES
    ('POST_CATEGORY', 'NOTICE', '공지사항', '공지사항 게시판', TRUE, 1),
    ('POST_CATEGORY', 'FREE', '자유게시판', '자유 주제 게시판', TRUE, 2),
    ('POST_CATEGORY', 'WORK', '업무공유', '업무 관련 공유 게시판', TRUE, 3),
    ('POST_CATEGORY', 'QNA', '질문답변', '질문과 답변 게시판', TRUE, 4),
    ('POST_CATEGORY', 'SUGGEST', '제안', '개선 제안 게시판', TRUE, 5),
    ('POST_CATEGORY', 'EVENT', '행사안내', '행사 및 이벤트 안내', TRUE, 6),
    ('POST_CATEGORY', 'EDUCATION', '교육자료', '교육 관련 자료', TRUE, 7),
    ('POST_CATEGORY', 'ETC', '기타', '기타 게시판', TRUE, 8);

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
    ('WIKI_CATEGORY', 'WORK_GUIDE', '업무가이드', '업무 가이드 문서', TRUE, 1),
    ('WIKI_CATEGORY', 'SYS_MANUAL', '시스템매뉴얼', '시스템 매뉴얼 문서', TRUE, 2),
    ('WIKI_CATEGORY', 'PROCESS', '프로세스', '프로세스 관련 문서', TRUE, 3),
    ('WIKI_CATEGORY', 'POLICY', '정책및규정', '회사 정책 및 규정', TRUE, 4),
    ('WIKI_CATEGORY', 'TECH', '기술문서', '기술 관련 문서', TRUE, 5),
    ('WIKI_CATEGORY', 'COMPANY', '회사소개', '회사 소개 문서', TRUE, 6),
    ('WIKI_CATEGORY', 'EDUCATION', '교육자료', '교육 관련 자료', TRUE, 7),
    ('WIKI_CATEGORY', 'FAQ', 'FAQ', '자주 묻는 질문', TRUE, 8),
    ('WIKI_CATEGORY', 'ETC', '기타', '기타 문서', TRUE, 9);


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
    ('POSITION', 'BD', '부장', '부장', TRUE, 6),
    ('POSITION', 'CJ', '차장', '차장', TRUE, 7),
    ('POSITION', 'GJ', '과장', '과장', TRUE, 8),
    ('POSITION', 'DR', '대리', '대리', TRUE, 9),
    ('POSITION', 'ENG', '사원', '사원', TRUE, 10),
    ('POSITION', 'INT', '인턴', '인턴', TRUE, 11);



-- 상위 메뉴: 전자결재
INSERT INTO menu (id, parent_id, name, path, icon, sequence, base_role, active_yn)
VALUES (1, NULL, '전자결재', '/approval', 'fa-file-signature', 1, 'USER', TRUE);

-- 하위 메뉴: 전자결재
INSERT INTO menu (id, parent_id, name, path, icon, sequence, base_role, active_yn)
VALUES
    (2, 1, '기안함', '/approval/drafts', NULL, 1, 'USER', TRUE),
    (3, 1, '결재 목록', '/approval/list', NULL, 2, 'USER', TRUE);


-- 상위 메뉴: 프로젝트 관리
INSERT INTO menu (id, parent_id, name, path, icon, sequence, base_role, active_yn)
VALUES (4, NULL, '프로젝트 관리', '/projects', 'fa-project-diagram', 2, 'USER', TRUE);

-- 하위 메뉴: 프로젝트 관리
INSERT INTO menu (id, parent_id, name, path, icon, sequence, base_role, active_yn)
VALUES
    (5, 4, '프로젝트', '/projects/list', NULL, 1, 'USER', TRUE),
    (6, 4, '인력배정', '/projects/members', NULL, 2, 'USER', TRUE);


-- 상위 메뉴: 투표
INSERT INTO menu (id, parent_id, name, path, icon, sequence, base_role, active_yn)
VALUES (7, NULL, '투표', '/votes', 'fa-poll', 3, 'USER', TRUE);


-- 상위 메뉴: 조직 관리
INSERT INTO menu (id, parent_id, name, path, icon, sequence, base_role, active_yn)
VALUES (8, NULL, '조직 관리', '/organization', 'fa-sitemap', 4, 'USER', TRUE);

-- 하위 메뉴: 조직 관리
INSERT INTO menu (id, parent_id, name, path, icon, sequence, base_role, active_yn)
VALUES
    (9, 8, '부서 목록', '/organization/departments', NULL, 1, 'USER', TRUE),
    (10, 8, '직원 현황', '/organization/employees', NULL, 2, 'USER', TRUE);


-- 상위 메뉴: 커뮤니케이션
INSERT INTO menu (id, parent_id, name, path, icon, sequence, base_role, active_yn)
VALUES (11, NULL, '커뮤니케이션', '/communication', 'fa-comments', 5, 'USER', TRUE);

-- 하위 메뉴: 커뮤니케이션
INSERT INTO menu (id, parent_id, name, path, icon, sequence, base_role, active_yn)
VALUES
    (12, 11, '게시판', '/communication/board', NULL, 1, 'USER', TRUE),
    (13, 11, '사내 위키', '/communication/wiki', NULL, 2, 'USER', TRUE),
    (14, 11, '메뉴얼', '/communication/manual', NULL, 3, 'USER', TRUE);


-- 상위 메뉴: 관리자 메뉴
INSERT INTO menu (id, parent_id, name, path, icon, sequence, base_role, active_yn)
VALUES (15, NULL, '관리자 메뉴', '/admin', 'fa-user-shield', 6, 'ADMIN', TRUE);

-- 하위 메뉴: 관리자 메뉴
INSERT INTO menu (id, parent_id, name, path, icon, sequence, base_role, active_yn)
VALUES
    (16, 15, '전자결재 관리', '/admin/approval', NULL, 1, 'ADMIN', TRUE),
    (17, 15, '게시물 관리', '/admin/posts', NULL, 2, 'ADMIN', TRUE),
    (18, 15, '직원 생성/관리', '/admin/employees', NULL, 3, 'ADMIN', TRUE),
    (19, 15, '조직 관리', '/admin/org', NULL, 4, 'ADMIN', TRUE),
    (20, 15, '비품/자산 관리', '/admin/assets', NULL, 5, 'ADMIN', TRUE),
    (21, 15, '결재선 관리', '/admin/approval-line', NULL, 6, 'ADMIN', TRUE),
    (22, 15, '공통 코드 관리', '/admin/common-codes', NULL, 7, 'ADMIN', TRUE);


-- 상위 메뉴: 내 정보
INSERT INTO menu (id, parent_id, name, path, icon, sequence, base_role, active_yn)
VALUES (23, NULL, '내 정보', '/my-info', 'fa-user', 7, 'USER', TRUE);
