# 🧩 프로젝트 실행 방법
* 프로젝트 실행 전 환경변수 등록 필요
 - JASYPT_ENCRYPTOR_PASSWORD="값은 문의 바람"

# docker 관련
* docker 실행 시 /mysql-init/*.sql 이 자동으로 실행되어 기본 테이블이 생성됩니다.

* Database 테이블 전제 삭제 후 재시작 필요 시
```
docker-compose down -v
docker-compose up
```


# 🧩 계정 생성 및 Swagger 테스트 가이드

## 1️⃣ 계정 생성 방법

아래 API를 통해 계정을 생성할 수 있습니다.

- **URL:** [http://localhost:8080/swagger-ui/index.html?urls.primaryName=member#/Member/createMember](http://localhost:8080/swagger-ui/index.html?urls.primaryName=member#/Member/createMember)
- **Method:** `POST`

### 📌 요청 예시 (Request Body)

```json
{
    "memberName": "kim",
    "memberId": "kim",
    "email": "valva@naver.com",
    "phone": "019-3859703",
    "department": "EXEC",
    "rank": "CEO",
    "hireDt": "2025-10-25",
    "baseRole": "ROLE_ADMIN",
    "detailRole": {
        "projectActiveYn": true
    }
}
```


## 2️⃣ AccessToken 발급 방법

아래 API를 통해 AccessToken을 발급받을 수 있습니다.

- **URL** [http://localhost:8080/swagger-ui/index.html?urls.primaryName=auth#/Auth/login]
- **Method:** `POST`

### 📌 요청 예시 (Request Body)

```json
{
    "memberId": "kim",
    "password": "coev1_init",
    "fcmToken": "string"
}
```




# 📘 코드 컨벤션 (MD)

# 🌱 메소드 명명 규칙

---

## 🧩 1. 공통 기본 규칙 (Java 표준)

모든 메소드명은 Java 기본 컨벤션을 따릅니다.

| 항목 | 규칙 |
|------|------|
| **형식** | `camelCase` (소문자로 시작, 단어마다 대문자) |
| **의미 중심** | 동사 + 목적어 형태 사용 (`getUser()`, `saveFile()`) |
| **약어 금지** | `getUsr()` ❌ → `getUser()` ✅ |
| **의도 드러내기** | 구현이 아닌 “무엇을 하는지” 표현 (`calculateTotalPrice()` 등) |

---

## 🧱 2. Controller 계층

> **요청(Request)에 대한 행동 중심으로** 명명합니다.  
> 즉, **HTTP Method + 리소스 이름** 조합이 일반적입니다.

| 예시 | 설명 |
|------|------|
| `getUsers()` | 사용자 목록 조회 (GET `/users`) |
| `getUserById(Long id)` | 사용자 단건 조회 (GET `/users/{id}`) |
| `createUser(UserRequest request)` | 사용자 등록 (POST `/users`) |
| `updateUser(Long id, UserRequest request)` | 사용자 수정 (PUT `/users/{id}`) |
| `deleteUser(Long id)` | 사용자 삭제 (DELETE `/users/{id}`) |

📌 **규칙 요약**
- HTTP 동사에 맞춰 `get`, `create`, `update`, `delete` 사용
- Controller 메소드명은 REST API의 의미와 일치시킴
- 메소드명에 DTO나 Entity 이름을 포함해도 무방 (`createUser`, `createPost`, `updateComment` 등)

---

## 🧠 3. Service 계층

> **비즈니스 행위 중심으로 명명**합니다.  
> 실제 동작(로직)을 표현해야 하므로, 컨트롤러보다 **의미 중심적**입니다.

| 예시 | 설명 |
|------|------|
| `registerUser(UserRequest request)` | 사용자 등록 로직 수행 |
| `loginUser(String email, String password)` | 로그인 처리 |
| `calculateAverageRating(Long postId)` | 평점 계산 |
| `sendNotificationToFollowers(Long userId)` | 팔로워에게 알림 발송 |

📌 **규칙 요약**
- “비즈니스 행위”를 나타내는 **의미 있는 동사** 사용
- `get`, `find`, `save`, `update`, `delete`, `calculate`, `send`, `process`, `register` 등
- **Controller 이름을 그대로 복사하지 말고**, **업무적 의미**를 담음

---

## 🧾 4. Repository 계층 (Spring Data JPA 기준)

> JPA 쿼리 메소드 네이밍 규칙을 따릅니다.  
> 즉, **find + By + 속성명 + 조건** 형태.

| 예시 | 설명 |
|------|------|
| `findByEmail(String email)` | 이메일로 조회 |
| `findByUsernameAndStatus(String username, String status)` | 복합 조건 조회 |
| `existsByEmail(String email)` | 존재 여부 확인 |
| `deleteByUserId(Long userId)` | 조건 기반 삭제 |
| `countByCategory(String category)` | 카테고리별 개수 카운트 |

📌 **규칙 요약**
- `findBy`, `readBy`, `getBy`, `countBy`, `existsBy`, `deleteBy` 등의 접두사
- 조건은 **Entity 필드명 기준** (`findByUserIdAndStatus`)
- 정렬은 `OrderBy필드명Asc/Desc` 추가 (`findByStatusOrderByCreatedAtDesc`)

---

## ⚙️ 5. 유틸리티 / 헬퍼 클래스

> 입력 → 처리 → 결과의 의미를 명확히 드러냅니다.

| 예시 | 설명 |
|------|------|
| `convertToEntity(UserRequest dto)` | DTO → Entity 변환 |
| `convertToDto(User entity)` | Entity → DTO 변환 |
| `generateRandomCode()` | 랜덤 코드 생성 |
| `formatDate(LocalDateTime dateTime)` | 날짜 포맷팅 |

📌 **규칙 요약**
- `convert`, `format`, `generate`, `validate`, `parse` 등의 동사 사용
- “변환”, “생성”, “검증”, “포맷팅” 등 도메인 독립적 로직 표현

---

## 📚 6. 테스트 코드

> “검증 대상 메소드명 + 시나리오 + 기대 결과” 형식으로 명명합니다.

| 예시 | 설명 |
|------|------|
| `createUser_WhenValidInput_ShouldSaveUser()` | 입력이 유효할 때 저장되어야 함 |
| `loginUser_WhenPasswordIncorrect_ShouldThrowException()` | 비밀번호가 틀릴 때 예외 발생 |
| `getUsers_ShouldReturnSortedList()` | 정렬된 목록 반환 확인 |

📌 **규칙 요약**
- Given/When/Then 패턴 반영 (`메소드명_When조건_Should결과`)
- 테스트 목적이 명확하게 드러나야 함

---

## ✅ 요약

| 계층 | 명명 패턴 | 예시 |
|------|------------|------|
| **Controller** | HTTP 동사 + 리소스 | `getUser()`, `createPost()` |
| **Service** | 비즈니스 행위 중심 | `registerUser()`, `calculateRating()` |
| **Repository** | JPA 규칙 | `findByEmail()`, `existsById()` |
| **Util/Helper** | 행위 중심 | `convertToDto()`, `generateCode()` |
| **Test** | 시나리오 기반 | `updateUser_WhenValid_ShouldSucceed()` |


## 📁 패키지 구조 예시 (도메인 + 계층 기반)
```
com.projectname
├── global              # 공통 설정, 예외, 유틸 등
│   ├── config
│   ├── exception
│   └── util
├── domain
│   └── member                # 도메인 이름
│       ├── controller
│       ├── service
│       ├── repository
│       ├── dto
│       └── domain          # Entity, VO 등
```

## 1. 클래스/파일 구조
```java
// 순서대로 선언
package com.example.app;
import ...
/**
 * 클래스 설명 Javadoc
 */
