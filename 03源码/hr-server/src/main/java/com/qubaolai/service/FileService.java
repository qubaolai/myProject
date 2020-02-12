package com.qubaolai.service;

import com.qubaolai.vo.ResultVo;

import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/2/10 15:00
 * @Description 文件上传业务接口
 */
public interface FileService {
    /**
     * 上传文件
     * @param param
     * @return
     */
    public ResultVo uploadFile(Map<String, Object> param);

    /**
     * 将上传图片的信息落库
     * @param path
     */
    public void addFileInfo(String path);

    /**
     * 用户获取头像
     * @return
     */
    public String getPhoto();

    /**
     * 删除指定文件
     * @param path
     */
    public void deleteFile(String path);
}
