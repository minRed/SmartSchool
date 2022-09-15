package com.zhijia.smartschool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhijia.smartschool.mapper.ClazzMapper;
import com.zhijia.smartschool.pojo.Clazz;
import com.zhijia.smartschool.service.ClazzService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

    @Override
    public Page<Clazz> selectPage(Integer pageNo, Integer pageSize, Clazz clazz) {
        Page<Clazz> page = new Page<>(pageNo,pageSize);
        LambdaQueryWrapper<Clazz> queryWrapper = new LambdaQueryWrapper<>();

        String gradeName = clazz.getGradeName();
        if (gradeName != null) {
            queryWrapper.eq(Clazz::getGradeName, gradeName);
        }
        String name = clazz.getName();
        if (name != null) {
            queryWrapper.like(Clazz::getName, name);
        }
        queryWrapper.orderByDesc(Clazz::getId);
        this.baseMapper.selectPage(page, queryWrapper);
        return page;
    }
}
