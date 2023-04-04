package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Item;
import com.example.repository.ItemsRepository;
import com.example.service.ShowItemDetailService;

@SpringBootTest
class ShowItemDetailServiceTest {
	
	@Mock
	ItemsRepository itemsRepository;
	
	@InjectMocks
	ShowItemDetailService showItemDetailService;
	
	Item item;

	@BeforeEach
	void setUp() throws Exception {
		
		item = new Item();
	}
	
	@DisplayName("商品詳細のテスト")
	@Test
	void test() {
		
		item.setId(1);
		item.setName("White Rabbit Eyeshadow (Colourpop)");
		item.setCondition(1);
		item.setCategory("Beauty/Fragrance/Women");
		item.setBrand("abc");
		item.setPrice((double) 10.0);
		item.setShipping(1);
		item.setDescription("100% Guaranteed Authentic Will provide copy of receipt if asked Brand new /Never opened "
				+ "Nor swatched! For maximum coverage use the fingertip to tap shadow on the eyelid. A flat, fine tip "
				+ "synthetic brush is perfect for detailed application close to the lash line. Using a fluffy brush that "
				+ "has good movement, dip the tip of the brush into the shadow and apply with a “windshield wiper”"
				+ " movement throughout the crease of the eye. This will make your shadow appear soft and diffused "
				+ "like a total pro! When finished, close that sucker up tight! Don’t leave the jar opened for long "
				+ "periods of time, you may lose some of the magic that makes the Super Shock Shadow so amazing. 10% s"
				+ "elling fees!");
		
		when(itemsRepository.load(item.getId())).thenReturn(item);
		
		Item detailItem = showItemDetailService.showItemDetail(item.getId());
		
		// IDで取得した商品詳細と比較
		assertEquals(item, detailItem);
		
		
	}

}
