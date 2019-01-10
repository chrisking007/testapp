package com.example.test;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.ws.rs.Consumes;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Body;
import feign.Headers;
import feign.RequestLine;

@FeignClient(value = "callme", url = "${callme.url}" )
@CrossOrigin(origins="*")
public interface CallMeAccessor {

    @RequestMapping(value = "/call/get", method = RequestMethod.GET)
    ResponseEntity<String> callMeGet();
    
    /*@RequestMapping(value = "/call/post", method = RequestMethod.POST)
    ResponseEntity<String> callMePOST(@RequestBody FormInfo formtest);*/
    
    /*//@RequestLine("POST /call/post")
    @CrossOrigin(origins="*")
    @Consumes(MediaType.TEXT_PLAIN_VALUE)
	@Body(" sample text ")
    @RequestMapping(value = "/call/post", method = RequestMethod.POST)  
    @Headers("Access-Control-Allow-Origin: *")
    ResponseEntity<String> callMePOST();*/
    
    @RequestMapping(value = "/call/post", method = RequestMethod.POST, headers={"Content-Length=0"})  
    ResponseEntity<String> callMePOST();
    
    @RequestMapping(value = "/call/postwithoutbody/{id}", method = POST)
    ResponseEntity<String> callMePOSTWithOutBody(@PathVariable("id") final String id);

}
