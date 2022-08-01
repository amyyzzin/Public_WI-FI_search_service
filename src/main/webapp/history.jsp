<%@ page import="com.example.project_01.domain.WifiHistory" %>
<%@ page import="com.example.project_01.service.WifiHistoryDbUtil" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        thead {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: #04AA6D;
            color: white;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table td, table th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        table tr:nth-child(even) {background-color: #f2f2f2;}

        table tbody tr:hover {background-color: #ddd;}

        .delete-button {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1><%= "와이파이 정보 구하기" %>
    </h1>
    <br/>
    <br/>
    <a href="/">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기 </a>
    <br/>
    <br/>
    <table>
        <thead>
            <tr>
                <td>ID</td>
                <td>X 좌표</td>
                <td>Y 좌표</td>
                <td>조회일자</td>
                <td>비고</td>
            </tr>
        </thead>
        <tbody>
            <%
                List<WifiHistory> wifiHistories = WifiHistoryDbUtil.getWifiHistories();
            %>

            <% for (WifiHistory wifiHistory : wifiHistories) { %>
            <tr>
                <td><%=wifiHistory.getId()%></td>
                <td><%=wifiHistory.getLat()%></td>
                <td><%=wifiHistory.getLnt()%></td>
                <td><%=wifiHistory.getRegDate()%></td>
                <td class="delete-button"><button type="button" onclick="fn_delete(<%=wifiHistory.getId()%>)">삭제</button> </td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <script type="text/javascript">
        function fn_delete(id) {
            var object = new Object();
            object.id = id;

            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/delete?id=' + id, true);
            xhr.onload = function () {
                location.reload();
            };
            xhr.send();
        }
    </script>
</body>
</html>
