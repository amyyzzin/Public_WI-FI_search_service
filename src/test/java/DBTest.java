import com.example.project_01.domain.TbPublicWifiInfo;
import com.example.project_01.domain.WifiHistory;
import com.example.project_01.domain.WifiInfo;
import com.example.project_01.service.ApiUtil;
import com.example.project_01.service.WifiHistoryDbUtil;
import com.example.project_01.service.WifiDbUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class DBTest {

    @Test
    public void insertWifiInfoTest() throws IOException, ClassNotFoundException {
        TbPublicWifiInfo tbPublicWifiInfo = ApiUtil.callApi();
        WifiDbUtil.insertWifiInfos(tbPublicWifiInfo);
    }

    @Test
    public void selectWifiInfoTest() throws ClassNotFoundException {
        List<WifiInfo> wifiInfos = WifiDbUtil.getWifiInfos();

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
        WifiHistoryDbUtil.insertWifiHistory(wifiHistory);

        WifiHistory lastWifiHistory = WifiHistoryDbUtil.getLastWifiHistory();
        int deletedCnt = WifiHistoryDbUtil.deleteWifiHistory(lastWifiHistory.getId());

        assert(deletedCnt == 1);
    }

    @Test
    public void selectWifiHistoryTest() throws ClassNotFoundException {
        List<WifiHistory> wifiHistories = WifiHistoryDbUtil.getWifiHistories();

        for (WifiHistory wifihistory : wifiHistories) {
            System.out.println(wifihistory);
        }

        assert(wifiHistories.size() > 0);
    }
}