public class MemberService {
    // 상수
    public static final int MAX_SIZE = 100;

    // 필드
    private final MemberRepository memberRepository;

    // 생성자
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // public 메서드
    public void doSomething() {
        // ...
    }

    // private 메서드
    private String formatName(String name) {
        return name.trim().toUpperCase();
    }
}
```

---

## 2. 어노테이션 사용 규칙 및 메서드 네이밍
### 접두사 정리
- 속성에 접근: get/set
- 데이터 생성: create
- 데이터 조회: find
- 데이터 변경: modify
- 데이터 삭제: delete
- 데이터 입력: input
- 데이터 초기화: init
- 데이터 불러오기: load
- 데이터 유무 확인: has

### Controller 클래스
- `@Tag`, `@RequestMapping`, `@RestController`, `@RequiredArgsConstructor`
- Controller: HTTP 액션 + 리소스명 중심 (ex. `getUser`, `createUser`, `updateUser`)
- Swagger는 Operation으로 설명
- 의존성 주입은 어노테이션을 사용 (**@RequiredArgsConstructor**)

```java
@Tag(name = "Member", description = "멤버 관련 API")
@RequestMapping("/member")
@RestController
public class MemberController {

  private final MemberService memberService;
  
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

  @GetMapping
  @Operation(
          summary = "회원정보 조회",
          description = "특정 회원 정보를 조회한다.",
          parameters = {
                  @Parameter(name = "memberId", description = "회원 ID", in = ParameterIn.PATH, required = true)
          },
          responses = {
                  @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공", content = @Content(schema = @Schema(implementation = MemberRes.class)))
          }
  )
  public Response<MemberRes> getMember(@Parameter(hidden = true) @AuthPrincipal UserAuth user, String memberId) {
    return Response.ok(memberService.getMember(memberId));
  }
```

### Service
- `@Slf4j`, `@Service`, `@RequiredArgsConstructor`
- Service: 비즈니스 동작 설명 (ex. `findUserById`, `registerUser`, `modifyUserInfo`)
- 비즈니스 로직은 이곳에서 처리하며, 트랜잭션 처리 필요시 명시적으로 지정
- 의존성 주입은 생성자 주입을 사용 (**@RequiredArgsConstructor**, **@AllArgsConstructor** 는 지양)
```java
@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    
    public Memberervice(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public MemberRes getMember(Long id) {
        // ...
    }
}
```

### Repository
- Spring Data JPA 메서드는 도메인 + 조건 + 키워드 기반
    - `findBy`, `countBy`, `existsBy`, `deleteBy` 사용
    - 예시:
        - `findByUsername(String username)`
        - `findByEmailAndStatus(String email, Status status)`
        - `existsByEmail(String email)`
        - `deleteByCreatedDateBefore(LocalDate date)`
```java
public interface MemberRepository {
    Member findMemberById(Long id);
}
```

### Repository 구현체
- Spring Data JPA 메서드는 도메인 + 조건 + 키워드 기반
    - `findBy`, `countBy`, `existsBy`, `deleteBy` 사용
    - 예시:
        - `findByUsername(String username)`
        - `findByEmailAndStatus(String email, Status status)`
        - `existsByEmail(String email)`
        - `deleteByCreatedDateBefore(LocalDate date)`
- 의존성 주입은 생성자 주입을 사용 (**@RequiredArgsConstructor**, **@AllArgsConstructor** 는 지양)
```java
public class MemberRepositoryImpl implements MembercRepository{

