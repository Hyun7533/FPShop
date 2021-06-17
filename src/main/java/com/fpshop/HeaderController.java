package com.fpshop;

import org.springframework.stereotype.Controller;

@Controller
public class HeaderController {

    public String headerController() {

        return "fragment/header";
    }
}
