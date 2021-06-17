package com.fpshop.commenu;

import java.util.List;

import com.fpshop.model.HeaderMenuList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {

    List<HeaderMenuList> selmenulist();

}