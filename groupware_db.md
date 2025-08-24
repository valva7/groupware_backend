
# 그룹웨어 DB 테이블 구조

## 직원
### member (직원)
- id (PK)
- member_id
- member_name
- rank
- profile_image_url
- email
- phone
- address
- emergency_name
- emergency_phone
- password
- approval_active_yn
- project_active_yn
- read_active_yn
- push_token
- push_yn
- status
- hire_dt
- expiration_dt

## 권한
### role (권한)
- id (PK)
- role_name
- description

### member_role (직원 권한)
- id (PK)
- role_id (FK: role - id)
- member_id (FK: member - id)

## 조직도
### department (부서 목록)
- id (PK)
- parent_department_id (FK)
- leader_id (FK: member - id)
- name
- description
- department_tag

### department_member (부서 직원 목록)
- id (PK)
- department_id (FK: department - id)
- member_id (FK: member - id)

## 휴가
### vacation_request (휴가 신청)
- id (PK)
- vacation_member_id (FK: member - id)
- vacation_type
- start_date
- end_date
- days

### vacation_member (직원 잔여 휴가 정보)
- id (PK)
- member_id (FK: member - id)
- total_days
- used_days
- used_hours
- remaining_days
- remaining_hours

## 전자결재
### approval_type (전자결재 종류 목록)
- id (PK)
- parent_id
- name
- description
- used_count
- active_yn

### approval_request (전자결재 요청 목록)
- id (PK)
- type_id (FK: approval_type - id)
- requester_id (FK: member - id)
- title
- reject_reason
- status

### approval_type_field (전자결재 필드)
- id (PK)
- type_id (FK: approval_type - id)
- field_name (입력 항목 이름)
- field_type (데이터 타입 (TEXT, NUMBER, DATE 등))
- required_yn

### approval_type_data (전자결재 필드 데이터)
- id (PK)
- request_id (FK: approval_request - id)
- type_field_id (FK: approval_type_field - id)
- value
- sequence (요청별 순서를 적용)

### approval_line (결재선)
- id (PK)
- type (승인, 참조)
- request_id (FK: approval_request - id)
- approval_id (FK: member - id)
- sequence
- status
- approved_dt

## 게시글
### post_type (게시글 종류)
- id (PK)
- name
- description
- used_count
- active_yn
- sequence

### post (게시글 작성 목록)
- id (PK)
- type_id (FK: post_type - id)
- author_id (FK: member - id)
- title
- content
- view_count

### post_comment (게시글 댓글)
- id (PK)
- post_id (FK)
- commenter_id (FK: member - id)
- content

### post_like (게시글 좋아요)
- id (PK)
- post_id (FK: post - id)
- liker_id (FK: member - id)
- first_like_yn
- like_yn

### post_attachment (첨부 파일)
- id (PK)
- post_id (FK: post - id)
- file_name
- file_path
- file_size

## 투표
### vote (투표 목록)
- id (PK)
- creator_id (FK: member - id)
- type
- title
- description
- target_department
- status
- start_date
- end_date

### vote_option (투표 선택지)
- id (PK)
- vote_id (FK: vote - id)
- option_text
- vote_count

### vote_participant (투표자 목록)
- id (PK)
- vote_id (FK: vote - id)
- voter_id (FK: member -id)
- option_id (FK: vote_option - id)
- voted_at

## 프로젝트
### project_type (프로젝트 종류)
- id (PK)
- name
- description
- active_yn

### project (프로젝트 목록)
- id (PK)
- type_id (FK: project_type - id)
- leader_id (FK: member - id)
- title
- description
- status
- start_date
- end_date

### project_member (프로젝트 인력)
- id (PK)
- project_id (FK: project - id)
- member_id (FK: member - id)
- role
- joined_at

### project_attachment (프로젝트 첨부 파일)
- id (PK)
- project_id (FK: project - id)
- file_name
- file_path
- file_size

## 메뉴
### menu (메뉴)
- id (PK)
- parent_id (FK: menu - id)
- name
- path
- icon
- sequence
- active_yn

## 공통 코드
### common_code (공통 코드)
- id (PK)
- group_code
- code
- code_name
- description
- active_yn
- sequence

## 알림
### notification (알림)
- id (PK)
- recipient_id (FK: member - id)
- type
- title
- message
- read_yn
- go_url
- created_at
