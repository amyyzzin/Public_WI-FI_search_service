package com.example.project_01.service;

import com.example.project_01.domain.TbPublicWifiInfo;
import com.example.project_01.domain.WifiInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.project_01.service.WifiHistoryDbUtil.*;

public class WifiDbUtil {

    private final static String WIFI_DB_FILE = "C:\\Users\\82109\\OneDrive\\바탕 화면\\제로베이스 백엔드 스쿨 _2기_김우진(220717)\\제로베이스 백엔드 스쿨 _2기_김우진(220723)_소스파일\\WIFI.db";

    public static List<WifiInfo> getWifiInfos() throws ClassNotFoundException {
        String dbFile = WIFI_DB_FILE;
        Class.forName("org.sqlite.JDBC");
        List<WifiInfo> wifiInfos = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM WIFI")

        ) {
            while (resultSet.next()) {
                WifiInfo wifiInfo = convertResultSetToWifiInfo(resultSet);

                wifiInfos.add(wifiInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wifiInfos;
    }

    public static void insertWifiInfos(TbPublicWifiInfo tbPublicWifiInfo) throws ClassNotFoundException {
        String dbFile = WIFI_DB_FILE;
        Class.forName("org.sqlite.JDBC");

        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
                PreparedStatement preparedStatement = connection.prepareStatement(getWifiInsertQuery())
        ) {
            //SQL 수행
            int idx = 0;
            connection.setAutoCommit(false);

            for (WifiInfo wifiInfo : tbPublicWifiInfo.getWifiInfos()) {
                preparedStatement.setString(1, wifiInfo.getMgrNo());
                preparedStatement.setString(2, wifiInfo.getWrdofc());
                preparedStatement.setString(3, wifiInfo.getMainNm());
                preparedStatement.setString(4, wifiInfo.getAdres1());
                preparedStatement.setString(5, wifiInfo.getAdres2());
                preparedStatement.setString(6, wifiInfo.getInstlFloor());
                preparedStatement.setString(7, wifiInfo.getInstlTy());
                preparedStatement.setString(8, wifiInfo.getInstlMby());
                preparedStatement.setString(9, wifiInfo.getSvcSe());
                preparedStatement.setString(10, wifiInfo.getCmcwr());
                preparedStatement.setString(11, wifiInfo.getCnstcYear());
                preparedStatement.setString(12, wifiInfo.getInoutDoor());
                preparedStatement.setString(13, wifiInfo.getRemars3());
                preparedStatement.setString(14, wifiInfo.getLat());
                preparedStatement.setString(15, wifiInfo.getLnt());
                preparedStatement.setString(16, wifiInfo.getWorkDttm());
                preparedStatement.addBatch();

                if (idx++ % BATCH_SIZE == 0) {
                    preparedStatement.executeBatch();
                }
            }

            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
