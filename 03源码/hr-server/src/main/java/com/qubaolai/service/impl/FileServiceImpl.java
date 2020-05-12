package com.qubaolai.service.impl;

import com.qubaolai.common.exception.exceptions.NoDataException;
import com.qubaolai.common.exception.exceptions.ParamException;
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
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        String path = (String) param.get("path");
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdir();
        }
        File targetFile = new File(path, fileName);
        //将文件保存到服务器指定位置
        try {
            multipartFile.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            return new ResultVo(200, "success", targetFile);
        } catch (IOException e) {
            log.error("上传文件发生异常:{}", e.getMessage(), e);
            e.printStackTrace();
            return new ResultVo(509, "上传失败");
        }
    }

    @Override
    public void addFileInfo(String path) {
        String fileName;
        String parentPath;
        String[] filePath = path.split("\\\\"); //部分标点符号不能直接使用，需要加\\ 而恰好要分割的符号也是\\
        fileName = filePath[filePath.length - 1];//这里取到最后一个数组单元，即文件名
        char[] pathTemp = new char[50];
        path.getChars(0, path.length(), pathTemp, 0);
        //将文件名之前的字符串都拷贝到路径字符串中
        parentPath = String.valueOf(pathTemp, 0, path.length() - fileName.length() - 1);
        Employee currentLoginEmployee = employeeService.getCurrentLoginEmployee();
        FileInfoExample example = new FileInfoExample();
        FileInfoExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNumberEqualTo(currentLoginEmployee.getId());
        List<FileInfo> fileInfos = fileInfoMapper.selectByExample(example);
        if (fileInfos != null && 1 <= fileInfos.size()) {
            FileInfo fileInfo = fileInfos.get(0);
            fileInfo.setFileName(fileName);
            fileInfo.setFilePath(parentPath);
            fileInfo.setUploadTime(DateUtil.getDate());
            fileInfoMapper.updateByPrimaryKey(fileInfo);
        } else {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setId(UUIDUtil.getUUID());
            fileInfo.setEmployeeNumber(currentLoginEmployee.getId());
            fileInfo.setFilePath(parentPath);
            fileInfo.setFileName(fileName);
            fileInfo.setUploadTime(DateUtil.getDate());
            fileInfoMapper.insert(fileInfo);
        }
    }

    @Override
    public String getPhoto() {
        //获取当前登录用户
        Employee currentLoginEmployee = employeeService.getCurrentLoginEmployee();
        FileInfoExample example = new FileInfoExample();
        FileInfoExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNumberEqualTo(currentLoginEmployee.getId());
        List<FileInfo> fileInfos = fileInfoMapper.selectByExample(example);
        if (fileInfos == null || 0 >= fileInfos.size()) {
            throw new NoDataException(400, "获取用户头像信息失败!");
        }
        FileInfo fileInfo = fileInfos.get(0);
        String path = fileInfo.getFilePath() + "/" + fileInfo.getFileName();
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    @Override
    public void deleteFile(String path) {
        if (path == null || "".equals(path)) {
            throw new ParamException(501, "文件路径无效");
        }
        File file = new File(path);
        if (file.isFile()) {// 表示该文件不是文件夹
            file.delete();
        }
    }

}
