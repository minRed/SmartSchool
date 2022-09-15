package com.zhijia.smartschool.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.pojo.Clazz;
import com.zhijia.smartschool.service.ClazzService;
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
@RestController
@RequestMapping("/sms/clazzController")
@Api(tags = "班级控制类")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    //GET /sms/clazzController/getClazzsByOpr/1/3?gradeName=%E4%B8%80%E5%B9%B4%E7%BA%A7&name=1 HTTP/1.1
    @ApiOperation("查询班级列表，带分页")
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzsByOpr(
            @ApiParam("当前页数") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("每页记录数") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("查询条件") Clazz clazz
    ) {

        Page<Clazz> page = clazzService.selectPage(pageNo,pageSize,clazz);
        return Result.ok(page);

    }

    @ApiOperation("修改/添加班级")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(
            @ApiParam("班级信息") @RequestBody Clazz clazz
    ){
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }

    @ApiOperation("删除班级")
    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(@ApiParam("班级id集合") @RequestBody List<Integer> ids){
        clazzService.removeByIds(ids);
        return Result.ok();
    }

}
