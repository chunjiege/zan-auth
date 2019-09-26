package com.zan.hu.auth;

import com.zan.hu.auth.query.ClientInputDTO;

import java.util.List;

/**
 * @version 1.0
 * @Author admin
 * @Date 2019-04-04 13:20
 * @Description todo
 **/
public interface ClientService {

    /**
     * 创建oauth客户端
     *
     * @param clientInputDTO
     * @throws Exception
     */
    void create(ClientInputDTO clientInputDTO) throws Exception;

    /**
     * 更新oauth客户端
     *
     * @param clientInputDTO
     */
    void update(ClientInputDTO clientInputDTO) throws IllegalAccessException;


    /**
     * 删除oauth客户端
     *
     * @param id 主键
     */
    void delete(Long id);


    /**
     * 分页批量查询
     *
     * @param clientInputDTO
     * @return
     */
    List<Client> page(ClientInputDTO clientInputDTO);

    /**
     * 根据clientId查找实例
     *
     * @param clientId
     * @return
     */
    Client selectByClientId(String clientId);
}
