# Public_WI-FI_search_service

<h3><b>서울시 OPEN API를 통해 내 위치 기반 공공 와이파이 정보를 확인하는 웹서비스 입니다.</b></h3>

* 현재 소스파일 내 src//main//java//com.example.project_01//service//DBUtil 파일 내 설정 된 경로를 해당 파일의 다운로드 된 절대 경로 값으로 설정해야합니다.

* 이 프로젝트에서 사용하는 DB파일은 총 2개이며, WIFI.db와 WIFI_HISTORY.db 입니다. 
* 아래 예시는 현재 DBUtil상 지정 되어 있는 절대 경로 값입니다. 

* private final static String WIFI_DB_FILE = 
  "C:\\Users\\82109\\OneDrive\\바탕 화면\\제로베이스 백엔드 스쿨 _2기_김우진(220717)\\제로베이스 백엔드 스쿨 _2기_김우진(220723)_소스파일\\WIFI.db";
* private final static String WIFI_HISTORY_DB_FILE = 
  "C:\\Users\\82109\\OneDrive\\바탕 화면\\제로베이스 백엔드 스쿨 _2기_김우진(220717)\\제로베이스 백엔드 스쿨 _2기_김우진(220723)_소스파일\\WIFI_HISTORY.db";

* DBUtil 을 WifiDbUtil, WifiHistoryUtil 로 분리 작업 하였습니다.
