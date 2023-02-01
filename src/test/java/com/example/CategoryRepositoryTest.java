package com.example;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.domain.Category;
import com.example.repository.CategoryRepository;

@SpringBootTest
class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
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
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void 大カテゴリーを検索するテスト() {
		System.out.println("大カテゴリーを検索するテスト開始");
		
		List<Category> largeCategoryList = categoryRepository.findByLargeCategory();
		// assertAllを使用することで1つ目で失敗しても全てのテストを実施してくれる
		assertAll(
			//ラムダ式
			() -> assertEquals("Beauty", largeCategoryList.get(0).getName(), "大カテゴリーのBeautyが見つかりません"),
			() -> assertEquals(null, largeCategoryList.get(0).getParent(), "大カテゴリーのparentIdがnullではありません"),
			() -> assertEquals(null,largeCategoryList.get(0).getNameAll(), "大カテゴリーのname_allがnullではありません"),
			() -> assertEquals("Kids", largeCategoryList.get(1).getName(), "大カテゴリーのKidsが見つかりません"),
			() -> assertEquals(null, largeCategoryList.get(1).getParent(), "大カテゴリーのparentIdがnullではありません"),
			() -> assertEquals(null, largeCategoryList.get(1).getNameAll(), "大カテゴリーのname_allがnullではありません"),
			() -> assertEquals("Other", largeCategoryList.get(9).getName(), "大カテゴリーのOtherが見つかりません"),
			() -> assertEquals(null, largeCategoryList.get(9).getParent(), "大カテゴリーのparentIdが見つかりません"),
			() -> assertEquals(null, largeCategoryList.get(9).getNameAll(), "大カテゴリーのname_allが見つかりません")
		);
		System.out.println("大カテゴリーを検索するテスト終了");
	}

//	@Test
//	void 中カテゴリーを検索するテスト() {
//		System.out.println("中カテゴリーを検索するテスト開始");
//		
//		List<Category> mediumcategoryList = categoryRepository.findByMidumCatgory();
//		
//		assertEquals("Makeup", mediumcategoryList.get(0).getName(), "中カテゴリー名が見つかりません");
//		
//		
//		System.out.println("中カテゴリーを検索するテスト終了");
//	}

}
