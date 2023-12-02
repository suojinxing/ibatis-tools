package org.workp.core.interaction.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ibatistools")
public class HtmlController {
    @RequestMapping
    public String homePage() {
        return "index";
    }
}
