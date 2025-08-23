
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
- status
- hire_date

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
### vacation (휴가)
- id (PK)
- vacation_member_id (FK: member - id)
- vacation_type
- start_date
- end_date
- days

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
- content
- status

### approval_line (결재선)
- id (PK)
- request_id (FK: approval_request - id)
- approver_id (FK: member - id)
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
- title
- description
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
