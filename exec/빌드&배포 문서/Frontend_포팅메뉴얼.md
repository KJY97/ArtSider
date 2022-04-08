# Frontend 포팅매뉴얼

## 소개

artsider Frontend 빌드 매뉴얼

## 기술스택

| Name        | Version |
| ----------- | ------- |
| Vue.js      | 2.6.11  |
| Vue CLI     | 4.5.15  |
| Vuex        | 3.4.0   |
| Vue-router  | 3.2.0   |
| vuetify     | 2.6.0   |
| sass-loader | 10.0.0  |
| Node.js     | 10.15.3 |
| npm         | 6.4.1   |

## 개발 환경 구성

1. 프로젝트 다운로드

   ```bash
   git clone <repo URL> <folder-name>
   ```

2. frontend 폴더로 이동

   ```bash
   cd <folder-name>/frontend
   ```

3. 패키지 설치

   ```bash
   npm install
   ```

4. 프로젝트 실행

   ```bash
   npm run serve
   ```

## 디렉토리 구조

```
.
├─src
│   ├─ assets  // image, css, js 등의 리소스
│   ├─ components
│	│		└─ NavBar.vue
│   ├─ plugins
│	│		└─ vuetify.js
│   ├─ router // vue-router
│   ├─ store  // Vuex 관련 파일
│   └─ views  // 페이지 단위의 Vue 파일
│   	├─ account // 회원관리 컴포넌트, 프로필 페이지
│   	├─ home // 홈 및 메인 화면
│   	└─ show // 공연 관련 페이지
├─ Dockerfile
├─ package.json // 패키지 의존성 관리
└─ vue.config.js
```

