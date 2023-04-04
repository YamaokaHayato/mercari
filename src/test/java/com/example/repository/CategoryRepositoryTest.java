package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.domain.Category;

@SpringBootTest
class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private JdbcTemplate jdbc;

	@BeforeEach
	void setUpDatabase() {
		
//		jdbc.execute("INSERT INTO category VALUES (1, null, 'Men', null)");
//		jdbc.execute("INSERT INTO category VALUES (2, 1, 'Clothes', null)");
//		jdbc.execute("INSERT INTO category VALUES (3, 2, 'shoes', 'Men/Clothes/shoes')");
	}
	
	@DisplayName("大カテゴリーを検索するテスト")
	@Test
	void findByLargeCategory() {
		System.out.println("大カテゴリーを検索するテスト開始");
		
		List<Category> largeCategoryList = categoryRepository.findByLargeCategory();
		// assertAllを使用することで1つ目のテストで失敗しても全てのテストを実施してくれる
		assertAll(
			() -> assertEquals("Men", largeCategoryList.get(0).getName(), "大カテゴリーのBeautyが見つかりません"),
			() -> assertEquals(null, largeCategoryList.get(0).getParent(), "大カテゴリーのparentIdがnullではありません"),
			() -> assertEquals(null,largeCategoryList.get(0).getNameAll(), "大カテゴリーのname_allがnullではありません"),
			() -> assertEquals(1, largeCategoryList.size(), "リストに入っている大カテゴリーが1つではありません")
		);
		System.out.println("大カテゴリーを検索するテスト終了");
	}
	
	@DisplayName("中カテゴリーを検索するテスト")
	@Test
	void findByMidumCatgory() {
		System.out.println("中カテゴリーを検索するテスト開始");
		
		List<Category> mediumcategoryList = categoryRepository.findByMidumCatgory(1);
		assertAll(
				() -> assertEquals("Clothes", mediumcategoryList.get(0).getName(), "Clothesが見つかりません"),
				() -> assertEquals(1, mediumcategoryList.get(0).getParent(), "parentIdが見つかりません"),
				() -> assertEquals(null,mediumcategoryList.get(0).getNameAll(), "name_allがnullではありません")
			);
		System.out.println("中カテゴリーを検索するテスト終了");
	}
	
	@DisplayName("小カテゴリーを検索するテスト")
	@Test
	void findBySmallCategory() {
		System.out.println("小カテゴリーを検索するテスト開始");
		
		List<Category> smallCategoryList = categoryRepository.findBySmallCategory(2);
		
		assertAll(
				//ラムダ式
				() -> assertEquals("shoes", smallCategoryList.get(0).getName(), "shoesが見つかりません"),
				() -> assertEquals(2, smallCategoryList.get(0).getParent(), "parentIdが見つかりません"),
				() -> assertEquals("Men/Clothes/shoes", smallCategoryList.get(0).getNameAll(), "'Men/Clothes/shoesが見つかりません")
			);
		System.out.println("小カテゴリーを検索するテスト終了");
	}

	@AfterEach
	void setUpAfterTransaction() {
//		jdbc.execute("DELETE FROM category");
	}

}
