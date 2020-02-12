package com.qubaolai.controller;

import com.qubaolai.common.exception.exceptions.ParamException;
import com.qubaolai.service.FileService;
import com.qubaolai.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/2/10 13:12
 * @Description 文件上传接口
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileService fileService;

    /**
     * 图片文件上传
     * @param headPhoto
     * @param path
     * @return
     */
    @PostMapping("/uploadFile")
    public ResultVo uploadFile(@Param("headPhoto") MultipartFile headPhoto, @Param("path") String path){
        Map<String, Object> param = new HashMap<>();
        param.put("multipartFile", headPhoto);
        param.put("path", path);
        ResultVo resultVo = fileService.uploadFile(param);
        return resultVo;
    }

    /**
     * 图片信息持久化
     * @param path
     * @return
     */
    @RequestMapping("/addFileInfo")
    public ResultVo addFileInfo(String path){
        if(path == null || "".equals(path)){
            throw new ParamException(501, "获取图片路径失败");
        }
        fileService.addFileInfo(path);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 获取用户头像路径
     * @return
     */
    @GetMapping("/getHeadPhoto")
    public ResultVo getPhoto(){
        String photo = fileService.getPhoto();
        String base64Path = "data:image/png;base64," + photo;
        return ResultVo.sendResult(200, "success", base64Path);
    }

    @DeleteMapping("deleteFile")
    public ResultVo deleteFile(String path){
        fileService.deleteFile(path);
        return ResultVo.sendResult(200, "success");
    }
}
