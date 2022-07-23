<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        thead {
            background-color: aquamarine;
        }
    </style>
</head>
<body>
    <h1><%= "와이파이 정보 구하기" %>
    </h1>
    <br/>
    <br/>
    <a href="/">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <br/>
    <br/>
    <form method="get" action="/">
        LAT: <input id="lat" name="lnt" type="text" />,
        LNT: <input id="lnt" name="lat" type="text" />
        <button id="myLocationButton" type="button">내 위치 가져오기</button>
        <input type="submit" value="근처 WIFI 정보 가져오기" />
    </form>
    <br/>
    <br/>
    <table>
        <thead>
            <tr>
                <th>거리</th>
                <th>관리번호</th>
                <th>자치구</th>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>상세주소</th>
                <th>설치위치(층)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망종류</th>
                <th>설치년도</th>
                <th>실내외구분</th>
                <th>WIFI접속환경</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</body>
</html>