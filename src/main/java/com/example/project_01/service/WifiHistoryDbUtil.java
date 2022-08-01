package com.example.project_01.service;

import com.example.project_01.domain.WifiHistory;
import com.example.project_01.domain.WifiInfo;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class WifiHistoryDbUtil {
    public final static int BATCH_SIZE = 1000;

    private final static String WIFI_HISTORY_DB_FILE = "C:\\Users\\82109\\OneDrive\\바탕 화면\\제로베이스 백엔드 스쿨 _2기_김우진(220717)\\제로베이스 백엔드 스쿨 _2기_김우진(220723)_소스파일\\WIFI_HISTORY.db";


    public static List<WifiHistory> getWifiHistories() throws ClassNotFoundException {
        String dbFile = WIFI_HISTORY_DB_FILE;
        Class.forName("org.sqlite.JDBC");
        List<WifiHistory> wifiHistories = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT id, lat, lnt, datetime(reg_date, 'localtime') as reg_date FROM WIFI_HISTORY")
        ) {
            while (resultSet.next()) {
                WifiHistory wifiHistory = convertResultSetToWifiHistory(resultSet);

                wifiHistories.add(wifiHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wifiHistories;
    }

    public static WifiHistory getLastWifiHistory() throws ClassNotFoundException {
        String dbFile = WIFI_HISTORY_DB_FILE;
        Class.forName("org.sqlite.JDBC");
        WifiHistory wifiHistory = null;

        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM WIFI_HISTORY ORDER BY REG_DATE DESC LIMIT 1")
        ) {
            if (resultSet.next()) {
                wifiHistory = convertResultSetToWifiHistory(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wifiHistory;
    }


    public static void insertWifiHistory(WifiHistory wifiHistory) throws ClassNotFoundException {
        String dbFile = WIFI_HISTORY_DB_FILE;
        Class.forName("org.sqlite.JDBC");

        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
                PreparedStatement preparedStatement = connection.prepareStatement(getWifiHistoryInsertQuery())
        ) {
            //SQL 수행
            preparedStatement.setString(1, wifiHistory.getLat());
            preparedStatement.setString(2, wifiHistory.getLnt());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int deleteWifiHistory(int id) throws ClassNotFoundException {
        String dbFile = WIFI_HISTORY_DB_FILE;
        Class.forName("org.sqlite.JDBC");
        int deletedCnt = 0;

        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
                PreparedStatement preparedStatement = connection.prepareStatement(getWifiHistoryDeleteQuery())
        ) {
            //SQL 수행
            preparedStatement.setInt(1, id);

            deletedCnt = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deletedCnt;
    }

    public static WifiInfo convertResultSetToWifiInfo(ResultSet resultSet) throws SQLException {
        WifiInfo wifiInfo = new WifiInfo();
        wifiInfo.setMgrNo(resultSet.getString("MGR_NO"));
        wifiInfo.setWrdofc(resultSet.getString("WRDOFC"));
        wifiInfo.setMainNm(resultSet.getString("MAIN_NM"));
        wifiInfo.setAdres1(resultSet.getString("ADRES1"));
        wifiInfo.setAdres2(resultSet.getString("ADRES2"));
        wifiInfo.setInstlFloor(resultSet.getString("INSTL_FLOOR"));
        wifiInfo.setInstlTy(resultSet.getString("INSTL_TY"));
        wifiInfo.setInstlMby(resultSet.getString("INSTL_MBY"));
        wifiInfo.setSvcSe(resultSet.getString("SVC_SE"));
        wifiInfo.setCmcwr(resultSet.getString("CMCWR"));
        wifiInfo.setCnstcYear(resultSet.getString("CNSTC_YEAR"));
        wifiInfo.setInoutDoor(resultSet.getString("INOUT_DOOR"));
        wifiInfo.setRemars3(resultSet.getString("REMARS3"));
        wifiInfo.setLat(resultSet.getString("LAT"));
        wifiInfo.setLnt(resultSet.getString("LNT"));
        wifiInfo.setWorkDttm(resultSet.getString("WORK_DTTM"));

        return wifiInfo;
    }

    public static WifiHistory convertResultSetToWifiHistory(ResultSet resultSet) throws SQLException {
        WifiHistory wifiHistory = new WifiHistory();
        wifiHistory.setId(resultSet.getInt("ID"));
        wifiHistory.setLat(resultSet.getString("LAT"));
        wifiHistory.setLnt(resultSet.getString("LNT"));
        wifiHistory.setRegDate(Instant.ofEpochMilli(resultSet.getDate("REG_DATE").getTime())
                .atZone(ZoneId.systemDefault()).toLocalDateTime());

        return wifiHistory;
    }

    static String getWifiInsertQuery() {
        String sql = "INSERT INTO WIFI (" +
                "MGR_NO" +
                ", WRDOFC" +
                ", MAIN_NM" +
                ", ADRES1" +
                ", ADRES2" +
                ", INSTL_FLOOR" +
                ", INSTL_TY" +
                ", INSTL_MBY" +
                ", SVC_SE" +
                ", CMCWR" +
                ", CNSTC_YEAR" +
                ", INOUT_DOOR" +
                ", REMARS3" +
                ", LAT" +
                ", LNT" +
                ", WORK_DTTM) VALUES (" +
                "?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?)";

        return sql;
    }

    private static String getWifiHistoryInsertQuery() {
        String sql = "INSERT INTO WIFI_HISTORY (" +
                "LAT" +
                ", LNT) VALUES (" +
                "?" +
                ", ?)";

        return sql;
    }

    private static String getWifiHistoryDeleteQuery() {
        String sql = "DELETE FROM WIFI_HISTORY WHERE ID = ?";
        return sql;
    }
}
