package com.example.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Item;
import com.example.repository.ItemsRepository;
import com.example.service.AddItemService;

@SpringBootTest
class AddItemServiceTest {
	
	@Mock
	ItemsRepository itemsRepository;
	AutoCloseable closeable;
	
	@InjectMocks
	AddItemService addItemService;
	
	@BeforeEach
	void openMocks() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
	}
	
	@DisplayName("商品追加のテスト")
	@Test
	void addItemTest() {
		
		Item item = new Item();
		
		item.setName("sneaker");
		item.setPrice(10.0);
		item.setBrand("NIKE");
		item.setCondition(1);
		item.setCategory("3");
		item.setShipping(0);
		item.setDescription("very rare item");
		
		addItemService.addItem(item);
		
		verify(itemsRepository, times(1)).insert(item);
	}
	
	@AfterEach
	public void releaseMocks() throws Exception {
	    closeable.close();
	  }
	
	

}
