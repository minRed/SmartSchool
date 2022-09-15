package com.zhijia.smartschool.service;

import com.zhijia.smartschool.pojo.LoginForm;
import com.zhijia.smartschool.pojo.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
public interface StudentService extends IService<Student> {

    Student login(LoginForm loginForm);

    Student getStudentById(int intValue);
}
