package com.qubaolai.service.impl;

import com.qubaolai.common.utils.DateUtil;
import com.qubaolai.common.utils.UUIDUtil;
import com.qubaolai.mapper.FileInfoMapper;
import com.qubaolai.po.Employee;
import com.qubaolai.po.FileInfo;
import com.qubaolai.po.FileInfoExample;
import com.qubaolai.service.EmployeeService;
import com.qubaolai.service.FileService;
import com.qubaolai.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/2/10 15:01
 * @Description 文件上传实现类
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private FileInfoMapper fileInfoMapper;
    @Override
    public ResultVo uploadFile(Map<String, Object> param) {
        MultipartFile multipartFile = (MultipartFile) param.get("multipartFile");
        //获取原始文件名称(包含格式)
        String originalFileName = multipartFile.getOriginalFilename();
        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        //设置文件新名称
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;
        //在指定路径下创建一个文件
        String path = (String)param.get("path");
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdir();
        }
        File targetFile = new File(path, fileName);
        //将文件保存到服务器指定位置
        try {
            multipartFile.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            return new ResultVo(200,"success", targetFile);
        } catch (IOException e) {
            log.error("上传文件发生异常:{}", e.getMessage(), e);
            e.printStackTrace();
            return new ResultVo(509, "上传失败");
        }
    }

    @Override
    public void addFileInfo(String path, String name) {
        Employee currentLoginEmployee = employeeService.getCurrentLoginEmployee();
        FileInfoExample example = new FileInfoExample();
        FileInfoExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNumberEqualTo(currentLoginEmployee.getId());
        List<FileInfo> fileInfos = fileInfoMapper.selectByExample(example);
        if(fileInfos != null && 1 >= fileInfos.size()){
            FileInfo fileInfo = fileInfos.get(0);
            fileInfo.setFileName(name);
            fileInfo.setFilePath(path);
            fileInfo.setUploadTime(DateUtil.getDate());
            fileInfoMapper.updateByPrimaryKey(fileInfo);
        }else{
            FileInfo fileInfo = new FileInfo();
            fileInfo.setId(UUIDUtil.getUUID());
            fileInfo.setEmployeeNumber(currentLoginEmployee.getId());
            fileInfo.setFilePath(path);
            fileInfo.setFileName(name);
            fileInfo.setUploadTime(DateUtil.getDate());
            fileInfoMapper.insert(fileInfo);
        }
    }
}
