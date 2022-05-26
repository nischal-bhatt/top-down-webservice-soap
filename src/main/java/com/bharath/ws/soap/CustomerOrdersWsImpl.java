package com.bharath.ws.soap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.feature.Features;

import com.bharath.ws.trainings.CreateOrdersRequest;
import com.bharath.ws.trainings.CreateOrdersResponse;
import com.bharath.ws.trainings.CustomerOrdersPortType;
import com.bharath.ws.trainings.GetOrdersRequest;
import com.bharath.ws.trainings.GetOrdersResponse;
import com.bharath.ws.trainings.Order;
import com.bharath.ws.trainings.Product;

@Features(features="org.apache.cxf.feature.LoggingFeature")
public class CustomerOrdersWsImpl implements CustomerOrdersPortType{

	Map<BigInteger, List<Order>> customerOrders = new HashMap<>();	
	int currentId;
	
	public CustomerOrdersWsImpl()
	{
		this.init();
	}
	
	
	public void init()
	{
		
		List<Order> orders = new ArrayList();
		Order o = new Order();
		o.setId(BigInteger.valueOf(1));
		
		Product p = new Product();
		p.setId("1");
		p.setDescription("iphone");
		p.setQuantity(BigInteger.valueOf(3));
		o.getProduct().add(p);
		
		
		orders.add(o);
		
		customerOrders.put(BigInteger.valueOf(++currentId), orders);
		
		
		
	}
	
	@Override
	public GetOrdersResponse getOrders(GetOrdersRequest request) {
		BigInteger customerId = request.getCustomerId();
		List<Order> orders =customerOrders.get(customerId);
		GetOrdersResponse response=new GetOrdersResponse();
		response.getOrder().addAll(orders);
		return response;
	}

	@Override
	public CreateOrdersResponse createOrders(CreateOrdersRequest request) {
		
		BigInteger customerId = request.getCustomerId();
		Order order = request.getOrder();
		
		List<Order> orders = customerOrders.get(customerId);
		orders.add(order);
		
		CreateOrdersResponse response = new CreateOrdersResponse();
	
		response.setResult(true);
		
		return response;
	}

}
