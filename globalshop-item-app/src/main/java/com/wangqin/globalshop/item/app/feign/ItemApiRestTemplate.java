package com.wangqin.globalshop.item.app.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Create by 777 on 2018/8/24
 */

@Service
public class ItemApiRestTemplate {


	public static final String item_pai_uri = "http://itemApi/";


	@Autowired RestTemplate restTemplate;  //第二种方案

	public BaseResponseDto postRestTemplate(String path){
		MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("name", "迪奥");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, headers);
		ResponseEntity<BaseResponseDto> data =  restTemplate.postForEntity(item_pai_uri+path,httpEntity,BaseResponseDto.class);
		return data.getBody();
	}
}
