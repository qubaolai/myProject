package com.qubaolai.mapper;

import com.qubaolai.po.Move;
import com.qubaolai.po.MoveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MoveMapper {
    int countByExample(MoveExample example);

    int deleteByExample(MoveExample example);

    int deleteByPrimaryKey(String id);

    int insert(Move record);

    int insertSelective(Move record);

    List<Move> selectByExample(MoveExample example);

    Move selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Move record, @Param("example") MoveExample example);

    int updateByExample(@Param("record") Move record, @Param("example") MoveExample example);

    int updateByPrimaryKeySelective(Move record);

    int updateByPrimaryKey(Move record);
}