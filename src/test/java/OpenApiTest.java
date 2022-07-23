import com.example.project_01.domain.TbPublicWifiInfo;
import com.example.project_01.service.ApiUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class OpenApiTest {
    @Test
    public void callApiTest() throws IOException {
        TbPublicWifiInfo tbPublicWifiInfo = ApiUtil.callApi();

        assert(tbPublicWifiInfo.getWifiInfos() != null
                && tbPublicWifiInfo.getWifiInfos().size() > 0);
    }
}
