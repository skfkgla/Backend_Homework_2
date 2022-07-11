# Backend_Homework_2
## Used
- maven
- Java(1.8)
- JPQL
- h2
- Mysql(8)
- flyway
- Spring Data Jpa
- Spring Boot
- docker
- docker-compose

## maven build
```bash
mvnw clean package -DskipTests
```
## docker-compose 실행
프로파일과 실행 포트 지정
```bash
docker-compose up --build
```


## 순서
```bash
//1. 유저를 등록해야 해당 유저로 events를 사용할 수 있습니다.
Post    /user/register
{
    userId : "String"
}
```
----
```bash
//2. 리뷰 등록, 수정, 삭제 이벤트 실행
```
---
```bash
//3. 유저로 point조회
Get     /point
{
    userId : "String"
}
```
