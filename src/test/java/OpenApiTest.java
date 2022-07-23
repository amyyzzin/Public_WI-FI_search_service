import com.example.project_01.domain.TbPublicWifiInfo;
import com.example.project_01.service.ApiUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class OpenApiTest {
    private static final int TOTAL_WIFI_CNT = 14493;

    @Test
    public void callApiTest() throws IOException {
        TbPublicWifiInfo tbPublicWifiInfo = ApiUtil.callApi();

        assert(tbPublicWifiInfo.getListTotalCount() == TOTAL_WIFI_CNT);
    }
}
