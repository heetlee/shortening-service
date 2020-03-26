# Shortening Service Project

### 설명
  - originUrlMap 
    - key : 요청 받은 url 문자열의 hashcode key
    - value : ShorteningUrl VO 객체로 shorteningUrl 과 callCount 정보를 가진다.
    - 동일한 url 요청인 경우 문자열의 hashcode 가 동일하기 때문에 callCount 증가
    - 신규 url 요청인 경우 8자리 문자열 생성
  - shorteningUrlMap
    - key : shorteningUrl
    - value : 요청 받은 origin url

### 빌드 / 실행
  - 서비스 실행을 위해서는 Git, Apache Maven, Java 8 사전 설치 필수
###### git clone 
```bash
> git clone https://github.com/heetlee/shortening-service.git
> cd shortening-service
```
###### 최신 소스 업데이트와 Maven 빌드
```bash
> ./_deploy.sh
```
###### 서비스 실행
```bash
> ./_startup.sh
```
###### 스크립트 허가 거부 발생시 참고
```bash
> chmod u+x _deploy.sh
> chmod u+x _startup.sh
```

### 서비스 정보

    * 서버 포트 : 8080
    * url : http://{서버IP}:8080
        
### Rest API 테스트
  - Postman 또는 curl 명령어 실행
  - ex) curl 명령어 \
  curl --request POST \
    --url http://localhost:8080/v1/shortenurl \
    --header 'cache-control: no-cache' \
    --header 'content-type: application/json' \
    --data '{\n	"url" : "https://github.com/heetlee/shortening-service"\n}'
    
### 화면 테스트
  - step1. URL 입력폼 제공
  
  - step2. URL Shortening Key 생성
  
  - step3. 동일한 URL에 대한 요청 수 정보
  
  - step4. Shortening 된 URL을 요청받으면 원래 URL로 리다이렉트