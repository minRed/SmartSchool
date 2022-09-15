package com.zhijia.smartschool.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.pojo.Grade;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
public interface GradeService extends IService<Grade> {

    Page<Grade> selectPage(Integer pageNo, Integer pageSize, String gradeName);

}
