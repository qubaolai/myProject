package com.guanghua.mapper;

import com.guanghua.po.Lea;
import com.guanghua.po.LeaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LeaMapper {
    int countByExample(LeaExample example);

    int deleteByExample(LeaExample example);

    int deleteByPrimaryKey(String id);

    int insert(Lea record);

    int insertSelective(Lea record);

    List<Lea> selectByExample(LeaExample example);

    Lea selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Lea record, @Param("example") LeaExample example);

    int updateByExample(@Param("record") Lea record, @Param("example") LeaExample example);

    int updateByPrimaryKeySelective(Lea record);

    int updateByPrimaryKey(Lea record);
}