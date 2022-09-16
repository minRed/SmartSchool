package com.zhijia.smartschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.pojo.LoginForm;
import com.zhijia.smartschool.pojo.Teacher;
import com.zhijia.smartschool.mapper.TeacherMapper;
import com.zhijia.smartschool.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhijia.smartschool.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public Teacher login(LoginForm loginForm) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teacher::getName, loginForm.getUsername());
        queryWrapper.eq(Teacher::getPassword, MD5.encrypt(loginForm.getPassword()));
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Teacher getTeacherById(int intValue) {
        return this.baseMapper.selectById(intValue);
    }

    @Override
    public Page<Teacher> selectPage(Integer pageNo, Integer pageSize, Teacher teacher) {

        Page<Teacher> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(teacher.getName())) {
            queryWrapper.like(Teacher::getName, teacher.getName());
        }
        if (!StringUtils.isEmpty(teacher.getClazzName())) {
            queryWrapper.eq(Teacher::getClazzName, teacher.getClazzName());
        }
        queryWrapper.orderByDesc(Teacher::getId);
        this.baseMapper.selectPage(page, queryWrapper);
        return page;
    }
}
