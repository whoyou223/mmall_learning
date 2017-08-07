package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by feng on 2017/7/31.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
