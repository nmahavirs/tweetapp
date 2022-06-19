package com.tweetapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponse {
	private Object data;
	private String errorMsg;
	private Object errors;
}
