package com.zhijia.smartschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhijia.smartschool.mapper.StudentMapper;
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

}
