package com.guanghua.mapper;

import com.guanghua.po.RewardsPunishment;
import com.guanghua.po.RewardsPunishmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RewardsPunishmentMapper {
    int countByExample(RewardsPunishmentExample example);

    int deleteByExample(RewardsPunishmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(RewardsPunishment record);

    int insertSelective(RewardsPunishment record);

    List<RewardsPunishment> selectByExample(RewardsPunishmentExample example);

    RewardsPunishment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RewardsPunishment record, @Param("example") RewardsPunishmentExample example);

    int updateByExample(@Param("record") RewardsPunishment record, @Param("example") RewardsPunishmentExample example);

    int updateByPrimaryKeySelective(RewardsPunishment record);

    int updateByPrimaryKey(RewardsPunishment record);
}