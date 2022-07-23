import com.example.project_01.domain.TbPublicWifiInfo;
import com.example.project_01.domain.WifiHistory;
import com.example.project_01.domain.WifiInfo;
import com.example.project_01.service.ApiUtil;
import com.example.project_01.service.DBUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class DBTest {

    @Test
    public void insertWifiInfoTest() throws IOException, ClassNotFoundException {
        TbPublicWifiInfo tbPublicWifiInfo = ApiUtil.callApi();
        DBUtil.insertWifiInfos(tbPublicWifiInfo);
    }

    @Test
    public void selectWifiInfoTest() throws ClassNotFoundException {
        List<WifiInfo> wifiInfos = DBUtil.getWifiInfos();

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
        DBUtil.insertWifiHistory(wifiHistory);

        WifiHistory lastWifiHistory = DBUtil.getLastWifiHistory();
        int deletedCnt = DBUtil.deleteWifiHistory(lastWifiHistory.getId());

        assert(deletedCnt == 1);
    }

    @Test
    public void selectWifiHistoryTest() throws ClassNotFoundException {
        List<WifiHistory> wifiHistories = DBUtil.getWifiHistories();

        for (WifiHistory wifihistory : wifiHistories) {
            System.out.println(wifihistory);
        }

        assert(wifiHistories.size() > 0);
    }
}
