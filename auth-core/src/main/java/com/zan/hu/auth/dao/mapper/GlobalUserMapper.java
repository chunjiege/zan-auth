package com.zan.hu.auth.dao.mapper;

import com.zan.hu.auth.domain.GlobalUser;
import tk.mybatis.mapper.common.Mapper;

public interface GlobalUserMapper extends Mapper<GlobalUser> {

    GlobalUser selectByAccount(String account);
}