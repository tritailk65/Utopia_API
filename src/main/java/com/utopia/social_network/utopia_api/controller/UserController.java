/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;

import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IUserService;
import com.utopia.social_network.utopia_api.model.UserLoginModel;
import com.utopia.social_network.utopia_api.model.UserProfileModel;
import com.utopia.social_network.utopia_api.model.UserRegisterModel;
import com.utopia.social_network.utopia_api.service.FileStorageService;
import com.utopia.social_network.utopia_api.utils.APIResult;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author trita
 */
@CrossOrigin
@RestController
@RequestMapping("/api/User")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private APIResult rs;

    @PostMapping(value = "/UploadAvatar/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    private APIResult uploadFile(@RequestPart(value = "avatar") MultipartFile file, @PathVariable("id") Long id) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                //                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        String rs = fileName + fileDownloadUri + file.getContentType() + file.getSize();

        userService.updateUserAvatarPath(fileName, id);

        return new APIResult(200, "Ok", null, null);
    }

    @GetMapping(value = "/Avatar/{id}")
    private ResponseEntity<Resource> getAvatar(@PathVariable("id") Long id, HttpServletRequest request) {

        User u = new User();
        u = userService.getUserById(id);

        String fileName = u.getAvatarPath();

        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                // for download source image
                //                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }

    @GetMapping
    private APIResult getAllUser() {
        return new APIResult(200, "Ok", null, userService.getAllUser());
    }

    @GetMapping(value = {"/{id}"})
    private APIResult getAllUserById(@PathVariable("id") Long id) {
        return new APIResult(200, "Ok", null, userService.getUserById(id));
    }

    @GetMapping(value = {"/Login"})
    private APIResult userLogin(@RequestBody UserLoginModel uLogin) {
        if (userService.login(uLogin)) {
            return new APIResult(200, "Đăng nhập thành công !", null, null);
        } else {
            throw new ResourceNotFoundException("Đăng nhập không thành công !");
        }     
    }

    @PostMapping(value = "/SignUp")
    private APIResult userRegister(@RequestBody UserRegisterModel u){
        return new APIResult(200, "Ok", null, userService.signUp(u));
    }

    @PutMapping(value = {"/EditProfile/{id}"})
    private APIResult editProfile(@RequestBody UserProfileModel uProfile, @PathVariable("id") Long id) {
        return new APIResult(200, "Ok", null, userService.editProfile(uProfile, id));
    }

}
