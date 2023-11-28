package org.workp.core.infrastructure.mapper;

import org.workp.core.infrastructure.po.IBatisLogPo;

public interface IBatisLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(IBatisLogPo record);

    int insertSelective(IBatisLogPo record);

    IBatisLogPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IBatisLogPo record);

    int updateByPrimaryKey(IBatisLogPo record);

}
