//package com.capg;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.capg.dto.SalonServicedto;
//import com.capg.entity.SalonService;
//import com.capg.exception.SalonServiceNotFoundException;
//import com.capg.exception.ServiceAlreadyExistsException;
//import com.capg.repository.ISalonRepository;
//import com.capg.service.ISalonService;
//import com.capg.service.SalonServiceImp;
//
//@SpringBootTest
//class SalonServiceApplicationTests {
//	@Mock
//	ISalonRepository salonRepository;
//	@Autowired
//	SalonServiceImp salonserviceimp;
//	
//	@InjectMocks
//	ISalonService salon = new SalonServiceImp();
//	SalonService s1,s2,s3;
//	
//	public static SalonService demo(){
//		SalonService s = new SalonService();
//		s.setServiceId(1);
//		s.setServiceName("Haircut");
//		s.setServicePrice("200");
//		s.setServiceDuration("60");
//		s.setDiscount(10);
//		return s;
//	}
//	@BeforeEach
//    public void init() {
//        s1 = new SalonService(1, " Haircut","200","60",10);
//        s2 = new SalonService(2, " Hairspa","200","60",10);
//        s3 = new SalonService(3, " Haircut","200","60",10);
//        
//	}
//        
//
//	@Test
//	void validSalonServiceAddition() throws ServiceAlreadyExistsException{
//		SalonService salonService = SalonServiceApplicationTests.demo();
//		Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.empty());
//		Assertions.assertEquals(salon.addService(salonService), salonService);
//	}
//	@Test
//	void invalidServiceAddition() throws ServiceAlreadyExistsException{
//		SalonService salonService = SalonServiceApplicationTests.demo();
//		Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.of(salonService));
//		ServiceAlreadyExistsException ex = Assertions.assertThrows(ServiceAlreadyExistsException.class, () -> salon.addService(salonService));
//		Assertions.assertEquals(ex.getMessage(), "Service.SERVICE_ALREADY_EXISTS");
//	}
//	@Test
//	void validRemoveService() throws ServiceAlreadyExistsException{
//		SalonService salonService = SalonServiceApplicationTests.demo();
//		Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.of(salonService));
//		Assertions.assertDoesNotThrow(() -> salon.removeService(salonService.getServiceId()));
//	}
//	
//	@Test
//	void invalidRemoveService() throws SalonServiceNotFoundException{
//		SalonService salonService = SalonServiceApplicationTests.demo();
//		Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.empty());
//		SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class, ()-> salon.removeService(salonService.getServiceId()));
//		Assertions.assertEquals(ex.getMessage(), "Service.SalonService_NOT_FOUND");
//	}
//	 @Test
//	    public void validServiceUpdate() throws SalonServiceNotFoundException {
//		 SalonService salonService = SalonServiceApplicationTests.demo();
//		 Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.of(salonService));
//		 assertEquals(salonService.getDiscount(),salon.updateService(salonService.getServiceId(),salonService).getDiscount());
//		 /*when(salonRepository.existsById(s1.getServiceId())).thenReturn(true);
//		 when(salonRepository.findById(s1.getServiceId())).thenReturn(Optional.ofNullable(s1));
//		 when(salonRepository.save(s1)).thenReturn(s1);
//		 assertEquals(s1.getDiscount(),salonserviceimp.updateService(s1.getServiceId(),s1).getDiscount());
//		 */
//	 }
//	 @Test
//		void invalidServiceUpdate() throws SalonServiceNotFoundException{
//		 SalonService salonService = SalonServiceApplicationTests.demo();
//			Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.empty());
//			SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class, () -> salon.updateService((salonService.getServiceId()),salonService));
//			Assertions.assertEquals(ex.getMessage(), "Service.SalonService_NOT_FOUND");
//		}
//		
//	 @Test
//		void validGetService() throws SalonServiceNotFoundException{
//		 SalonService salonService = SalonServiceApplicationTests.demo();
//			Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.of(salonService));
//			Assertions.assertEquals(salon.getService(salonService.getServiceId()), salonService);
//		}
//	 @Test
//		void invalidGetService() throws SalonServiceNotFoundException{
//		 SalonService salonService = SalonServiceApplicationTests.demo();
//			Mockito.when(salonRepository.findById(salonService.getServiceId())).thenReturn(Optional.empty());
//			SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class, ()-> salon.getService(salonService.getServiceId()));
//			Assertions.assertEquals(ex.getMessage(), "Service.SalonService_NOT_FOUND");
//		}
//	 @Test
//		void validGetAllService() throws SalonServiceNotFoundException{
//		 SalonService salonService = SalonServiceApplicationTests.demo();
//			List<SalonService> list = new ArrayList<>();
//			list.add(salonService);
//			Mockito.when(salonRepository.findAll()).thenReturn(list);
//			Assertions.assertEquals(salon.getAllServices(), list);
//		}
//	 @Test
//		void validgetServiceByName() throws SalonServiceNotFoundException{
//		 SalonService salonService = SalonServiceApplicationTests.demo();
//		 List<SalonService> list = new ArrayList<>();
//			list.add(salonService);
//			Mockito.when(salonRepository.findByServiceName(salonService.getServiceName())).thenReturn(list);
//			/*list.forEach(p->{
//				list.add(salonService);
//			});*/
//			Assertions.assertEquals(salon.getServiceByName(salonService.getServiceName()), list);
//		}
//	 @Test
//		void invalidgetServiceByName() throws SalonServiceNotFoundException{
//		 SalonService salonService = SalonServiceApplicationTests.demo();
//			Mockito.when(salonRepository.findByServiceName(salonService.getServiceName())).thenReturn(new ArrayList<>());
//			SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class, ()-> salon.getServiceByName(salonService.getServiceName()));
//			Assertions.assertEquals(ex.getMessage(), "Service.SalonService_NOT_FOUND");
//		}
//	 
//	 @Test
//		void validgetServiceByPrice() throws SalonServiceNotFoundException{
//		 SalonService salonService = SalonServiceApplicationTests.demo();
//		 List<SalonService> list = new ArrayList<>();
//			list.add(salonService);
//			Mockito.when(salonRepository.findByServicePrice(salonService.getServicePrice())).thenReturn(list);
//			Assertions.assertEquals(salon.getServiceByPrice(salonService.getServicePrice()), list);
//		}
//	 @Test
//		void invalidgetServiceByPrice() throws SalonServiceNotFoundException{
//		 SalonService salonService = SalonServiceApplicationTests.demo();
//			Mockito.when(salonRepository.findByServicePrice(salonService.getServicePrice())).thenReturn(new ArrayList<>());
//			SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class, ()-> salon.getServiceByPrice(salonService.getServicePrice()));
//			Assertions.assertEquals(ex.getMessage(), "Service.SalonService_NOT_FOUND");
//		}
//	 @Test
//		void validgetServiceByDuration() throws SalonServiceNotFoundException{
//		 SalonService salonService = SalonServiceApplicationTests.demo();
//		 List<SalonService> list = new ArrayList<>();
//			list.add(salonService);
//			Mockito.when(salonRepository.findByServiceDuration(salonService.getServiceDuration())).thenReturn(list);
//			Assertions.assertEquals(salon.getServicesByDuration(salonService.getServiceDuration()), list);
//		}
//	 @Test
//		void invalidgetServiceByDuration() throws SalonServiceNotFoundException{
//		 SalonService salonService = SalonServiceApplicationTests.demo();
//			Mockito.when(salonRepository.findByServiceDuration(salonService.getServiceDuration())).thenReturn(new ArrayList<>());
//			SalonServiceNotFoundException ex = Assertions.assertThrows(SalonServiceNotFoundException.class, ()-> salon.getServicesByDuration(salonService.getServiceDuration()));
//			Assertions.assertEquals(ex.getMessage(), "Service.SalonService_NOT_FOUND");
//		}
//	 
//	 
//}