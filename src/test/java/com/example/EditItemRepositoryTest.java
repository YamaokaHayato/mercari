package com.example;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.domain.EditItem;
import com.example.repository.EditItemRepository;

@SpringBootTest
class EditItemRepositoryTest {
	
	@Autowired
	private EditItemRepository editItemRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		EditItem item = new EditItem();
		item.setName("テスト");
		item.setPrice(1.0);
		item.setCategory(1);
		item.setBrand("テスト");
		item.setCondition(1);
		item.setDescription("テストテスト");
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void 選択された商品をIDで検索するテスト() {
		System.out.println("選択された商品をIDで検索するテスト開始");
		
		Integer minId = template.queryForObject("select min(id) from items;", new MapSqlParameterSource(), Integer.class);
		EditItem resultItem = editItemRepository.load(minId);
		assertAll(
			() -> assertEquals("White Rabbit Eyeshadow (Colourpop)", resultItem.getName(), "商品名が見つかりません"),
			() -> assertEquals(10.0, resultItem.getPrice(), "金額が設定されていません"),
			() -> assertEquals(3, resultItem.getCategory(), "カテゴリーが見つかりません"),
			() -> assertEquals("aa", resultItem.getBrand(), "ブランド名が見つかりません"),
			() -> assertEquals(1, resultItem.getCondition(), "コンディションが見つかりません")
		);
		System.out.println("選択された商品をIDで検索するテスト終了");
	}
	
	

	@Test
	void test() {
		
	}

}
