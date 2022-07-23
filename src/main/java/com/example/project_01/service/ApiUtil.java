package com.example.project_01.service;

import com.example.project_01.domain.TbPublicWifiInfo;
import com.example.project_01.domain.WifiInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ApiUtil {

    private static final String API_KEY = "4a616b5162616d793537507a777744";

    private static final int PAGE_SIZE = 1000;

    public static TbPublicWifiInfo callApi() throws IOException {
        // 해당 API는 한번 호출 할 때, 최대 범위는 1000개
        // 따라서 1 ~ 1000개씩 데이터를 호출을 반복

        String start = "1";
        String end = "1000";

        //TbPublicWifiInfo 클래스는 OpenAPI를 통해 받은 전체 데이터를 저장하는 클래스
        TbPublicWifiInfo tbPublicWifiInfo = new TbPublicWifiInfo();

        List<WifiInfo> wifiInfos = new ArrayList<>();
        int maxPage = Integer.MAX_VALUE;

        //start가 MAX_PAGE 이하 일 경우 아직 데이터가 있으므로
        // start가 <= MAX_PAGE일 때는 계속 api 호출
        while (Integer.valueOf(start) <= maxPage) {

            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
            urlBuilder.append("/" + URLEncoder.encode(API_KEY, "UTF-8")); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
            urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
            urlBuilder.append("/" + URLEncoder.encode(start, "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
            urlBuilder.append("/" + URLEncoder.encode(end, "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
            // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
            // openAPI 명세서와 동일하게 작성함

            //OkHttpClient를 통해 API호출

            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder()
                    .url(urlBuilder.toString())
                    .get();
            builder.addHeader("Content-type", "application/json");

            Request request = builder.build();
            Response response = client.newCall(request).execute();

            TbPublicWifiInfo pagingWifiInfo = null;

            // API호출이 성공적으로 이루어 졌다면
            if (response.isSuccessful()) {
                ResponseBody body = response.body();

                //문자열 형태로 결과를 저장
                String responseString = body.string();

                //결과를 Json 형태로 변환
                JsonObject jsonObject = JsonParser.parseString(responseString).getAsJsonObject();

                //Gson 라이브러리를 통해 Json 형태로 변환한 API 결과를 PagingWifiInfo 에 저장
                Gson gson = new Gson();
                pagingWifiInfo = gson.fromJson(jsonObject.getAsJsonObject("TbPublicWifiInfo"), TbPublicWifiInfo.class);

                //tbPublicWifiInfo의 listTotalCount 업데이트 (한번만 업데이트 해도 됨)
                tbPublicWifiInfo.setListTotalCount(pagingWifiInfo.getListTotalCount());
                maxPage = pagingWifiInfo.getListTotalCount();

                //앞서 선언한 wifiInfos에 api를 통해 받은 결과들을 저장함
                //1~1000, 1001~2000, 2001~3000 순으로 마지막 데이터까지 저장하기

                wifiInfos.addAll(pagingWifiInfo.getWifiInfos());

                //start와 end를 1000씩 증가시킴
                //end 14500이 끝으로 더 증가하지 않도록함
                start = String.valueOf(Integer.parseInt(start) + PAGE_SIZE);
                end = String.valueOf(Math.min(Integer.parseInt(end) + PAGE_SIZE, maxPage));

            }
        }

        tbPublicWifiInfo.setWifiInfos(wifiInfos);

        return tbPublicWifiInfo;
    }
}