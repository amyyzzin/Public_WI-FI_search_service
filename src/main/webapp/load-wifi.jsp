<%@ page import="com.example.project_01.domain.TbPublicWifiInfo" %>
<%@ page import="com.example.project_01.service.ApiUtil" %>
<%@ page import="com.example.project_01.service.Wifi_History_Db_Util" %>
<%@ page import="com.example.project_01.service.Wifi_Db_Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style>
        body {
            text-align: center;
        }
    </style>
    <title>와이파이 정보 구하기</title>
</head>
<body>

<%
    TbPublicWifiInfo tbPublicWifiInfo = ApiUtil.callApi();
    Wifi_Db_Util.insertWifiInfos(tbPublicWifiInfo);
%>
<h1><%= tbPublicWifiInfo.getListTotalCount() %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>

    <a href="/">홈 으로 가기</a>

</body>
</html>