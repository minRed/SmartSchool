package com.zhijia.smartschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.mapper.StudentMapper;
import com.zhijia.smartschool.pojo.Clazz;
import com.zhijia.smartschool.pojo.LoginForm;
import com.zhijia.smartschool.pojo.Student;
import com.zhijia.smartschool.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhijia.smartschool.util.MD5;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public Student login(LoginForm loginForm) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getName, loginForm.getUsername());
        queryWrapper.eq(Student::getPassword, MD5.encrypt(loginForm.getPassword()));
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Student getStudentById(int intValue) {
        return this.baseMapper.selectById(intValue);
    }

    @Override
    public Page<Student> selectPage(Integer pageNo, Integer pageSize, Student student) {
        Page<Student> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        String clazzName = student.getClazzName();
        if (clazzName!=null){
            queryWrapper.eq(Student::getClazzName, clazzName);
        }
        String name = student.getName();
        if (name != null) {
            queryWrapper.like(Student::getName, name);
        }
        queryWrapper.orderByDesc(Student::getId);
        this.baseMapper.selectPage(page, queryWrapper);
        return page;
    }

}
