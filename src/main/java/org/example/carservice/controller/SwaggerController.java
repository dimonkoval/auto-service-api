package org.example.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
    @RequestMapping("/swagger-ui.html")
    public String swaggerUi() {
        return "forward:/swagger-ui/index.html";
    }
}

