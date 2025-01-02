package com.example.sampleapi;

import static org.assertj.core.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.sampleapi.controller.UserController;
import com.example.sampleapi.entity.Users;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@SpringBootTest
@Transactional   //テスト完了後はROLLBACKする
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class
    })
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(1)
class SampleApiApplicationTests {
	
    @Autowired
    private UserController userController;

    private MockMvc mockMvc;	

    private static final Logger logger = LoggerFactory.getLogger(SampleApiApplicationTests.class);

    
	@Test
	@DatabaseSetup("classpath:dbunit/users.xml")
    @Order(1)	
	void getAllUsers() throws Exception{

	       logger.info("■" + new Object(){}.getClass().getEnclosingMethod().getName());	
		
	       mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	       String responseJson = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
	    		    .contentType("text/plain;charset=UTF-8")
	    		   )
//	    		   .andDo(MockMvcResultHandlers.print())
	               .andExpect(MockMvcResultMatchers.status().isOk())
	     		   .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

	       logger.info("レスポンスJSON：" + responseJson);    

	       //JSONの配列数＝取得行数を調べる
	       ObjectMapper mapper = new ObjectMapper();
	       TypeReference<List<Map<String, String>>> typeRef 
	           = new TypeReference<List<Map<String, String>>>() {};        
	       List<Map<String, String>> list 
	           = mapper.readValue(responseJson, typeRef);	              
	       
	       logger.info("取得行数：" +  list.size() );
	 
	       //取得行数が想定通り、6行か確認する
	       assertThat(list.size()).isEqualTo(6);	       
	}

	@Test
	@DatabaseSetup("classpath:dbunit/users.xml")
    @Order(2)	
	void getUser() throws Exception{

	       logger.info("■" + new Object(){}.getClass().getEnclosingMethod().getName());	

	       mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	       String responseJson = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1000")
	    		    .contentType("text/plain;charset=UTF-8")
	    		   )
//	                .andDo(MockMvcResultHandlers.print())
	    		   .andExpect(MockMvcResultMatchers.status().isOk())
	     		   .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
	       
	       logger.info("レスポンスJSON：" + responseJson);
       
	}

	@Test
	@DatabaseSetup("classpath:dbunit/users.xml")
    @Order(3)	
	void search() throws Exception{

	       logger.info("■" + new Object(){}.getClass().getEnclosingMethod().getName());	

	       mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	       String responseJson = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/search?name=山田&email=")
	    		    .contentType("text/plain;charset=UTF-8")
	                )
//	                .andDo(MockMvcResultHandlers.print())
	                .andExpect(MockMvcResultMatchers.status().isOk())
	     		   .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
       
	       logger.info("レスポンスJSON：" + responseJson);

	       //JSONの配列数＝取得行数を調べる
	       ObjectMapper mapper = new ObjectMapper();
	       TypeReference<List<Map<String, String>>> typeRef 
	           = new TypeReference<List<Map<String, String>>>() {};        
	       List<Map<String, String>> list 
	           = mapper.readValue(responseJson, typeRef);	              
	       
	       logger.info("取得行数：" +  list.size() );
	 
	       //取得行数が想定通り、2行か確認する
	       assertThat(list.size()).isEqualTo(2);	       
	
	}


	@Test
   @Order(4)	
	void insert() throws Exception{

	       logger.info("■" + new Object(){}.getClass().getEnclosingMethod().getName());	

	       mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	       java.sql.Date date = java.sql.Date.valueOf("2025-01-01");
	       
	       Users users = new Users();	
	       users.setId((long) 7000);
	       users.setName("テスト太郎");
	       users.setEmail("foo@bar.com");
	       users.setStartdate(date);
	       users.setLank(0);
	       
	       ObjectMapper mapper = new ObjectMapper();
	       String json = mapper.writeValueAsString(users);	              

	       //■データ挿入
	       MockHttpServletRequestBuilder request =
	                MockMvcRequestBuilders.post("/api/v1/users")
	                		.contentType("text/plain;charset=UTF-8")
	                        .content(json)
	                        .contentType(MediaType.APPLICATION_JSON_VALUE);
	        mockMvc.perform(request)
//	        		.andDo(MockMvcResultHandlers.print())
	                .andExpect(MockMvcResultMatchers.status().isOk());

	        //■挿入したデータが取得できるか確認
		    String responseJson = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/7000")
		    		    .contentType("text/plain;charset=UTF-8")
		    		   )
// 	                   .andDo(MockMvcResultHandlers.print())
		    		   .andExpect(MockMvcResultMatchers.status().isOk())
		     		   .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
		       
		    logger.info("レスポンスJSON：" + responseJson);        	        

		    //■挿入したデータがデータベースから取得できるか確認する
		    // .andExpect(MockMvcResultMatchers.content().string(json) はUTF-8で日本語が取れないので、assertThatで確認する
		    assertThat(responseJson).isEqualTo(json);
		    
	}
	
	@Test
	@DatabaseSetup("classpath:dbunit/users.xml")
    @Order(5)	
	void delete() throws Exception{

	       logger.info("■" + new Object(){}.getClass().getEnclosingMethod().getName());	

	       mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	       mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/2000")
	    		    .contentType("text/plain;charset=UTF-8")
	    		   )
//	                .andDo(MockMvcResultHandlers.print())
	                .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
