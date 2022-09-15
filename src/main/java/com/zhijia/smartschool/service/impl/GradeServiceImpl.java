package com.zhijia.smartschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.pojo.Grade;
import com.zhijia.smartschool.mapper.GradeMapper;
import com.zhijia.smartschool.service.GradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Override
    public Page<Grade> selectPage(Integer pageNo, Integer pageSize, String gradeName) {
        Page page = new Page(pageNo, pageSize);
        LambdaQueryWrapper<Grade> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(gradeName)) {
            queryWrapper.like(Grade::getName, gradeName);
        }
        queryWrapper.orderByDesc(Grade::getId);
        this.baseMapper.selectPage(page, queryWrapper);
        return page;

    }
}
