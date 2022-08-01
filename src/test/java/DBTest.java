import com.example.project_01.domain.TbPublicWifiInfo;
import com.example.project_01.domain.WifiHistory;
import com.example.project_01.domain.WifiInfo;
import com.example.project_01.service.ApiUtil;
import com.example.project_01.service.Wifi_History_Db_Util;
import com.example.project_01.service.Wifi_Db_Util;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class DBTest {

    @Test
    public void insertWifiInfoTest() throws IOException, ClassNotFoundException {
        TbPublicWifiInfo tbPublicWifiInfo = ApiUtil.callApi();
        Wifi_Db_Util.insertWifiInfos(tbPublicWifiInfo);
    }

    @Test
    public void selectWifiInfoTest() throws ClassNotFoundException {
        List<WifiInfo> wifiInfos = Wifi_Db_Util.getWifiInfos();

        for (WifiInfo wifiInfo : wifiInfos) {
            System.out.println(wifiInfo);
        }

        assert(wifiInfos.size() > 0);
    }

    @Test
    public void insertAndDeleteWifiHistoryTest() throws ClassNotFoundException {
        WifiHistory wifiHistory = new WifiHistory();
        wifiHistory.setLat("0.0");
        wifiHistory.setLnt("0.0");
        Wifi_History_Db_Util.insertWifiHistory(wifiHistory);

        WifiHistory lastWifiHistory = Wifi_History_Db_Util.getLastWifiHistory();
        int deletedCnt = Wifi_History_Db_Util.deleteWifiHistory(lastWifiHistory.getId());

        assert(deletedCnt == 1);
    }

    @Test
    public void selectWifiHistoryTest() throws ClassNotFoundException {
        List<WifiHistory> wifiHistories = Wifi_History_Db_Util.getWifiHistories();

        for (WifiHistory wifihistory : wifiHistories) {
            System.out.println(wifihistory);
        }

        assert(wifiHistories.size() > 0);
    }
}
