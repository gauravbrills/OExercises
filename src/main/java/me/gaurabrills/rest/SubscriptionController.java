package me.gaurabrills.rest;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.gaurabrills.order.OrderDetailsProcessor;

@Controller
public class SubscriptionController {
	private static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);
	private @Autowired OrderDetailsProcessor processor;

	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public @ResponseBody String subscribe(HttpServletRequest request) {
		String orderDetails = processor.getOrderDetails(request);
		String truncatedOrderDetails = processor.truncate(orderDetails, 250);
		log.info(truncatedOrderDetails);
		return truncatedOrderDetails;
	}
}
