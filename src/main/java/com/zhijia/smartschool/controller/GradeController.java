package com.zhijia.smartschool.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.pojo.Grade;
import com.zhijia.smartschool.service.GradeService;
import com.zhijia.smartschool.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
@RestController
@RequestMapping("/sms/gradeController")
@Api(tags = "年级控制器")
public class GradeController {

    @Autowired
    private GradeService gradeService;




    /**
     * 查询所有的年级
     * @return
     */
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> list = gradeService.list();
        return Result.ok(list);
    }


    /**
     * 分页查询带条件
     * @param pageNo
     * @param pageSize
     * @param gradeName
     * @return
     */
    @ApiOperation("根据名称模糊查询，带分页")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(@ApiParam("当前第几页") @PathVariable("pageNo") Integer pageNo,
                            @ApiParam("每页记录数") @PathVariable("pageSize") Integer pageSize,
                            @ApiParam("年级名称") String gradeName){

        Page<Grade> page = gradeService.selectPage(pageNo, pageSize, gradeName);

        return Result.ok(page);
    }


    /**
     * 修改或保存
     * @param grade
     * @return
     */
    @PostMapping("/saveOrUpdateGrade")
    @ApiOperation("修改或保存，有id修改，没id保存")
    public Result saveOrUpdateGrade(@RequestBody Grade grade){
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

    /**
     * 删除
     * @param list
     * @return
     */
    @DeleteMapping("/deleteGrade")
    @ApiOperation("单个或批量删除")
    public Result deleteGradeByIds(@ApiParam("年级id集合") @RequestBody List<Integer> list){
        gradeService.removeByIds(list);
        return Result.ok();
    }


}
