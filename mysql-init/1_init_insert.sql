-- role 샘플 데이터
INSERT INTO roles (role_id, role_name, description) VALUES
('ROLE_ADMIN', '시스템 관리자', '시스템 전체 기능과 설정에 접근할 수 있는 관리자 역할'),
('ROLE_USER', '일반 사용자', '일반 사용자가 기본 기능을 사용할 수 있는 역할'),
('ROLE_MANAGER', '인사 관리자', '팀 관리 및 승인 권한을 가진 인사 관리자 역할'),
('ROLE_GUEST', '자산 관리자', '자산 관련 기능만 접근 가능한 제한된 역할');


-- member 샘플 데이터
INSERT INTO member (member_id, member_name, rank_cd, email, phone, address,emergency_name, emergency_phone,status, hire_dt, expiration_dt,password, profile_image_url) VALUES
('devkim', '김태욱', 'DR', 'kimgleam@example.com', '010-1111-2222', '서울 강남구',     '홍길동', '010-9999-8888', 'WORK', '2024-01-01', NULL,'qwe1212!', NULL);


-- member_roles 샘플 데이터
INSERT INTO member_roles (member_id, role_id) VALUES
('devkim', 'ROLE_ADMIN');


-- 부서 정보
INSERT INTO department (parent_department_code, code, name, description, leader_id) VALUES
(NULL, 'EXEC', '경영팀', '경영팀', NULL),
(NULL, 'DEV', '개발팀', '개발팀', NULL),
('DEV', 'LEAD', '리더팀', '리더팀', NULL),
('DEV', 'ENG', '엔지니어팀', '엔지니어팀', NULL);


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



-- M + 메뉴 약자 + 화면 종류 (DT, LT 등)
-- 상위 메뉴
-- =========================
-- 메뉴 INSERT (상위 메뉴)
-- =========================
INSERT INTO menu (menu_id, menu_name, path, parent_id) VALUES
                                                           ('MDASHBD', '대시보드', '/dashboard', NULL),
                                                           ('MAPPROV', '전자결재', '#', NULL),
                                                           ('MPROJMG', '프로젝트 관리', '#', NULL),
                                                           ('MVOTEMN', '투표', '/votes', NULL),
                                                           ('MORGNMG', '조직 관리', '#', NULL),
                                                           ('MCOMMSN', '커뮤니케이션', '#', NULL),
                                                           ('MADMNMN', '관리자 메뉴', '#', NULL),
                                                           ('MPROFIL', '내 정보', '/profile', NULL);

-- =========================
-- 메뉴 INSERT (하위 메뉴)
-- =========================
INSERT INTO menu (menu_id, menu_name, path, parent_id) VALUES
                                                           ('MDRAFT1', '기안함', '/approval/draft', 'MAPPROV'),
                                                           ('MLIST01', '결재 목록', '/approval/list', 'MAPPROV'),
                                                           ('MPROJ01', '프로젝트', '/projects', 'MPROJMG'),
                                                           ('MRESR01', '인력 배정', '/projects/resources', 'MPROJMG'),
                                                           ('MDEPT01', '부서 목록', '/organization/departments', 'MORGNMG'),
                                                           ('MMEMB01', '직원 현황', '/organization/members', 'MORGNMG'),
                                                           ('MBOARD1', '게시판', '/board', 'MCOMMSN'),
                                                           ('MWIKI01', '사내 위키', '/wiki', 'MCOMMSN'),
                                                           ('MMANUAL', '메뉴얼', '/manual', 'MCOMMSN'),
                                                           ('MADAPRV1', '전자결재 관리', '/admin/approval', 'MADMNMN'),
                                                           ('MADPOST1', '게시물 관리', '/admin/posts', 'MADMNMN'),
                                                           ('MADMEMP1', '직원 생성/관리', '/admin/members', 'MADMNMN'),
                                                           ('MADMORG1', '조직 관리', '/admin/organization', 'MADMNMN'),
                                                           ('MADAST01', '비품/자산 관리', '/admin/assets', 'MADMNMN'),
                                                           ('MADMAPP2', '결재선 관리', '/admin/approval-lines', 'MADMNMN'),
                                                           ('MADMCC01', '공통 코드 관리', '/admin/common-codes', 'MADMNMN');

-- =========================
-- 화면 INSERT (screen)
-- =========================
INSERT INTO screen (screen_id, screen_name, path, menu_id) VALUES
-- Dashboard
('SDASHBD', '대시보드', '/dashboard', 'MDASHBD'),

-- Approval screens
('SADRAF1', '기안함', '/approval/draft', 'MDRAFT1'),
('SALIST01', '결재 목록', '/approval/list', 'MLIST01'),
('SADETAIL', '결재 상세', '/approval/detail/:id', 'MLIST01'),

-- Project screens
('SPROJ01', '프로젝트', '/projects', 'MPROJ01'),
('SPROJCR', '프로젝트 생성', '/projects/create', 'MPROJ01'),
('SPROJDT', '프로젝트 상세', '/projects/detail/:id', 'MPROJ01'),
('SRESR01', '인력 배정', '/projects/resources', 'MRESR01'),

-- Vote screens
('SVOTE01', '투표 목록', '/votes', 'MVOTEMN'),
('SVOTECR', '투표 생성', '/votes/create', 'MVOTEMN'),

-- Organization screens
('SDEPT01', '부서 목록', '/organization/departments', 'MDEPT01'),
('SMEMB01', '직원 현황', '/organization/members', 'MMEMB01'),

-- Communication screens
('SBOARD1', '게시판', '/board', 'MBOARD1'),
('SPOSTCR1', '게시물 생성', '/board/create', 'MBOARD1'),
('SPOSTDT1', '게시물 상세', '/board/detail/:id', 'MBOARD1'),
('SWIKI01', '사내 위키', '/wiki', 'MWIKI01'),
('SWIKICR1', '위키 생성', '/wiki/create', 'MWIKI01'),
('SWIKIDT1', '위키 상세', '/wiki/detail/:id', 'MWIKI01'),
('SMANUAL1', '메뉴얼', '/manual', 'MMANUAL'),
('SMANUCR1', '메뉴얼 생성', '/manual/create', 'MMANUAL'),
('SMANUDT1', '메뉴얼 상세', '/manual/detail/:id', 'MMANUAL'),

-- Admin screens
('SADMAPRV', '전자결재 관리', '/admin/approval', 'MADAPRV1'),
('SADMPOST', '게시물 관리', '/admin/posts', 'MADPOST1'),
('SPOSTDT2', '게시물 상세', '/admin/posts/detail/:id', 'MADPOST1'),
('SPOSTEDT', '게시물 수정', '/admin/posts/edit/:id', 'MADPOST1'),
('SADMMEMB', '직원 생성/관리', '/admin/members', 'MADMEMP1'),
('SADMORGN', '조직 관리', '/admin/organization', 'MADMORG1'),
('SADMASST', '비품/자산 관리', '/admin/assets', 'MADAST01'),
('SADMALIN', '결재선 관리', '/admin/approval-lines', 'MADMAPP2'),
('SADMCC01', '공통 코드 관리', '/admin/common-codes', 'MADMCC01'),

-- Profile screen
('SPROFIL', '내 정보', '/profile', 'MPROFIL');
