package com.example.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

@AutoConfigureMockMvc
@SpringBootTest
class ShowItemDetailTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ShowItemDetailController controller;
	
	@BeforeEach
	void setUp() throws Exception {
		
		//MockMvcBuilders.standaloneSetup(...)でspringMVCの動作を再現するための準備
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@DisplayName("商品詳細のテスト")
	@Test
	@WithMockUser(username = "山岡", password = "Test-0000")
	void createShowItemDetailHttpRequest() throws Exception {
		
		//idパラメーターを付与して /showItemDetail パスにGETリクエストを送信し、
		//レスポンスステータスが200OKであることを検証し、
		//レスポンスからModelAndViewを取得し、ビュー名がdetailであることを検証
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/showItemDetail")
				.param("id", "1")) // パラメータを付与
				.andExpect(status().isOk()).andReturn();

		ModelAndView mav = mvcResult.getModelAndView();
		// 期待されるビュー名と一致することを確認する
		ModelAndViewAssert.assertViewName(mav, "detail");
	}

}
