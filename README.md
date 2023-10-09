# ResumeGenerator

개요 :
패스트캠퍼스 사전 과제 - 입력받은 정보를 토대로 이력서(자기소개서)를 자동 생성해준다.

구성 :
controller - ResumeController : 이력서 자동생성 접근 컨트롤러.
				                      ▶로컬, 웹 각기 접근을 염두해두었다. (웹 미구현)

service - GetReportInfo : 이력서 생성정보 입력처리 서비스
			                    ▶입력받은 정보를 가공하고 VO에 담는 역할을 한다.
	      - GenerateReport : 이력서 생성 서비스
			                    ▶GetReportInfo의 저장 된 정보와 POI 라이브러리를 이용하여 이력서 엑셀파일을 생성한다.
VO - Carrer
		▶경력정보
	  - Education
		▶교육정보
	  - PersonInfo
		▶개인정보
