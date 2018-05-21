package com.wangqin.globalshop.biz1.app.remote;

import com.wangqin.globalshop.biz1.api.AppApi;
import com.wangqin.globalshop.biz1.api.dto.ConfigDTO;
import com.wangqin.globalshop.biz1.api.dto.request.AppRequest;
import com.wangqin.globalshop.biz1.api.dto.response.BaseResp;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author  patrick
 */
public class RemoteMock {

    public static AppApi createAppApi(){
        AppApi mockApi = mock(AppApi.class);
        when(mockApi.getConfig(any(AppRequest.class))).thenReturn(new BaseResp<ConfigDTO>().isSuccess());
        return mockApi;
    }
}
