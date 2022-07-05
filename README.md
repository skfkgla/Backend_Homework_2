# Backend_Homework_2

## maven build
```bash
mvnw clean package
```
## maven run
프로파일과 실행 포트 지정
```bash
mvnw spring-boot:run -Dspring-boot.run.profiles=local -Dspring-boot.run.jvmArguments='-Dserver.port=8080'
```



## DB접속

```bash
url: localhost:8080/h2
JDBC URL: jdbc:h2:mem:test
id: sa
```

## 순서
```bash
//1. 유저를 등록해야 해당 유저로 events를 사용할 수 있습니다.
Post    /user/register
{
    userId : "String"
}
```
```bash
//2. 리뷰 등록, 수정, 삭제 이벤트 실행
```
```bash
//3. 유저로 point조회
Get     /point
{
    userId : "String"
}
```

```