package com.qubaolai.controller;

import com.qubaolai.service.FileService;
import com.qubaolai.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @PostMapping("/uploadFile")
    public ResultVo uploadFile(@Param("headPhoto") MultipartFile headPhoto, @Param("path") String path){
        Map<String, Object> param = new HashMap<>();
        param.put("multipartFile", headPhoto);
        param.put("path", path);
        ResultVo resultVo = fileService.uploadFile(param);
        return resultVo;
    }

    @PostMapping("addFileInfo")
    public ResultVo addFileInfo(@Param("path") String path,@Param("name") String name){
        fileService.addFileInfo(path, name);
        return ResultVo.sendResult(200, "success");
    }
}
