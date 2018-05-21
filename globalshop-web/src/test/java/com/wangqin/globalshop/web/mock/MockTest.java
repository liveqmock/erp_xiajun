package com.wangqin.globalshop.web.mock;

import com.wangqin.globalshop.biz1.api.AppApi;
import com.wangqin.globalshop.biz1.api.dto.ConfigDTO;
import com.wangqin.globalshop.biz1.api.dto.request.AppRequest;
import com.wangqin.globalshop.biz1.api.dto.response.BaseResp;
import com.wangqin.globalshop.biz1.app.remote.RemoteMock;
import com.wangqin.globalshop.common.utils.LogWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Patrick on 2018/5/15.
 */
@Slf4j
public class MockTest {

    @Test
    public void testMock() throws Exception {
        AppApi appApi = RemoteMock.createAppApi();
        BaseResp<ConfigDTO> config = appApi.getConfig(new AppRequest());

        LogWorker.log(log,"log:","{}",config);
    }


}