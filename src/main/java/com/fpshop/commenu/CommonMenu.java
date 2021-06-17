package com.fpshop.commenu;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fpshop.model.Const;

//범위 지정해서 사용 가능특정 애노테이션 특정 클래스 타입 사용 가능

@ControllerAdvice // 범위 (assignbleTypes = {aaa.class,bbb.class})
public class CommonMenu {

    @Autowired
    private CommonMapper mapper;

    // 헤더 메뉴 뿌리기
    @ModelAttribute
    public void menu(Model model) {

        if (Const.menuList == null) {
            System.out.println(" ----- get menus from DB -----");
            Const.menuList = mapper.selmenulist();
        }
        model.addAttribute("menulist", Const.menuList);
    }
}