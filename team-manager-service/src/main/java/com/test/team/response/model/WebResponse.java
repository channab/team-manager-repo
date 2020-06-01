package com.test.team.response.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class WebResponse {

	private String title;
	private int code;
	private String message;
	private Map<String, Object> data;
	
	public WebResponse(String title, String message, int code) {
		super();
		this.title = title;
		this.code = code;
		this.message = message;
	}
}
