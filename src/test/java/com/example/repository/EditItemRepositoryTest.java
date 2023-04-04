package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.example.domain.EditItem;
import com.example.domain.Item;

@SpringBootTest
class EditItemRepositoryTest {

	@Autowired
	EditItemRepository editItemRepository;

	@Autowired
	private JdbcTemplate jdbc;

	EditItem item = new EditItem();

	@BeforeEach
	void setUpDatabase() {
//		jdbc.execute("INSERT INTO items VALUES (1, 'sneaker', 1, 3, 'NIKE', 10.0, 0, 'very rare sneakers', 1)");
//		jdbc.execute("INSERT INTO category VALUES (2, null, 'Men', null)");
//		jdbc.execute("INSERT INTO category VALUES (3, 2, 'Clothes', null)");
//		jdbc.execute("INSERT INTO category VALUES (4, 3, 'shoes', 'Men/Clothes/shoes')");
	}

	@DisplayName("idと紐づいた商品を1件検索するテスト")
	@Test
	void load() {
		System.out.println("選択された商品をIDで検索するテスト開始");

		item = editItemRepository.load(1);

		assertAll(() -> assertEquals("sneaker", item.getName(), "nameが見つかりません"),
				() -> assertEquals(10.0, item.getPrice(), "priceが設定されていません"),
				() -> assertEquals(3, item.getCategory(), "categoryが見つかりません"),
				() -> assertEquals("NIKE", item.getBrand(), "brandが見つかりません"),
				() -> assertEquals(1, item.getCondition(), "conditionが見つかりません"));
		
		System.out.println("選択された商品をIDで検索するテスト終了");
	}

	@DisplayName("商品情報を更新するテスト")
	@Test
	void update() {
		
		System.out.println("商品情報を更新するテスト開始");

		item.setId(1);
		item.setName("sandals");
		item.setCondition(2);
		item.setCategory(3);
		item.setBrand("NewBalance");
		item.setPrice(8.5);
		item.setShipping(0);
		item.setDescription("very good sandals");
		item.setVersion(1);

		editItemRepository.update(item);

		assertAll(() -> assertEquals("sandals", item.getName(), "nameが更新されていません"),
				() -> assertEquals("NewBalance", item.getBrand(), "brandが更新されていません"),
				() -> assertEquals("very good sandals", item.getDescription(), "descriptionが更新されていません"),
				() -> assertEquals(1, item.getVersion(), "versionが更新されていません")
				);
		
		System.out.println("選商品情報を更新するテスト終了");

	}

	@AfterEach
	void setUpAfterTransaction() {
//		jdbc.execute("DELETE FROM items");
//		jdbc.execute("DELETE FROM category");
	}

}
