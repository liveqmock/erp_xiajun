package com.wangqin.globalshop.common.utils;

import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5.
 */
public class ExchangeRateUtil {

    public static Double exchangeRate = 9.188D;


    public static void getExchangeRate() {

        Map<String, Object> param = new HashMap<String, Object>();
        Date nowDate = new Date();
        Date taskDate = new Date(nowDate.getYear(),nowDate.getMonth(),nowDate.getDate(),17,0);
        if (nowDate.getTime()>taskDate.getTime()) {
            param.put("curDate",new SimpleDateFormat("yyyy-MM-dd").format(nowDate));
            //System.out.println("时间过了");
        } else {
            param.put("curDate",new SimpleDateFormat("yyyy-MM-dd").format(nowDate.getTime()-86400000));
            //System.out.println("时间还没到");
        }
        param.put("baseCurrency","HKD");
        param.put("transactionCurrency","EUR");
        String result = HttpClientUtil.post("http://www.unionpayintl.com/cardholderServ/serviceCenter/rate/search", null, param, "2", "2");
        if(StringUtils.isNotBlank(result)) {
            JSONObject jsonObject = JSONObject.fromObject(result);
            exchangeRate = Double.valueOf(jsonObject.get("exchangeRate").toString());
        }
    }
}
