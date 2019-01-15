package com.example.test;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import feign.Body;
import feign.Headers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/test")
@Api(tags = "Test Controller")
@CrossOrigin(origins="*")
@EnableFeignClients
public class TestController {
    
    @Autowired
    CallMeService callMe;

	@GetMapping(value = "/get")
	@ApiOperation("Get Request")
	public ResponseEntity<String> sayHelloGet() {
		String str = "Hello Get !!";		
		ResponseEntity<String> res = new ResponseEntity<String>(str, HttpStatus.OK);
		return res;
	}
	
	@PostMapping(value = "/post")
	@ApiOperation("Post Request")
	public ResponseEntity<String> sayHelloPost() {	
		String str = "Hello Post !!";		
		ResponseEntity<String> res = new ResponseEntity<String>(str, HttpStatus.OK);
		return res;
	}
	
	@GetMapping(value = "/get/feign")
    @ApiOperation("Get Request")
    public ResponseEntity<String> callGetWithFeign() {
        String str = callMe.callGetWithFeign();        
        ResponseEntity<String> res = new ResponseEntity<String>(str, HttpStatus.OK);
        return res;
    }
    
    /*@PostMapping(value = "/post/feign")
    @ApiOperation("Post Request")
    public ResponseEntity<String> callPostWithFeign(@RequestBody FormInfo fi) {  
        String str = callMe.callPostWithFeign(fi);       
        ResponseEntity<String> res = new ResponseEntity<String>(str, HttpStatus.OK);
        return res;
    }*/
    
	@CrossOrigin(origins="*")
	@Consumes(MediaType.TEXT_PLAIN_VALUE)
	@Body(" sample text ")
    //@GetMapping(value = "/post/feign")
	@RequestMapping(value = "/post/feign", method = {RequestMethod.POST,RequestMethod.GET},headers={"Content-Length=0"})
	@Headers("Content-Length: 0")	
    @ApiOperation("Post Request")
    public ResponseEntity<String> callPostWithFeign() {  
        String str = callMe.callPostWithFeign();       
       /* HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
       // ResponseEntity<String> res = new ResponseEntity<String>(str, HttpStatus.OK);
        MultiValueMap<String,String> headers1 = new LinkedMultiValueMap<String,String>();
        headers1.add("Content-Length", "0");
        headers1.add("Access-Control-Allow-Origin", "*");
        headers1.add("Access-Control-Request-Method", "POST");*/
       // headers1.add("Content-Length", "0");
       // ResponseEntity<String> res = new ResponseEntity<String>(str,headers1, HttpStatus.OK);
		/*Feign.builder()
        .client(new OkHttpClient())
        .target(String.class, "https://api.github.com");*/
        ResponseEntity<String> res = new ResponseEntity<String>(str, HttpStatus.OK);
        return res;
    }
	
	@PostMapping(value = "/post/rest")
    @ApiOperation("Post Request")
    public ResponseEntity<String> callPostWithRest() {  
        //String str = callMe.callPostWithFeign();       
       /* HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
       // ResponseEntity<String> res = new ResponseEntity<String>(str, HttpStatus.OK);
        MultiValueMap<String,String> headers1 = new LinkedMultiValueMap<String,String>();
        headers1.add("Content-Length", "0");
        headers1.add("Access-Control-Allow-Origin", "*");
        headers1.add("Access-Control-Request-Method", "POST");*/
       // headers1.add("Content-Length", "0");
       // ResponseEntity<String> res = new ResponseEntity<String>(str,headers1, HttpStatus.OK);
		RestTemplate restTemplate = new RestTemplate();
		//HttpEntity<Foo> request = new HttpEntity<>(new Foo("bar"));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("id", "1");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		
        ResponseEntity<String> res = restTemplate.postForEntity("https://callmeapp.azurewebsites.net/call/post", request , String.class);
        return res;
    }
    
    @PostMapping(value = "/postwithoutbody/feign/{content}")
    @ApiOperation("Post Request")
    public ResponseEntity<String> callPostWihtoutBodyWithFeign(@PathVariable String content) {  
        String str = callMe.callPostWihtoutBodyWithFeign(content);       
        ResponseEntity<String> res = new ResponseEntity<String>(str, HttpStatus.OK);
        return res;
    }
}
