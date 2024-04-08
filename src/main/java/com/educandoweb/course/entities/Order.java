package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import com.baomidou.mybatisplus.annotation.TableName;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;


@Data
@TableName( "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*
	 *操作时间
	 */
	private Date moment;

	/**
	 * 订单状态
	 */
	private Integer orderStatus;

	/**
	 * 订单价格
	 */
	private Double price;

	/**
	 * 关联skuid
	 */
	private Long skuId;

	/**
	 * 下单用户
	 */
	private Long userId;

	public Order() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

}
