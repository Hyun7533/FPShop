package com.fpshop;

import org.apache.ibatis.annotations.Mapper;

import com.fpshop.model.UserEntity;

@Mapper
public interface HomeMapper {
	int insUser(UserEntity p);
	UserEntity selUser(UserEntity p);
}
