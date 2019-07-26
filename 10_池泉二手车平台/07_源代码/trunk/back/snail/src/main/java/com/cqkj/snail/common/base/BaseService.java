package com.cqkj.snail.common.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T extends BaseEntity> {

    /**
     * 查询
     * @param id
     * @return
     */
    T get(String id);

    /**
     * 查询
     * @param id
     * @return
     */
    T find(String id);

    /**
     * 删除
     * @param id
     * @return
     */
    void delete(String id);

    void delete(T entity);

    /**
     * 创建
     * @param entity
     * @return
     */
    void create(T entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    void update(T entity);

    /**
     * 读取所有
     * @param pageable
     * @return
     */
    Page<T> page(Pageable pageable);

    /**
     * 判断id是否存在
     * @param id
     * @return
     */
    boolean exists(String id);

}
