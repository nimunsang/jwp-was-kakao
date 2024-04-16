# 웹 애플리케이션 서버
## 진행 방법
* 웹 애플리케이션 서버 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 요구사항
- [X] GET /index.html 응답하기
  - [X] 요청 헤더에서 {타입, URL} 추출하기
  - [X] URL에 해당하는 파일을 읽기
  - [X] 파일 내용을 클라이언트에게 응답하기
- [X] CSS 지원하기
- [X] Query String 파싱
- [X] POST 방식으로 회원가입
- [X] Redirect
- [ ] “로그인” 메뉴를 클릭하면 `http://localhost:8080/user/login.html` 으로 이동한다.
- [ ] 회원가입한 사용자로 로그인할 수 있다.
  - [ ] 로그인이 성공하면 `index.html`로 이동한다.
  - [ ] 로그인이 실패하면 `/user/login_failed.html`로 이동한다.
- [ ] 세션 아이디를 전달하는 이름으로 `JSESSIONID`를 사용한다. 
- [ ] 서버에서 HTTP 응답을 전달할 때 응답 헤더에 `Set-Cookie`를 추가하고 `JSESSIONID=656cef62-e3c4-40bc-a8df-94732920ed46` 형태로 값을 전달하면 클라이언트 요청 헤더의 `Cookie` 필드에 값이 추가된다.
