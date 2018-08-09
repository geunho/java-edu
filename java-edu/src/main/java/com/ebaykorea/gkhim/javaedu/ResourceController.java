package com.ebaykorea.gkhim.javaedu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/resources")
@Controller
public class ResourceController {
  @GetMapping("/hello")
  public String helloResource() {
    return "/static/hello.json";
  }
}
