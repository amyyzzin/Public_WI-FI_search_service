package com.example.project_01.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WifiInfo {

    // 관리번호
    @SerializedName("X_SWIFI_MGR_NO")
    private String mgrNo;

    // 자치구
    @SerializedName("X_SWIFI_WRDOFC")
    private String wrdofc;

    //와이파이명
    @SerializedName("X_SWIFI_MAIN_NM")
    private String mainNm;

    // 도로명주소
    @SerializedName("X_SWIFI_ADRES1")
    private String adres1;

    // 상세주소
    @SerializedName("X_SWIFI_ADRES2")
    private String adres2;

    // 설치위치(층)
    @SerializedName("X_SWIFI_INSTL_FLOOR")
    private String instlFloor;

    // 설치유형
    @SerializedName("X_SWIFI_INSTL_TY")
    private String instlTy;

    // 설치기관
    @SerializedName("X_SWIFI_INSTL_MBY")
    private String instlMby;

    // 서비스구분
    @SerializedName("X_SWIFI_SVC_SE")
    private String svcSe;

    // 망종류
    @SerializedName("X_SWIFI_CMCWR")
    private String cmcwr;

    // 설치년도
    @SerializedName("X_SWIFI_CNSTC_YEAR")
    private String cnstcYear;

    // 실내외구분
    @SerializedName("X_SWIFI_INOUT_DOOR")
    private String inoutDoor;

    // WIFI접속환경
    @SerializedName("X_SWIFI_REMARS3")
    private String remars3;

    // X좌표
    @SerializedName("LNT")
    private String lat;

    // Y좌표
    @SerializedName("LAT")
    private String lnt;

    // 작업일자
    @SerializedName("WORK_DTTM")
    private String workDttm;
}