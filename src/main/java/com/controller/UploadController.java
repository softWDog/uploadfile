package com.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.doamain.UploadedFile;

@Controller
public class UploadController {

    private static final Log logger = LogFactory
            .getLog(UploadController.class);

    @RequestMapping(value = "/html5")
    public String inputFile() {
    	logger.info("inputFile() called");
        return "Html5";
    }

    @RequestMapping(value = "/upload-file")
    public void saveFile(HttpServletRequest servletRequest,
            @ModelAttribute UploadedFile uploadedFile,
            BindingResult bindingResult, Model model) {
        MultipartFile multipartFile = uploadedFile.getMultipartFile();
        String fileName =multipartFile.getOriginalFilename();
        int lastIndex=fileName.lastIndexOf("\\");
        String name=fileName.substring(lastIndex+1);
        System.out.println(name);
        try {
            File file = new File(servletRequest.getSession().getServletContext()
                    .getRealPath("/file"), name);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
