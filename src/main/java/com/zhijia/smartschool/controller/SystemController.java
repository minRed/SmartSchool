package com.zhijia.smartschool.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhijia.smartschool.pojo.Admin;
import com.zhijia.smartschool.pojo.LoginForm;
import com.zhijia.smartschool.pojo.Student;
import com.zhijia.smartschool.pojo.Teacher;
import com.zhijia.smartschool.service.AdminService;
import com.zhijia.smartschool.service.StudentService;
import com.zhijia.smartschool.service.TeacherService;
import com.zhijia.smartschool.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

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


    //    http://localhost:9001/sms/system/updatePwd/admin/123456
    @ApiOperation("修改用户密码")
    @PostMapping("/updatePwd/{oldPwd}/{newPwd}")
    public Result updatePwd(
            @ApiParam("旧密码") @PathVariable("oldPwd") String oldPwd,
            @ApiParam("新密码") @PathVariable("newPwd") String newPwd,
            @ApiParam("用户信息") @RequestHeader("token") String token) {

        if (JwtHelper.isExpiration(token)) {
            return Result.fail().message("身份认证已过期，请重新登录");
        }
        Integer userType = JwtHelper.getUserType(token);
        Long userId = JwtHelper.getUserId(token);
        String oldPassword = MD5.encrypt(oldPwd);
        String newPassword = MD5.encrypt(newPwd);

        switch (userType) {
            case 1:
                LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Admin::getId,userId);
                queryWrapper.eq(Admin::getPassword, oldPassword);
                Admin admin = adminService.getOne(queryWrapper);
                if (admin != null) {
                    admin.setPassword(newPassword);
                    adminService.saveOrUpdate(admin);
                }else{
                    return Result.fail().message("原始密码错误");
                }
                break;
            case 2:
                LambdaQueryWrapper<Student> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Student::getId,userId);
                queryWrapper1.eq(Student::getPassword, oldPassword);
                Student student = studentService.getOne(queryWrapper1);
                if (student != null) {
                    student.setPassword(newPassword);
                    studentService.saveOrUpdate(student);
                }else{
                    return Result.fail().message("原始密码错误");
                }
                break;
            case 3:
                LambdaQueryWrapper<Teacher> queryWrapper2 = new LambdaQueryWrapper<>();
                queryWrapper2.eq(Teacher::getId,userId);
                queryWrapper2.eq(Teacher::getPassword, oldPassword);
                Teacher teacher = teacherService.getOne(queryWrapper2);
                if (teacher != null) {
                    teacher.setPassword(newPassword);
                    teacherService.saveOrUpdate(teacher);
                }else{
                    return Result.fail().message("原始密码错误");
                }
                break;
        }
        return Result.ok();


    }


    @ApiOperation("头像上传统一接口")
    @PostMapping("/headerImgUpload")
    public Result uploadImg(MultipartFile multipartFile){
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String filename = multipartFile.getOriginalFilename();
        int i = filename.lastIndexOf(".");
        String suffix = filename.substring(i);
        filename = uuid.concat(suffix);
        String path = "C:/MyAllProject/SmartSchool/target/classes/public/upload/".concat(filename);
        try {
            multipartFile.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok("upload/".concat(filename));
    }

    @ApiOperation("获取用户信息")
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

    @ApiOperation("获取验证码")
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

    @ApiOperation("登录")
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
