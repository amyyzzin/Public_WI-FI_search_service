package com.example.project_01.servlet;

import com.example.project_01.domain.WifiHistory;
import com.example.project_01.domain.WifiInfo;
import com.example.project_01.service.WifiHistoryDbUtil;
import com.example.project_01.service.WifiDbUtil;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");

        //Hello
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("thead { padding-top: 12px;\n" +
                "            padding-bottom: 12px;\n" +
                "            background-color: #04AA6D;\n" +
                "            color: white;\n" +
                "            text-align: center;}");
        out.println("table { width: 100%;\n" +
                "            border-collapse: collapse; }");
        out.println("table td, table th {\n" +
                "            border: 1px solid #ddd;\n" +
                "            padding: 8px;}");
        out.println("table tr:nth-child(even) { background-color: #f2f2f2; }");
        out.println("table tbody tr:hover { background-color: #ddd; }");
        out.println("</style>");
        out.println("<body>");
        out.println("<h1>와이파이 정보 구하기</h1>");
        out.println("<br/><br/>");
        out.println("<a href=\"/\">홈</a> | <a href=\"history.jsp\">위치 히스토리 목록</a> | <a href=\"load-wifi.jsp\">Open API 와이파이 정보 가져오기</a>");
        out.println("<br/><br/>");
        out.println("<form method=\"get\" action=\"/\">");

        if (lat == null) {
            out.println("LAT: <input id=\"lat\" name=\"lat\" type=\"text\" value=\"0.0\" />, ");
        } else {
            out.println("LAT: <input id=\"lat\" name=\"lat\" type=\"text\" value=\"" + lat + "\"/>, ");
        }

        if (lnt == null) {
            out.println("LNT: <input id=\"lnt\" name=\"lnt\" type=\"text\" value=\"0.0\" /> ");
        } else {
            out.println("LAT: <input id=\"lat\" name=\"lat\" type=\"text\" value=\"" + lnt + "\"/>, ");
        }

        out.println("<button id=\"myLocationButton\" type=\"button\" onclick=\"getLocation()\">위치 가져오기</button>");
        out.println("<input type=\"submit\" value=\"근처 WIFI 정보 가져오기\" />");
        out.println("</form>");
        out.println("<br/><br/>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>거리(km)</th>");
        out.println("<th>관리번호</th>");
        out.println("<th>자치구</th>");
        out.println("<th>와이파이명</th>");
        out.println("<th>도로명주소</th>");
        out.println("<th>상세주소</th>");
        out.println("<th>설치위치(층)</th>");
        out.println("<th>설치유형</th>");
        out.println("<th>설치기관</th>");
        out.println("<th>서비스구분</th>");
        out.println("<th>망종류</th>");
        out.println("<th>설치년도</th>");
        out.println("<th>실내외구분</th>");
        out.println("<th>WIFI접속환경</th>");
        out.println("<th>X좌표</th>");
        out.println("<th>Y좌표</th>");
        out.println("<th>작업일자</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        if (lat != null && lnt != null) {
            try {
                Double doubleLat = Double.valueOf(lat);
                Double doubleLnt = Double.valueOf(lnt);

                List<WifiInfo> wifiInfos = WifiDbUtil.getWifiInfos();
                

                if (wifiInfos.size() == 0) {
                    out.println("<tr>");
                    out.println("<td colspan=\"17\" style=\"text-align: center\">Open API를 통해 와이파이 정보를 가져온 뒤 조회해주세요.</td>");
                    out.println("</tr>");
                } else {
                    WifiHistory wifiHistory = new WifiHistory();
                    wifiHistory.setLat(lat);
                    wifiHistory.setLnt(lnt);
                    WifiHistoryDbUtil.insertWifiHistory(wifiHistory);

                    wifiInfos.sort((o1, o2) -> {
                        double o1Distance = getDistance(doubleLat, doubleLnt, o1);
                        double o2Distance = getDistance(doubleLat, doubleLnt, o2);

                        return Double.compare(o1Distance, o2Distance);
                    });

                    for (int i = 0; i < Math.min(20, wifiInfos.size()); i++) {
                        out.println("<tr>");
                        out.println("<td>" + String.format("%.4f", getDistance(doubleLat, doubleLnt, wifiInfos.get(i))) + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getMgrNo() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getWrdofc() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getMainNm() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getAdres1() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getAdres2() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getInstlFloor() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getInstlTy() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getInstlMby() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getSvcSe() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getCmcwr() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getCnstcYear() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getInoutDoor() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getRemars3() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getLat() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getLnt() + "</td>");
                        out.println("<td>" + wifiInfos.get(i).getWorkDttm() + "</td>");
                        out.println("</tr>");
                    }
                }
            } catch (Exception e) {
                out.println("<tr>");
                out.println("<td colspan=\"17\" style=\"text-align: center\">위도, 경도를 올바르게 입력해주세요.</td>");
                out.println("</tr>");
            }
        } else {
            out.println("<tr>");
            out.println("<td colspan=\"17\" style=\"text-align: center\">위치 정보를 입력한 후에 조회해 주세요.</td>");
            out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");
        out.println("<script>");
        out.println("var lat = document.getElementById(\"lat\");");
        out.println("var lnt = document.getElementById(\"lnt\");");
        out.println("function getLocation() {");
        out.println("if (navigator.geolocation) {");
        out.println("navigator.geolocation.getCurrentPosition(showPosition);");
        out.println("}");
        out.println("}");
        out.println("function showPosition(position) {");
        out.println("lat.value = position.coords.latitude;");
        out.println("lnt.value = position.coords.longitude;");
        out.println("}");
        out.println("</script>");
        out.println("</body>");
        out.println("</head>");
        out.println("</html>");
    }

    /**
     * 출처: https://se-jung-h.tistory.com/entry/%EC%9E%90%EB%B0%94intelij-%EC%A2%8C%ED%91%9C%EC%9C%84%EB%8F%84%EA%B2%BD%EB%8F%84%EB%A1%9C-%EC%8B%A4%EC%A0%9C-%EA%B1%B0%EB%A6%AC-%EA%B5%AC%ED%95%98%EA%B8%B0
     */
    private Double getDistance(Double lat, Double lnt, WifiInfo wifiInfo) {
        Double lat2 = Double.valueOf(wifiInfo.getLat());
        Double lnt2 = Double.valueOf(wifiInfo.getLnt());
        double theta = lnt - lnt2;
        double dist = Math.sin(deg2rad(lat)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist/1000;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI/180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}