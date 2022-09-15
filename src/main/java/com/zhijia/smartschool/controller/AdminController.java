package com.zhijia.smartschool.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.pojo.Admin;
import com.zhijia.smartschool.service.AdminService;
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
@Api(tags = "系统管理员控制器")
@RestController
@RequestMapping("/sms/adminController")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("获取管理员列表")
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(@ApiParam("当前第几页") @PathVariable("pageNo") Integer pageNo,
                              @ApiParam("每页记录数") @PathVariable("pageSize") Integer pageSize,
                              @ApiParam("管理员名称") String adminName) {
        Page<Admin> page = adminService.selectPage(pageNo, pageSize, adminName);
        return Result.ok(page);
    }

    @PostMapping("/saveOrUpdateAdmin")
    @ApiOperation("新增/修改管理员")
    public Result saveOrUpdateAdmin(@ApiParam("管理员信息") @RequestBody Admin admin){
        if (admin.getId() == null || admin.getId() == 0) {
            admin.setPassword(MD5.encrypt(admin.getPassword()));
        }
        adminService.saveOrUpdate(admin);
        return Result.ok();
    }

    @DeleteMapping("/deleteAdmin")
    @ApiOperation("删除单个或多个管理员")
    public Result deleteAdmin(@ApiParam("管理员id集合") @RequestBody List<Integer> ids) {
        adminService.removeByIds(ids);
        return Result.ok();
    }


}
