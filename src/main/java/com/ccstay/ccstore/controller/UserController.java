package com.ccstay.ccstore.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ccstay.ccstore.controller.ex.FileEmptyException;
import com.ccstay.ccstore.controller.ex.FileSizeException;
import com.ccstay.ccstore.controller.ex.FileTypeException;
import com.ccstay.ccstore.entity.User;
import com.ccstay.ccstore.service.IUserService;
import com.ccstay.ccstore.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private static final long AVATAR_MAX_SIZE = 1 * 1024 * 1024;
    private static final List<String> AVATAR_TYPES=new ArrayList<String>();
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
    }
    @Autowired
    private IUserService service;

    @PostMapping("reg")
    public JsonResult<Void> reg(User user) {
        service.reg(user);
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(@RequestParam("old_password") String oldPassword,
            @RequestParam("new_password") String newPassword, HttpSession session) {

        // 从session中获取uid
        Integer uid = Integer.valueOf(session.getAttribute(SESSION_UID).toString());

        // 从session中获取username
        String username = session.getAttribute(SESSION_USERNAME).toString();

        service.changePassword(uid, oldPassword, newPassword, username);
        return new JsonResult<Void>(SUCCESS);
    }

    @PostMapping("/change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        // 从session中获取uid
        Integer uid = Integer.valueOf(session.getAttribute(SESSION_UID).toString());

        // 从session中获取username
        String username = session.getAttribute(SESSION_USERNAME).toString();

        // 调用service方法更新用户数据
        service.changeInfo(uid, username, user);

        return new JsonResult<>(SUCCESS);
    }

    @PostMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session) {

        // 调用service的login()进行登录
        User user = service.login(username, password);

        // 向session中添加uid
        session.setAttribute(SESSION_UID, user.getUid());

        // 向session中添加username
        session.setAttribute(SESSION_USERNAME, user.getUsername());

        return new JsonResult<User>(SUCCESS, user);
    }

    @GetMapping("/get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        // 从session中获取uid
        Integer uid = Integer.valueOf(session.getAttribute(SESSION_UID).toString());
        // 调用service获取用户数据
        User user = service.getByUid(uid);
        // 返回用户数据
        return new JsonResult<User>(SUCCESS, user);
    }

    @PostMapping("/change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        
        if(!AVATAR_TYPES.contains(file.getContentType())) {
            throw new FileTypeException("文件上传异常！文件类型不正确，允许的类型有："+AVATAR_TYPES);
        }
        if(file.isEmpty()) {
            throw new FileEmptyException("文件上传异常！文件不能为空");
        }
        if(file.getSize()>AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件上传异常！文件大小超过上限:"+(AVATAR_MAX_SIZE/1024)+"kb");
        }
        // 生成文件名
        String oFilename = file.getOriginalFilename();
        Integer index = Integer.valueOf(oFilename.lastIndexOf("."));
        String path = "";
        if (index != -1) {
            path = oFilename.substring(index);
        }
        String filename = UUID.randomUUID().toString() + path;
        // 生成目标路径
        String filepath = request.getServletContext().getRealPath(getDirPath());
        File parent = new File(filepath);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        File dest = new File(parent, filename);
        // 将用户上传的头像保存到服务器上
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将头像在服务器的路径保存到数据库
        // 保存相对于服务器的路径 不应该保存硬盘路径
        String avatar = getDirPath() + filename;
        Integer uid = getUidFromSession(request.getSession());
        String username = getUsernameFromSession(request.getSession());
        service.changeAvatar(uid, avatar, username);
        return new JsonResult<String>(SUCCESS, avatar);

    }

    /**
     * 动态生成路径
     */
    private String getDirPath() {
        return "/upload/";
    }

}
