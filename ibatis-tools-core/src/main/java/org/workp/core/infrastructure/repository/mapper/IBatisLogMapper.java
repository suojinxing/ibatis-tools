package org.workp.core.infrastructure.repository.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IBatisLogMapper {
    @Select("select * from user where id = #{id}")
    List query(@Param("id") int id);
}
