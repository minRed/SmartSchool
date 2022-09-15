package com.zhijia.smartschool.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.pojo.Clazz;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
public interface ClazzService extends IService<Clazz> {

    Page<Clazz> selectPage(Integer pageNo, Integer pageSize, Clazz clazz);
}
