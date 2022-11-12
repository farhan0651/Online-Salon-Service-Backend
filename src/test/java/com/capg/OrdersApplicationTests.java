package com.capg;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.capg.dto.Ordersdto;
import com.capg.entity.Orders;
import com.capg.exception.OrderAlreadyExistsException;
import com.capg.exception.OrderServiceNotFoundException;
import com.capg.repository.IOrderRepository;
import com.capg.service.IOrderService;
import com.capg.service.OrderServiceImp;


@SpringBootTest
public class OrdersApplicationTests {
	@Mock
	IOrderRepository orderRepository;
	
	@InjectMocks
	IOrderService orders = new OrderServiceImp();
	
	
	public static Orders demo() {
		Orders ord = new Orders();
		ord.setOrderId(1);
		ord.setBillingDate(LocalDate.of(2000, 01, 01));
		ord.setAmount(100.20);
		ord.setPaymentMethod("Upi");
		return ord;
	}
	@Test
	void validOrderAddition() throws OrderServiceNotFoundException, OrderAlreadyExistsException{
		Ordersdto ordersdto = Ordersdto.entityToDTO(OrdersApplicationTests.demo());
		Mockito.when(orderRepository.findById(ordersdto.getOrderId())).thenReturn(Optional.empty());
		Assertions.assertEquals(orders.addOrder(ordersdto), ordersdto);
	}
	
	@Test
	void invalidOrderAddition() throws OrderServiceNotFoundException, OrderAlreadyExistsException{
		Orders order = OrdersApplicationTests.demo();
		Mockito.when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
		OrderAlreadyExistsException ord = Assertions.assertThrows(OrderAlreadyExistsException.class, () -> 
		orders.addOrder(Ordersdto.entityToDTO(order)));
		Assertions.assertEquals(ord.getMessage(), "Service.ORDER_ALREADY_EXISTS");
	}
	@Test
	void validOrderUpdate() throws OrderServiceNotFoundException, OrderAlreadyExistsException {
		Orders order = OrdersApplicationTests.demo();
		Mockito.when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
		Assertions.assertEquals(orders.updateOrder(order.getOrderId(),Ordersdto.entityToDTO(order)), Ordersdto.entityToDTO(order));
	}
	@Test
	void invalidOrderUpdate() throws OrderServiceNotFoundException, OrderAlreadyExistsException{
		Ordersdto ordersdto = Ordersdto.entityToDTO(OrdersApplicationTests.demo());
		Mockito.when(orderRepository.findById(ordersdto.getOrderId())).thenReturn(Optional.empty());
		OrderServiceNotFoundException ord = Assertions.assertThrows(OrderServiceNotFoundException.class, () 
				-> orders.updateOrder(ordersdto.getOrderId(),ordersdto));
		Assertions.assertEquals(ord.getMessage(), "Service.Order_NOT_FOUND");
	}
	@Test
	void validDeleteOrder() throws OrderServiceNotFoundException, OrderAlreadyExistsException{
		Orders order = OrdersApplicationTests.demo();
		Mockito.when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
		Assertions.assertDoesNotThrow(() -> orders.deleteOrder(order.getOrderId()));
	}
	
	@Test
	void invalidDeleteOrder() throws OrderServiceNotFoundException{
		Ordersdto ordersdto = Ordersdto.entityToDTO(OrdersApplicationTests.demo());
		Mockito.when(orderRepository.findById(ordersdto.getOrderId())).thenReturn(Optional.empty());
		OrderServiceNotFoundException ord = Assertions.assertThrows(OrderServiceNotFoundException.class, ()-> orders.deleteOrder(ordersdto.getOrderId()));
		Assertions.assertEquals(ord.getMessage(), "Service.Order_NOT_FOUND");
	}
	
	@Test
	void validGetOrderDetails() throws OrderServiceNotFoundException, OrderAlreadyExistsException{
		Orders order = OrdersApplicationTests.demo();
		Mockito.when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
		Assertions.assertEquals(orders.getOrderDetails(order.getOrderId()), Ordersdto.entityToDTO(order));
	}
	
	@Test
	void invalidGetOrderDetails() throws OrderServiceNotFoundException, OrderAlreadyExistsException{
		Ordersdto ordersdto = Ordersdto.entityToDTO(OrdersApplicationTests.demo());
		Mockito.when(orderRepository.findById(ordersdto.getOrderId())).thenReturn(Optional.empty());
		OrderServiceNotFoundException ord = Assertions.assertThrows(OrderServiceNotFoundException.class, () -> orders.getOrderDetails(ordersdto.getOrderId()));
		Assertions.assertEquals(ord.getMessage(), "Service.Order_NOT_FOUND");
	}
	@Test
	void validGetAllOrders() throws OrderServiceNotFoundException, OrderAlreadyExistsException {
		Orders order = OrdersApplicationTests.demo();
		List<Orders> list = new ArrayList<>();
		list.add(order);
		Mockito.when(orderRepository.findAll()).thenReturn(list);
		List<Ordersdto> listdto = new ArrayList<>();
		list.forEach(o -> {
			listdto.add(Ordersdto.entityToDTO(o));
		});
		Assertions.assertEquals(orders.getAllOrders(), listdto);
	}
	
	@Test
	void invalidGetAllOrders() throws OrderServiceNotFoundException, OrderAlreadyExistsException{
		Mockito.when(orderRepository.findAll()).thenReturn(new ArrayList<Orders>());
		OrderServiceNotFoundException ord = Assertions.assertThrows(OrderServiceNotFoundException.class, ()-> orders.getAllOrders());
		Assertions.assertEquals(ord.getMessage(), "Service.Order_NOT_FOUND");
	}
	
	

}