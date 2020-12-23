package com.shixun.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shixun.api.entity.Sources;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JiaTao
 * @since 2020-12-16
 */
public interface SourcesMapper extends BaseMapper<Sources> {

    @Select("select id,name,url,remark,pid,icon\n" +
            "from sources \n" +
            "where id in (\n" +
            "select resources_fk\n" +
            "from role_sources\n" +
            "where role_fk = #{roleId}) and pid = #{pid}")
    List<Sources> findUserMenuVO(Integer roleId, Integer pid);
}