  private final JPAQueryFactory queryFactory;

  private final JpaMemberRepository jpaMemberRepository;
  private final EntityManager entityManager;

  public MemberRepositoryImpl(JPAQueryFactory queryFactory, JpaMemberRepository jpaMemberRepository, EntityManager entityManager) {
    this.queryFactory = queryFactory;
    this.jpaMemberRepository = jpaMemberRepository;
    this.entityManager = entityManager;
  }
    
  public Member findMemberById(Long id) {
    MemberEntity member = jpaMemberRepository.findById(id).orElseThrow();
    return member.toMember();
  }
}
```

### Entity
- `@Entity`, `@Getter`, `@Setter`, `@NoArgsConstructor`, `@Table`
- 테이블명 + Entity
- 연관관계는 `@ManyToOne(fetch = LAZY)` 등으로 반드시 명시
- 양방향 관계에서는 연관관계의 주인을 명확히 지정
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private RoleEntity roleEntity;
}
```

### VO(Value Object)
- `@Builder`, `@Getter`, `@Setter`, `@AllArgsConstructor`
```java
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Member {
    private Long memberId;
    private String memberName;
}
```

### DTO
- `@Getter`, `@Setter`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`
- ✅ 명명
  | 타입 | 접미사 | 예시 |
  |------|--------|------|
  | 요청 DTO | `Req` | `MemberCreateReq` |
  | 응답 DTO | `Res` | `MemberInfoRes` |
```java
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateReq {
    @NotBlank
    private String username;

    @Email
    private String email;
}
```

---

## 3. 네이밍 규칙
| 항목 | 규칙 | 예시                                  |
|------|------|-------------------------------------|
| 클래스 | UpperCamelCase | MemberController, MemberService |
| 변수 | lowerCamelCase | memberId, createdAt                 |
| 상수 | UPPER_SNAKE_CASE | MAX_PAGE_SIZE                       |
| 복수형 변수 | 의미 있는 복수형 | memberList, memberDtos              |
| boolean 변수 | is/has/should + 명사/동사 | isValid, hasPermission              |

---


## 4. 문법 및 작성 규칙
- 들여쓰기: `4칸`
- 중괄호 `{}` 스타일: `K&R (Kernighan and Ritchie)` 스타일 사용
  ```java
  if (user != null) {
      // 처리
  } else {
      // 처리
  }
  ```
- 반복문: Stream을 선호하되 가독성이 떨어지면 일반 for문 사용
```java
// ✅ stream 예시
List<String> names = users.stream()
    .map(User::getName)
    .collect(Collectors.toList());

// ✅ 일반 for문 예시
for (User user : users) {
    log.info(user.getName());
}
```
- 메서드 간은 `한 줄 띄우기`
- `if`, `for`, `while` 등은 항상 `{}` 사용
- 조건문은 부정보다 긍정을 우선
    - ❌ `if (!isValid)` → ✅ `if (isInvalid)`
- 클래스 내 내부 클래스(Nested Class) 작성 금지 → 별도 파일로 분리

---

## 5. 예외 처리 규칙
- 커스텀 예외 클래스 작성 (ex. `MemberNotFoundException`)
```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MemberNotFoundException.class)
    public Response handleUserNotFound(MemberNotFoundException ex) {
        return Response.error(HttpStatus.NOT_FOUND);
    }
}
```

---

## 6. 로그 출력 규칙
- `@Slf4j` 어노테이션 사용
- 메시지는 `{}` 형식으로 분리 표현
```java
@Slf4j
@Service
public class MemberService {
    public void register(User user) {
        log.info("멤버 등록 완료: {}", user.getId());
    }
}
```

---

## 7. 주석 스타일
### 클래스 위 Javadoc
```java
/*
 * <p>
 * 클래스명
 *
 * @author 김태욱
 * @version 1.0
 * @since 2025/01/01
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일        수정자           수정내용
 *  ----------    --------        ---------------------------
 *  2025/01/01    김태욱            최초 생성
 * </pre>
*/
```
### 메서드 Javadoc
```java
/**
 * 메서드 동작 설명
 *
 * @param id 사용자 ID
 * @return 사용자 정보
 */
