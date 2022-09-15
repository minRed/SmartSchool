package com.zhijia.smartschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.mapper.AdminMapper;
import com.zhijia.smartschool.pojo.Admin;
import com.zhijia.smartschool.pojo.LoginForm;
import com.zhijia.smartschool.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhijia.smartschool.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public Admin login(LoginForm loginForm) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getName, loginForm.getUsername());
        queryWrapper.eq(Admin::getPassword, MD5.encrypt(loginForm.getPassword()));
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Admin getAdminById(int userId) {
        Admin admin = this.baseMapper.selectById(userId);
        return admin;
    }

    @Override
    public Page<Admin> selectPage(Integer pageNo, Integer pageSize, String adminName) {

        Page<Admin> page = new Page<>(pageNo,pageSize);
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(adminName)) {
            queryWrapper.like(Admin::getName,adminName);
        }
        queryWrapper.orderByDesc(Admin::getId);
        this.baseMapper.selectPage(page, queryWrapper);
        return page;

    }
}
