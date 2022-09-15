package com.zhijia.smartschool.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhijia.smartschool.pojo.LoginForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
public interface AdminService extends IService<Admin> {

    Admin login(LoginForm loginForm);

    Admin getAdminById(int intValue);

    Page<Admin> selectPage(Integer pageNo, Integer pageSize, String adminName);
}
