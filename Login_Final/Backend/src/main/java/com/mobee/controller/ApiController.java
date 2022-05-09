package com.mobee.controller;

import com.mobee.service.MovieApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@Slf4j
public class ApiController {

    MovieApiService movieApiService;

    private final String KEY = "bf4027c3100b9e4e2dc3221cfb994433";
    private String result = "";

    @ResponseBody
    @GetMapping("/api/getPages")
    public String  getPages() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        

            for (int i = 1; i <= 100; i++) {


            }

        return "good!";
    }

}
