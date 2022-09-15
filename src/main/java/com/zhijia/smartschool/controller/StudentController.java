package com.zhijia.smartschool.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.pojo.Student;
import com.zhijia.smartschool.service.StudentService;
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
@Api(tags = "学生控制器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("删除单个或多个学生")
    @DeleteMapping("/delStudentById")
    public Result delStudentById(@ApiParam("学生id集合") @RequestBody List<Integer> ids) {
        studentService.removeByIds(ids);
        return Result.ok();
    }

    @PostMapping("/addOrUpdateStudent")
    @ApiOperation("添加或修改学生")
    public Result addOrUpdateStudent(@ApiParam("学生信息") @RequestBody Student student) {
        Integer id = student.getId();
        if (id == null || id == 0) {
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    @ApiOperation("查询学生列表带分页")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(@ApiParam("当前页码") @PathVariable("pageNo") Integer pageNo,
                                  @ApiParam("每页记录数") @PathVariable("pageSize") Integer pageSize,
                                  @ApiParam("查询条件") Student student){

        Page<Student> page = studentService.selectPage(pageNo, pageSize, student);

        return Result.ok(page);
    }
}
