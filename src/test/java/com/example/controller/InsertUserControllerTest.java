package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.example.form.InsertUserForm;
import com.example.service.UserService;

@AutoConfigureMockMvc
@SpringBootTest
class InsertUserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	InsertUserController controller;
	
	@Autowired
	UserService userService;
	
	MvcResult mvcResult;

	@BeforeEach
	void setUp() throws Exception {
		
		//MockMvcBuilders.standaloneSetup(...)でspringMVCの動作を再現するための準備
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@DisplayName("ユーザー登録画面遷移のテスト")
	@Test
	void toInsertTest() throws Exception {
		
		mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/toInsert"))
				.andExpect(status().isOk()).andReturn();

		ModelAndView mav = mvcResult.getModelAndView();
		// 期待されるビュー名と一致することを確認する
		ModelAndViewAssert.assertViewName(mav, "register");
	}
	
	@DisplayName("ユーザー登録のバリデーションテスト")
	@Test
	  public void postFormTestInValid() throws Exception {
	    
		InsertUserForm form = new InsertUserForm();
	    form.setUsername("");
	    form.setPassword("");

	    // flashAttrメソッドを使用して、フォームオブジェクトをフラッシュ属性として追加。
	    // 次に、andExpectメソッドを使用して、モデル属性にバリデーションエラーが含まれていること、
	    // モデル属性に追加されたフォームオブジェクトが同じであること、およびビューの名前が
	    // "InsertUserForm"であることを確認
	    mockMvc.perform((post("/user/insert")).flashAttr("form",form))
	        .andExpect(model().hasErrors())
	        .andExpect(model().attribute("form", form))
	        .andExpect(view().name("register"));
	  }

	
	@DisplayName("ユーザー登録のテスト")
	@Test
	void userInsertTest() throws Exception {

			InsertUserForm form = new InsertUserForm();
		    form.setUsername("山岡");
		    form.setPassword("Test-0000");

		    mockMvc.perform((post("/user/insert"))
		    	.flashAttr("form", form))
		        // modelの検証
		        // modelのいずれかにエラーが存在しないこと
		        .andExpect(model().hasNoErrors())
		        // postFormにエラーが存在しないこと
		        .andExpect(model().attributeHasNoErrors("form"))
		        .andExpect(view().name("redirect:/login"));
	}
	
}
