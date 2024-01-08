package org.workp.core.interaction.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * mybatisTools的页面展示
 * http://localhost:9010/ibatistools
 */
@Controller
@RequestMapping("/ibatistools")
public class HtmlController {
    @RequestMapping
    public String homePage() {
        return "index";
    }
}
