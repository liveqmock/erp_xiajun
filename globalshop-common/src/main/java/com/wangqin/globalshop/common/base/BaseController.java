package src.main.java.com.wangqin.globalshop.common.base;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.wangqin.globalshop.common.result.Result;
import com.wangqin.globalshop.common.utils.StringTrimEditor;

/**
 * @description：基础 controller
 * @author：zhixuan.wang @date：2015/10/1 14:51
 */
public abstract class BaseController {

    // 控制器本来就是单例，这样似乎更加合理
    protected Logger logger = LogManager.getLogger(getClass());

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        // binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
        // true));
        binder.registerCustomEditor(Date.class, "bookingDate",
                                    new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(Date.class, "startDate",
                                    new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(Date.class, "endDate",
                                    new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(Date.class, "orderTime",
                                    new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

        /**
         * 防止XSS攻击
         */
        // binder.registerCustomEditor(String.class, new StringEscapeEditor());

        // 去除参数头尾空格
        binder.registerCustomEditor(String.class, new StringTrimEditor());
    }

    /**
     * ajax失败
     * 
     * @param msg 失败的消息
     * @return {Object}
     */
    public Object renderError(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     * 
     * @return {Object}
     */
    public Object renderSuccess() {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    /**
     * ajax成功
     * 
     * @param msg 消息
     * @return {Object}
     */
    public Object renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     * 
     * @param obj 成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj) {
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(obj);
        return result;
    }
}
