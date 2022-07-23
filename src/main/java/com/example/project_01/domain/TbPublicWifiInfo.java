package com.example.project_01.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class TbPublicWifiInfo {

    @SerializedName(value = "list_total_count")
    private int listTotalCount;

    @SerializedName("row")
    public List<WifiInfo> wifiInfos = new ArrayList<>();
}
