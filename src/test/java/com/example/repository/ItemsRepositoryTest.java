package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.domain.Item;

@SpringBootTest
class ItemsRepositoryTest {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	ItemsRepository itemsRepository;
	
	Item item = new Item();
	
	@BeforeEach
	void setUpDatabase() {
		// サンプルデータの挿入
//		jdbc.execute("INSERT INTO items VALUES (1, 'sneaker', 1, 3, 'NIKE', 10.0, 0, 'very rare sneakers', 1)");
//		jdbc.execute("INSERT INTO category VALUES (2, null, 'Men', null)");
//		jdbc.execute("INSERT INTO category VALUES (3, 2, 'Clothes', null)");
//		jdbc.execute("INSERT INTO category VALUES (4, 3, 'shoes', 'Men/Clothes/shoes')");
		
	}

	@DisplayName("1件検索のテスト")
	@Test
	void load() {
		System.out.println("1件検索のテスト開始");
		
		item = itemsRepository.load(1);
		System.out.println(item);
		
		assertEquals("sneaker", item.getName(), "nameが見つかりません");
		assertEquals("NIKE", item.getBrand(), "brandが見つかりません");
		assertEquals(10.0, item.getPrice(), "priceが見つかりません");
				
		System.out.println("1件検索のテスト終了");
	}
	
	@DisplayName("商品名、カテゴリー、ブランドを検索するテスト")
	@Test
	void findByNameAndCategoryAndBrand() {
		System.out.println("商品名、カテゴリー、ブランドを検索するテスト開始");
		
		List<Item> itemList = itemsRepository.findByNameAndCategoryAndBrand("sneaker", 3, "NIKE");
		
		assertEquals("sneaker", itemList.get(0).getName(), "nameが見つかりません");
		assertEquals("NIKE", itemList.get(0).getBrand(), "brandが見つかりません");
		
		System.out.println("商品名、カテゴリー、ブランドを検索するテスト終了");
	}
	
	@AfterEach
	void setUpAfterTransaction() {
		// サンプルデータの削除
//		jdbc.execute("delete from items");
//		jdbc.execute("delete from category");
	}

}
