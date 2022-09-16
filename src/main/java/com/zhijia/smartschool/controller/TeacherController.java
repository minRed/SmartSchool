package com.zhijia.smartschool.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.pojo.Teacher;
import com.zhijia.smartschool.service.TeacherService;
import com.zhijia.smartschool.util.MD5;
import com.zhijia.smartschool.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
@Api(tags = "教师信息管理控制器")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    @ApiOperation("获取教师列表")
    public Result  getTeachers(@ApiParam("当前所在页数") @PathVariable("pageNo") Integer pageNo,
                               @ApiParam("每页记录数") @PathVariable("pageSize") Integer pageSize,
                               @ApiParam("教师条件信息") Teacher teacher){
        Page<Teacher> page = teacherService.selectPage(pageNo, pageSize, teacher);
        return Result.ok(page);
    }

    @ApiOperation("添加/修改教师")
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(@ApiParam("教师信息") @RequestBody Teacher teacher) {
        if (teacher.getId() == null || teacher.getId() == 0) {
            teacher.setPassword(MD5.encrypt(teacher.getPassword()));
        }
        teacherService.saveOrUpdate(teacher);
        return Result.ok();
    }

    @ApiOperation("删除单个或多个用户信息")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(@ApiParam("教师id集合") @RequestBody List<Integer> ids) {
        teacherService.removeByIds(ids);
        return Result.ok();
    }

}
