package com.example.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.EditItem;
import com.example.repository.EditItemRepository;
import com.example.service.ItemEditService;

@SpringBootTest
class ItemEditServiceTest {
	
	@Mock
	EditItemRepository editItemRepository;
	AutoCloseable closeable;
	
	@InjectMocks
	ItemEditService itemeditService;

	@BeforeEach
	void openMocks() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	public void releaseMocks() throws Exception {
	    closeable.close();
	  }

	@Test
	void test() {
		
		EditItem item = new EditItem();
		
		item.setName("sneaker");
		item.setPrice(10.0);
		item.setBrand("NIKE");
		item.setCondition(1);
		item.setCategory(3);
		item.setShipping(0);
		item.setDescription("very rare item");
		
		itemeditService.update(item);
		
		verify(editItemRepository, times(1)).update(item);
	}

}
