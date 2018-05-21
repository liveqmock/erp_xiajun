package com.wangqin.globalshop.biz1.api.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Patrick on 15/12/2017.
 */
@Data
public class AppRequest implements Serializable{
    @NotNull(message = "不能为空")
    private String id;
    @Min(value = 1, message = "最少为1")
    @Max(value = 20, message = "最大为20")
    private Integer age;
}
