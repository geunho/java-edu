package com.ebaykorea.gkhim.javaedu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boards")
public class BoardAPIController {
  @GetMapping
  public String boards(){
    return "boards";
  }

  @GetMapping("/{id}")
  public String boards(@PathVariable(name = "id") int id) {
    return "board #" + id;
  }
}
