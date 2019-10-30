package com.qubaolai.mapper;

import com.qubaolai.po.Position;
import com.qubaolai.po.PositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PositionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbg.generated Tue Oct 29 15:10:59 CST 2019
     */
    long countByExample(PositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbg.generated Tue Oct 29 15:10:59 CST 2019
     */
    int deleteByExample(PositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbg.generated Tue Oct 29 15:10:59 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbg.generated Tue Oct 29 15:10:59 CST 2019
     */
    int insert(Position record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbg.generated Tue Oct 29 15:10:59 CST 2019
     */
    int insertSelective(Position record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbg.generated Tue Oct 29 15:10:59 CST 2019
     */
    List<Position> selectByExample(PositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbg.generated Tue Oct 29 15:10:59 CST 2019
     */
    Position selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbg.generated Tue Oct 29 15:10:59 CST 2019
     */
    int updateByExampleSelective(@Param("record") Position record, @Param("example") PositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbg.generated Tue Oct 29 15:10:59 CST 2019
     */
    int updateByExample(@Param("record") Position record, @Param("example") PositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbg.generated Tue Oct 29 15:10:59 CST 2019
     */
    int updateByPrimaryKeySelective(Position record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table position
     *
     * @mbg.generated Tue Oct 29 15:10:59 CST 2019
     */
    int updateByPrimaryKey(Position record);
}