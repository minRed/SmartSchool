package com.zhijia.smartschool.service;

import com.zhijia.smartschool.pojo.LoginForm;
import com.zhijia.smartschool.pojo.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
public interface TeacherService extends IService<Teacher> {

    Teacher login(LoginForm loginForm);

    Teacher getTeacherById(int intValue);
}
