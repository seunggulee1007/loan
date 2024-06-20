# 대출 서비스

해당 프로젝트는 핀테크 및 대출 도메인을 이해하기 위해 만드는 프로젝트이다.
이 프로젝트의 목표는 5가지의 기능을 만들어보며 대출 도메인을 실제로 MVP 버전으로 구현해 보는 것이 목적이다.
5가지 기능은 다음과 같다.

- 대출 상담
- 대출 신청
- 대출 심사
- 대출 집행
- 대출 상환

## 1. 대출 상담

대출 상담은 간단하게 상담 신청 하고 수정 삭제를 하는 CRUD 성 기능이다.
테이블은 다음과 같다.

| No | 논리명            | 물리명                | 타입          | 비고 |
|----|----------------|--------------------|-------------|----|
| 1  | counsel_id     | 상담 식별자             | Long        | pk |
| 2  | address        | 주소                 | varchar(50) |    |
| 3  | address_detail | 상세 주소              | varchar(50) |    |
| 4  | applied_at     | 신청일자               | datetime    |    |
| 5  | cell_phone     | 전화번호               | varchar(13) |
| 6  | email          | 상담요청자 이메일          | varchar(50) |    |
| 7  | memo           | 상담메모               | text        |    |
| 8  | name           | 상담 요청자 varchar(12) |             |
| 9  | zip_code       | 우편번호               | varchar(5)  |    |

## 2. 대출 신청

신청 -> 약관동의 -> 대출신청서작성(희망한도) -> 신청조건 -> 입회서류등록 -> 대출신청 의 프로세스로 돌아간다.

- 신청서 작성
- 약관등록
- 심사에 필요한 서류 제출
- 약관 등록은 분리하여 구현
- 신청정보 작성과 파일 업로드도 분리하여 구현
- 신청 조건에 따른 신청 상태 변경도 분리하여 구현

### 도메인

- id 는 Long(bigint)
- soft delete
- 신청을 위해 필요한 기본 필드 정의

| No | 논리명            | 물리명     | 타입            | 비고 |
|----|----------------|---------|---------------|----|
| 1  | application_id | 신청 식별자  | bigint        | pk |
| 2  | created_at     | 신청일자    | datetime      |    |
| 3  | is_deleted     | 이용가능여부  | bit           |
| 4  | updated_at     | 수정일자    | datetime      |
| 5  | applied_at     | 신청일자    | datetime      |
| 6  | cell_phone     | 전화번호    | varchar(13)   |
| 7  | email          | 신청자 이메일 | varchar(50)   |
| 8  | fee            | 취급수수료   | decimal(5,4)  |
| 9  | hope_amount    | 대출신청금액  | decimal(15,2) |
| 10 | interest_rate  | 금리      | decimal(5,4)  |
| 11 | maturity       | 만기      | datetime      |
| 12 | name           | 신청자     | varchar(12)   |
