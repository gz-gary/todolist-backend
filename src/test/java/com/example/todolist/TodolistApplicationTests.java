package com.example.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodolistApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void testController() throws Exception {
		mockMvc.perform(get("/api/items"))
			.andExpect(status().isOk())
			.andExpect(content().json("[]"));

		mockMvc.perform(
			post("/api/items")
			.contentType(MediaType.APPLICATION_JSON)
			.content(String.format("{\"title\": \"%s\"}", "A new item"))
			)
			.andExpect(status().isOk())
			.andDo(result -> System.out.println(result.getResponse().getContentAsString()));

		mockMvc.perform(
			post("/api/items")
			.contentType(MediaType.APPLICATION_JSON)
			.content(String.format("{\"title\": \"%s\"}", "Another item"))
			)
			.andExpect(status().isOk())
			.andDo(result -> System.out.println(result.getResponse().getContentAsString()));

		mockMvc.perform(get("/api/items"))
			.andExpect(status().isOk())
			.andDo(result -> System.out.println(result.getResponse().getContentAsString()));

		mockMvc.perform(get("/api/items/0"))
			.andExpect(status().isOk())
			.andDo(result -> System.out.println(result.getResponse().getContentAsString()));

		mockMvc.perform(get("/api/items/1"))
			.andExpect(status().isOk())
			.andDo(result -> System.out.println(result.getResponse().getContentAsString()));

		mockMvc.perform(
			patch("/api/items/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(String.format("{\"finished\": true}"))
			)
			.andExpect(status().isOk())
			.andDo(result -> System.out.println(result.getResponse().getContentAsString()));

		mockMvc.perform(get("/api/items"))
			.andExpect(status().isOk())
			.andDo(result -> System.out.println(result.getResponse().getContentAsString()));
	}
}
