# Spring Batch 프로젝트

이 프로젝트는 Spring Boot와 Spring Batch를 사용하여 구현된 배치 처리 애플리케이션입니다.

## 📋 프로젝트 개요

- **프레임워크**: Spring Boot 3.4.4
- **Java 버전**: 21
- **빌드 도구**: Gradle
- **데이터베이스**: H2 (인메모리)
- **주요 기능**: 스케줄링된 배치 작업 실행

## 🚀 주요 기능

### 1. 자동 스케줄링
- 10초마다 자동으로 배치 작업을 실행
- `@Scheduled` 어노테이션을 사용한 크론 스케줄링

### 2. Spring Batch 작업
- `testJob`: 기본 배치 작업
- `myStep`: 작업을 구성하는 스텝
- `TestTask`: 실제 작업을 수행하는 태스크릿

### 3. H2 데이터베이스
- 인메모리 H2 데이터베이스 사용
- H2 콘솔 활성화 (http://localhost:8080/h2-console)

## 🛠️ 기술 스택

- **Spring Boot**: 3.4.4
- **Spring Batch**: 배치 처리 프레임워크
- **H2 Database**: 인메모리 데이터베이스
- **Lombok**: 코드 간소화
- **Gradle**: 빌드 도구

## 📁 프로젝트 구조

```
src/
├── main/
│   ├── java/com/updown/batch/
│   │   ├── SpringBatchApplication.java      # 메인 애플리케이션 클래스
│   │   ├── BasicTaskJobConfiguration.java   # 배치 작업 설정
│   │   ├── JobScheduler.java               # 스케줄러
│   │   └── TestTask.java                   # 테스트 태스크
│   └── resources/
│       └── application.yml                 # 애플리케이션 설정
└── test/
    └── java/com/updown/batch/
        └── SpringBatchApplicationTests.java # 테스트 클래스
```

## 🚀 실행 방법

### 1. 프로젝트 빌드
```bash
./gradlew build
```

### 2. 애플리케이션 실행
```bash
./gradlew bootRun
```

### 3. JAR 파일로 실행
```bash
./gradlew bootJar
java -jar build/libs/batch-0.0.1-SNAPSHOT.jar
```

## ⚙️ 설정 정보

### 데이터베이스 설정
- **URL**: `jdbc:h2:mem:testdb`
- **사용자명**: `sa`
- **비밀번호**: (없음)
- **H2 콘솔**: http://localhost:8080/h2-console

### 스케줄링 설정
- **실행 주기**: 10초마다 (`*/10 * * * * ?`)
- **작업명**: `testJob`

## 📝 주요 클래스 설명

### SpringBatchApplication
메인 애플리케이션 클래스로 `@EnableScheduling` 어노테이션을 통해 스케줄링 기능을 활성화합니다.

### BasicTaskJobConfiguration
배치 작업의 설정을 담당하는 클래스입니다:
- `testJob`: 메인 배치 작업 빈
- `step`: 작업을 구성하는 스텝 빈
- `greetingTasklet`: 실제 작업을 수행하는 태스크릿

### JobScheduler
스케줄링을 담당하는 클래스로, 10초마다 `testJob`을 실행합니다.

### TestTask
실제 배치 작업을 수행하는 태스크릿으로, `Tasklet` 인터페이스를 구현합니다.

## 🔍 로그 확인

애플리케이션 실행 시 다음과 같은 로그를 확인할 수 있습니다:
- 스텝 초기화 로그
- 잡 초기화 로그
- 태스크 실행 로그
- 잡 실행 완료/실패 로그

## 🧪 테스트

테스트 실행:
```bash
./gradlew test
```

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 있습니다.
