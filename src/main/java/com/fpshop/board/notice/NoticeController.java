package com.fpshop.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService service;
    
    @GetMapping("/list")
    public String notice() {
        return "/notice/list";
    }

    @GetMapping("/detail")
    public String ndetail() {
        return "/notice/detail";
    }




}
