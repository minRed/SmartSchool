package com.zhijia.smartschool.controller;


import com.zhijia.smartschool.pojo.Admin;
import com.zhijia.smartschool.pojo.LoginForm;
import com.zhijia.smartschool.pojo.Student;
import com.zhijia.smartschool.pojo.Teacher;
import com.zhijia.smartschool.service.AdminService;
import com.zhijia.smartschool.service.StudentService;
import com.zhijia.smartschool.service.TeacherService;
import com.zhijia.smartschool.util.CreateVerifyCodeImage;
import com.zhijia.smartschool.util.JwtHelper;
import com.zhijia.smartschool.util.Result;
import com.zhijia.smartschool.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/sms/system")
@Api(tags = "系统控制类")
public class SystemController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getInfo")
    public Result getInfo(@RequestHeader("token") String token){
        if (JwtHelper.isExpiration(token)){
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);

        Map<String, Object> map = new HashMap<>();
        switch (userType){
            case 1:
                Admin admin = adminService.getAdminById(userId.intValue());
                map.put("user", admin);
                map.put("userType", userType);
                break;
            case 2:
                Student student = studentService.getStudentById(userId.intValue());
                map.put("user", student);
                map.put("userType", userType);
                break;
            case 3:
                Teacher teacher = teacherService.getTeacherById(userId.intValue());
                map.put("user", teacher);
                map.put("userType", userType);
                break;
        }

        return Result.ok(map);
    }

    @GetMapping("/getVerifiCodeImage")
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response){
        //获取验证码图片
        BufferedImage verifyCodeImage = CreateVerifyCodeImage.getVerifyCodeImage();
        //获取验证码
        char[] verifyCode = CreateVerifyCodeImage.getVerifyCode();
        String code = new String(verifyCode);
        //将验证码存入session，以便下一次使用
        HttpSession session = request.getSession();
        session.setAttribute("verifyCode",code);
        //将验证码图片响应给浏览器
        try {
            ImageIO.write(verifyCodeImage,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PostMapping("/login")
    public Result login(HttpServletRequest request,@RequestBody LoginForm loginForm){
        HttpSession session = request.getSession();
        String saveVerifyCode = (String) session.getAttribute("verifyCode");
        String newVerifiCode = loginForm.getVerifiCode();
        if ("".equals(saveVerifyCode) || saveVerifyCode == null) {
            return Result.fail().message("验证码失效");
        }
        if (!saveVerifyCode.equalsIgnoreCase(newVerifiCode)) {
            return Result.fail().message("验证码有误,请刷新后重新输入");
        }
        session.removeAttribute("verifyCode");

        Map<String, Object> map = new LinkedHashMap<>();
        switch (loginForm.getUserType()){
            case 1:
                try {
                    Admin admin = adminService.login(loginForm);
                    if (admin != null) {
                        map.put("token", JwtHelper.createToken(admin.getId().longValue(), loginForm.getUserType()));
                    }else{
                        throw new RuntimeException("用户名或密码错误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 2:
                try {
                    Student student = studentService.login(loginForm);
                    if (student != null) {
                        map.put("token", JwtHelper.createToken(student.getId().longValue(), loginForm.getUserType()));
                    }else{
                        throw new RuntimeException("用户名或密码错误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 3:
                try {
                    Teacher teacher = teacherService.login(loginForm);
                    if (teacher != null) {
                        map.put("token", JwtHelper.createToken(teacher.getId().longValue(), loginForm.getUserType()));
                    }else{
                        throw new RuntimeException("用户名或密码错误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
        }

        return Result.fail().message("查无此用户");
    }

}
