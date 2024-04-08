package com.educandoweb.course.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.educandoweb.course.entities.enums.SkuStatus;
import lombok.Data;

import javax.persistence.*;


@Data
@TableName("tb_sku")
public class Sku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 售价
     */
    private Double price;
    /**
     * 库存
     */
    private Integer stock;

    /**
     * sku状态 {@link SkuStatus}
     */
    private Integer skuStatus;



    private String name;


    public String  getRedisKey(){
        return "sku:" + id;
    }
}
