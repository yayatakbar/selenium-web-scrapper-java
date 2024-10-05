package org.com.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.com.app.Model.Response.ResponseData;

import org.com.app.Service.SeleniumService;

import java.util.Map;

@RestController
public class WebScrapperController {

    private final SeleniumService seleniumService;

    @Autowired
    public WebScrapperController(SeleniumService seleniumService) {
        this.seleniumService = seleniumService;
    }


    @PostMapping("/artikel")
    public ResponseEntity<?> artikel(@RequestBody Map<String, String> payload) {
        ResponseData responseData = new ResponseData();
        try {
            String artikel = payload.get("artikel");
            responseData = seleniumService.getListArtikel(artikel);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(responseData.getResult()).body(responseData);
    }

}
