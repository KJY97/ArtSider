# Backend 포팅매뉴얼

## 소개

artsider Backend 빌드 매뉴얼

web server로 spring boot를 사용하며 데이터 처리는 fastApi에서 진행한다.

## 기술스택

| Name        | Version    |
| ----------- | ---------- |
| Java        | 11         |
| Spring boot | 2.5.10     |
| Gradle      | gradle-7.4 |
| JPA         |            |
| MySQL       | 8.0.28     |
| Python      | 3.8        |
| FastApi     |            |

## 개발 환경 구성

> Windows 10을 기준으로 개발 환경 구성 방법을 설명한다.

### 1. OpenJDK 설치

- JDK 다운로드 사이트에서 **11 GA 버전** 설치 파일 다운로드 및 실행

  - OpenJDK 공식 웹사이트 : https://jdk.java.net/archive/

- 명령 프롬프트(cmd) 확인

  ```bash
  $ java -version
  openjdk version "11" 2018-09-25
  OpenJDK Runtime Environment 18.9 (build 11+28)
  OpenJDK 64-Bit Server VM 18.9 (build 11+28, mixed mode)
  ```


## 2. DB 구성

> 이미 설치되어 있거나 원격 DB를 사용하는 경우 설치 부분 생략

- MySQL 사이트에서 **Windows (x86, 32-bit), MSI Installer (435.7M)** 설치 파일 다운로드 및 실행

  - MySQL 공식 웹사이트 : https://dev.mysql.com/downloads/mysql/

- 명령 프롬프트(cmd) 확인

  ```bash
  $ mysql --version
  ```

- dump.sql 파일 실행하여 DB 생성
- DB.properties

  ```bash
  spring.datasource.url=jdbc:mysql://j6b202.p.ssafy.io:3306/artsider?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=UTF-8

  spring.datasource.username=artsider
  spring.datasource.password=artsider202!
  ```

### 2. Python 설치

- **Python v3.8**로 설치

  - Python 공식 웹사이트 : https://www.python.org/downloads/

- 명령 프롬프트(cmd) 확인

  ```bash
  $ python -V
  Python 3.8.10
  ```

### 3. 프로젝트 다운로드

- 프로젝트 다운로드

  ```bash
  git clone <repo URL>	
  ```

- `{origin_DIR}/backend/backSpring/src/main/resources/application.properties`로 이동

  - 자신의 환경에 맞게 DB 설정
  
- `{origin_DIR}/backend/backSpring/src/main/java/com/ssafy/myapp/api/service/ShowServiceImpl.java`로 이동

  - **findUserBasedRecommend**메소드에서 **baseURL**을 `127.0.0.1:8000`으로 수정


### 4. Python 가상환경 생성 및 라이브러리 설치

- `{origin_DIR}/backend/fastApi/`로 이동

  ```bash
  $ pip3 install virtualenv
  $ python -m venv .venv
  $ source .venv/Scripts/activate
  
  $ pip3 install --upgrade pip
  $ pip3 install -r requirements.txt
  ```

-  형태소 분석기 mecab 설치

  https://uwgdqo.tistory.com/363를 참고하여 **mecab**을 설치한다.

- fastApi 프로젝트 실행

  ```bash
  $ uvicorn app.main:app
  ```

## 디렉토리 구조

### Spring Boot

```
.
└─ src
    ├─ main
         ├─ java
         │  └─ com
         │      └─ ssafy
         │          └─ myapp
         │              ├─ api
         │    			│   ├─ controller  // Controller 단위의 클래스
         │    			│   ├─ request
         │				│	├─ response
         │				│	└─ service  // 객체 서비스 클래스
         │              ├─ common
         │				│	├─ auth
         │				│	└─ util
         │              ├─ config  // Spring Config 파일
         │              └─ db
         │              	├─entity  // JPA entity 클래스
         │              	├─mapping
         │              	└─respository
         └─ resources
         	└─application.properties// db설정 properties 존재
```

### fastApi(빅데이터 관련)

```
.
├─ app
│   ├─ main
│   │    └─router // RestApi routes 폴더
│   │		├─ .gitignore
│   │		├─ keyword_result.py
│   │		├─ related_show.py
│   │		├─ tag_based_rec_show.py
│   │		└─ user_based_CF.py
│   ├─ __init__.py
│   ├─ .gitignore
│   └─ main.py // 실행 파일
├─ Dockerfile
├─ README.md
└─ requirements.txt // 패키지 의존성 관리
```

