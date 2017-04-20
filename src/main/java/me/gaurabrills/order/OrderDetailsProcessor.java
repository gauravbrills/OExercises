package me.gaurabrills.order;

import javax.servlet.http.HttpServletRequest;


public interface OrderDetailsProcessor {

	String getOrderDetails(HttpServletRequest request);

	String truncate(String orderDetails, int i);

}
