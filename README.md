# popxhi_팝업, 전시 공간 대여 예약 사이트 ✨



### 주제 : 크라우드 펀딩 사이트
  
### 개발기간 : 2023.12 ~ 2024.01

### 참여인원 : 4명
  
### 사용 도구
* Eclipse, HeidiSQL, Git
  
### 사용 기술
* MariaDB, JAVA, Spring Boot, JavaScript, jQuery, JSP, CSS3, HTML5, MyBatis

### 주제 설명 
* 개인, 단체, 기업이 창의적인 특정 아이디어나 프로젝트를 통해 불특정 다수를 상대로 활동 계획을 알리고 자금을 모집하거나 수익을 내며 프로젝트를 실현하는 크라우드 펀딩 사이트

### 선정배경
* 미닝 아웃(meaning out), 이 단어는 개인의 취향과 정치, 사회적 신념에 대해 솔직하고 거침없이 선언하는 행위를 의미하는 신조어입니다. 이러한 미닝 아웃 성향은 크라우드 펀딩에서 특히 강력한 힘을 발휘하고 있습니다. 단순히 제품의 기능과 장점 소개를 넘어 판매자가 중시하는 가치를 소비하는 고객이 증가하고 있다는 걸 분석할 수 있었습니다. 이를 토대로 미닝 아웃 성향을 가진 MZ세대를 저격하는 리워드형 크라우드 펀딩 사이트를 제작해보고 싶어 프로젝트를 진행하게 되었습니다.

### 제공기능
* (공통) 사용자와 관리자 공통으로 메인페이지에는 펀딩 상품 8개가 보이게 배치해 두었고, 카테고리별로 펀딩 상품 조회가 가능
* (관리자) 회원과 프로젝트 관리 및 승인 페이지가 있어 사이트의 전반적인 관리가 간편하도록 제작
* (사용자) 나의 정보 조회, 수정, 탈퇴가 가능하고 나의 펀딩 프로젝트와 결제 내용을 조회 가능
* 펀딩 프로젝트를 신청하는 페이지와 상품 정보를 보며 리워드를 선택할 수 있는 펀딩 상품 상세 페이지 제공
* 리워드를 선택하고 ‘펀딩 참여하기’ 버튼을 클릭하면 배송지를 입력한 뒤, 주문 정보 확인 페이지에서 ‘결제하기’ 버튼을 클릭하면 토스페이먼츠 결제창을 연동하여 펀딩 결제 진행

### 기대효과
* 사용자로서는 다양한 문화 생태계 조성에 소액을 통해 참여와 기부를 실천한다는 보람을 느낄 수 있습니다. 판매자로서는 소규모 프로젝트 및 창의적 아이디어에 대한 실행 자금을 마련할 수 있고, 제도권 금융을 이용할 수 없는 경우의 새로운 대출 기회 등의 장점이 있습니다.

### 담당업무
* 펀딩 프로젝트 신청 페이지 UI를 제작하고 신청 정보를 DB에 저장하며 특히 CKEditor를 활용하는 아이디어를 내어 프로젝트 상세 내용을 나타내는 곳을 더 다양하게 꾸밀 수 있도록 만듦
* 펀딩 프로젝트 상세 페이지에서 DB에 저장된 신청 정보를 불러오고 리워드 선택에 따른 총금액 계산과 ‘펀딩 참여하기’ 버튼을 클릭하면 주문 정보 입력 페이지 이동 구현
* ‘결제하기’ 버튼을 클릭하면 토스페이먼츠 결제창 API와 연동하여 실제 결제는 이루어지지 않지만, 가상으로 결제가 되도록 기능 제작
* 배송지 입력 페이지에서 입력한 주소가 DB에 저장되지 않은 오류를 해결
* 산출물로는 개발예정보고서, 플로우차트, UI 페이지 레이아웃를 맡아 주도적으로 작성
