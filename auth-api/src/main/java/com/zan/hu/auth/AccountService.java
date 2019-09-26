package com.zan.hu.auth;


import com.zan.hu.auth.query.AccountInputDTO;

import java.util.List;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-26 09:15
 * @Description todo
 **/
public interface AccountService {

    /**
     * 注册接口
     *
     * @param accountInputDto
     * @throws Exception
     */
    void register(AccountInputDTO accountInputDto) throws Exception;

    /**
     * 更新密码
     *
     * @param account,oldPassword,password
     */
    void update(String account, String oldPassword, String password) throws Exception;

    /**
     * 查找账号
     *
     * @param username
     * @return
     */
    Account selectByUsername(String username);

    /**
     * 批量导入账号
     *
     * @param accountInputDTOs
     */
    void insertBatch(List<AccountInputDTO> accountInputDTOs);
}
