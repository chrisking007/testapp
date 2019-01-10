package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CallMeService {

    @Autowired
    CallMeAccessor callMe;

    public String callGetWithFeign() {
        return callMe.callMeGet().getBody();
    }

    /*public String callPostWithFeign(FormInfo fi) {
    	String result = null;
    	ResponseEntity<String> results= callMe.callMePOST(fi);
    	return result;
        //return callMe.callMePOST().getBody();

    }*/
    
    public String callPostWithFeign() {
    	String result = null;
    	ResponseEntity<String> results= callMe.callMePOST();
    	return results.getBody();
        //return callMe.callMePOST().getBody();

    }

    public String callPostWihtoutBodyWithFeign(String content) {
        return callMe.callMePOSTWithOutBody(content).getBody();
    }

}
