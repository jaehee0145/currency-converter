# Currency Converter  
## 소개 
SpringBoot와 Vue.js로 만든 환율 계산기  
![img](.frontend/src/assets/img.PNG)

## 실행 방법
1. 해당 프로젝트를 clone
```bash
git clone https://github.com/jaehee0145/currency-converter.git
```
2. 프로젝트 폴더로 이동
```bash
cd currency-converter
```
3. Maven으로 프로젝트를 생성
```bash
mvn install
```
4. jar파일로 패키징된 프로젝트를 실행
```bash
java -jar target/currency-converter-0.0.1-SNAPSHOT.jar
```
5. localhost:8080 으로 접속

## 설계 및 구현   

1. 외부 API 호출
    - 최신 정보를 제공하는 것이 중요하다고 판단해서 환율 정보와 수취 금액을 요청할 때마다 외부 API를 요청하도록 설계했습니다. 
    - API 교체 경우를 대비해 Interface에 기능을 선언하고 Class에서 해당 기능을 구현했습니다. 
      또, 외부 API 요청 이외의 기능은 별도 Class에서 구현해서 모듈화시켰습니다.  
    - Timeout 설정을 이용해서 외부 API 요청 지연으로 인한 문제를 방지했습니다. 
    - 외부 API 요청에 필요한 Url과 Access key는 용이하게 관리하기 위해 application.properties에 저장했습니다.
    - 외부 API를 이용해 가져온 데이터를 사용하기 전에 유효성 검사를 진행합니다. 
        (송금 국가가 고정이므로 source가 "USD"로 일치하는지 등등)
    
2. 수취 국가, 송금액에 따라 환율 정보, 수취 금액 가져오기 
    - 수취 국가, 송금액을 입력하면 API 호출을 통해 환율 정보, 수취 금액을 가져옵니다. 
    - 요청을 보낼때마다 외부 API 요청을 통해 새로운 환율 정보를 가져와서 연산에 이용합니다.
    - Controller는 @RequestMapping을 통해 요청을 받고 ResponseEntity를 이용해 응답을 하는 역할만 하고 
     외부 API 요청, 환율 정보 연산, 수취 금액 연산 등의 비즈니스 로직은 Service가 처리합니다.  
    - 수취 국가, 송금액은 Frontend와 Backend에서 각각 유효성 검사를 합니다.    
    - 환율 정보, 수취 금액은 JavaScript 문법을 이용해 표기법에 맞게 표시됩니다. 

3. Vue.js를 이용해 화면 구현  
    - Maven 프로젝트 내에 Vue.js 프로젝트를 생성했습니다. 
    - 프로젝트 빌드시 WAS가 접근 가능한 경로에 html, css, js 파일이 생성되도록 설정해 연동했습니다.  

## 개선 사항  

1. 외부 API 호출 주기 설정  
    - 최신 정보를 유지하는 것이 중요하다고 생각해서 정보를 요청할 때마다 외부 API를 요청하도록 설계했습니다. 현재 이용하고 있는 Free Plan은 하루 주기로 정보를 업데이트 하기 때문에 API를 경제적으로 사용하기 위해 호출 주기를 설정할 예정입니다. 
2. 테스트 코드 추가  
    - 기본적인 테스트 코드만 작성한 상태라 다양한 경우를 테스트 할 수 있도록 테스트 코드를 추가할 예정입니다.  

## 개발 일지   
[[Currency Converter] Day 1](https://jaehee0145.github.io/currency%20converter/Currency-Converter-Day1/)  
[[Currency Converter] Day 2](https://jaehee0145.github.io/currency%20converter/Currency-Converter-Day2/)  
[[Currency Converter] Day 3](https://jaehee0145.github.io/currency%20converter/Currency-Converter-Day3/)  
[[Currency Converter] Day 4](https://jaehee0145.github.io/currency%20converter/Currency-Converter-Day4/)  