```

## 8. REST API 설계 규칙
### 설계 원칙
- URL은 소문자 사용, 동사 지양 (자원 중심 설계)
- 리소스는 **복수형 명사** 사용
- 동작은 HTTP 메서드로 표현 (GET, POST, PUT, DELETE)
- 응답은 통일된 포맷 제공
### URL 예시
| 메서드 | URI | 설명 |
|--------|-----|------|
| GET | /api/users | 전체 사용자 조회 |
| GET | /api/users/{id} | 사용자 단건 조회 |
| POST | /api/users | 사용자 생성 |
| PUT | /api/users/{id} | 사용자 전체 수정 |
| DELETE | /api/users/{id} | 사용자 삭제 |

---

## 9. 응답 포맷
```json
{
  "code": "200",
  "message": "completed!",
  "value": null
}
```

---

## 10. Git Commit 메시지 컨벤션
> - Feat : 새로운 기능을 추가하는 경우
> - Fix : 버그를 고친경우
> - Docs : 문서를 수정한 경우
> - Style : 코드 포맷 변경, 세미콜론 누락, 코드 수정이 없는경우
> - Refactor : 코드 리펙토링
> - Test : 테스트 코드. 리펙토링 테스트 코드를 추가했을 때
> - Chore : 빌드 업무 수정, 패키지 매니저 수정
> - Design : CSS 등 사용자가 UI 디자인을 변경했을 때
> - Rename : 파일명(or 폴더명) 을 수정한 경우
> - Remove : 코드(파일) 의 삭제가 있을 때. "Clean", "Eliminate" 를 사용하기도 함
> - #### ex) Feat : 로그인 기능 추가

---


# 프로젝트 실행
- 로컬 실행
  > ### <Java version 변경>
  >
  > 1.프로젝트 우클릭 > 모듈 설정 > 프로젝트
  >
  > - SDK : corretto-21 Amazon Corretto
  >
  > ### <인텔리제이 빌드 및 컴파일 설정>
  >
  > 1.인텔리제이 설정 > 빌드,실행,배포 > 빌드 도구 > Gradle 프로젝트
  >
  > - IntelliJ IDEA
  >
  > 2.빌드,실행,배포 > Java 컴파일러
  >
  > - 17
  >
  > ### <Gradle 로드>
  >
  > 1.Gradle 로드로 라이브러리 설치
  >
  > ### <어플리케이션 설정>
  >
  > 1.Spring Boot 어플리케이션 생성
  >
  > 2.빌드 및 실행 : java 21
>
### 실행
- Base Url : http://localhost:8080

## Coding Check Style 설정 (Google)
- 사전 준비 설정 파일 (배포)
> intellij-java-google-style
- 설정 방법
> 1.Check Style 플러그인 설치
>
> 2.인텔리제이 설정 > 도구(Tool) > Check style
> 3.에디터(Editor) > 코드스타일(Code Style)
>
> - 구성표 > 구성표 가져오기 > Checkstyle configuration > intellij-java-google-style 파일 추가 > 구성표 변경
>
> 4.단축키로 Formatter 적용
>
> - 코드 서식 적용 : Opt + Command + L
> - import문 최적화 : Ctrl+ Opt + O

## 주석 템플릿 적용
### Class 주석
> 1.인텔리제이 설정 > 에디터 > 파일 및 코드 템플릿
>
> - "포함" 탭 > File Header > 아래 설정값 입력
```java
/**
 * ${PACKAGE_NAME}.${NAME}
 * <p>
 * ${NAME}
 *
 * @author 김태욱
 * @version 1.0
 * @since ${YEAR}/${MONTH}/${DAY}
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일        수정자           수정내용
 *  ----------    --------        ---------------------------
 *  ${YEAR}/${MONTH}/${DAY}    김태욱            최초 생성
 * </pre>
 */
```

### Method 주석
> 1.인텔리제이 설정 > 에디터 > 파일 및 코드 템플릿
>
> - "포함" 탭 > `+` 클릭 > Method Header 생성 (아무것도 입력할 필요 없음)
> - "코드" 탭 > JavaDoc Method > 아래 설정값 입력
```
#parse("Method Header.java")
#foreach($param in $PARAMS)
 *@param $param
#end
#if($RETURN_TYPE !="void")
 *@return
#end
```
> 2.사용법
>
> - 메소드 생성 > 메소드 상단에 /** 입력 후 엔터

---


