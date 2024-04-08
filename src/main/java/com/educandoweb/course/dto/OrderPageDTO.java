package com.educandoweb.course.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderPageDTO {

    /**
     * 用户id
     */
    @NotNull
    private Long userId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 页码
     */
    @Max(value = 100,message = "超过最大页码")
    @NotNull
    private Integer page;
    /**
     * 每页条数
     */
    @Max(value = 100,message = "超过最大页面展示条数")
    @NotNull
    private Integer size;

}
