# Shortening Service Project

## 설명
  - originUrlMap 
    - key : 요청 받은 url 문자열의 hashcode 
    - value : ShorteningUrl VO 객체로 shorteningUrl 과 callCount 정보를 가진다.
    - 동일한 url 요청인 경우 문자열의 hashcode 가 동일하기 때문에 callCount 증가
    - 신규 url 요청인 경우 8자리 문자열 생성
  - shorteningUrlMap
    - key : shorteningUrl
    - value : 요청 받은 origin url

## 빌드 / 실행
  - 서비스 실행을 위해서는Git, Apache Maven, Java 8 사전 설치 필수 
  - _deploy.sh 스크립트를 실행하여 최신 소스 업데이트와 빌드 진행
  - _startup.sh 스크립트를 실행하여 jar 파일 실행
  - 스크립트 허가 거부 발생시 해당 명령어 실행 ex) chmod u+x _deploy.sh
  
## 테스트
  - Postman 또는 curl 명령어 실행
  - ex) curl 명령어 \
  curl --request POST \
    --url http://localhost:8080/v1/shortenurl \
    --header 'cache-control: no-cache' \
    --header 'content-type: application/json' \
    --data '{\n	"url" : "https://github.com/heetlee/shortening-service"\n}'