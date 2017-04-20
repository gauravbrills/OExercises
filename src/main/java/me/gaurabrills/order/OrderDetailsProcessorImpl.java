package me.gaurabrills.order;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import me.gaurabrills.rest.SubscriptionController;

@Service
class OrderDetailsProcessorImpl implements OrderDetailsProcessor {
	private static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);
	private static final String REPLACEMENT = " ... (truncated) ... ";

	@Override
	public String getOrderDetails(HttpServletRequest request) {
		String queryString = request.getQueryString();
		log.info("Got query String : ", queryString);
		return queryString;
	}

	@Override
	public String truncate(String orderDetails, int noOfCharsAllwd) {
		Assert.notNull(orderDetails, "Null strings not accepted");

		int orderDtlsLen = orderDetails.length();
		int repLen = REPLACEMENT.length();
		if (!(orderDtlsLen > noOfCharsAllwd && noOfCharsAllwd - repLen > 0)) {
			return orderDetails;
		}
		int buffer = (int) Math.round((noOfCharsAllwd - repLen) / 2);
		String truncated = orderDetails.substring(0, buffer) + REPLACEMENT
				+ orderDetails.substring(orderDtlsLen - buffer);
		return truncated;
	}

}
