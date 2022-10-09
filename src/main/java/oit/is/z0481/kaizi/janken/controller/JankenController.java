package oit.is.z0481.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JankenController {

  @GetMapping("/janken")
  public String janken() {
    return "janken.html";
  }

  @PostMapping("/janken")
  public String janken(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "janken.html";
  }

  @GetMapping("/choice/{param1}")
  public String choice(@PathVariable String param1, ModelMap model) {
    if (param1.equals("グー")) {
      model.addAttribute("gu", param1);
    } else if (param1.equals("チョキ")) {
      model.addAttribute("choki", param1);
    } else {
      model.addAttribute("pa", param1);
    }
    return "janken.html";
  }

}
