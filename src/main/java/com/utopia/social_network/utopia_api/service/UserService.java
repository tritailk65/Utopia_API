/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.MyBadRequestException;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IUserService;
import com.utopia.social_network.utopia_api.model.Email;
import com.utopia.social_network.utopia_api.model.UserLoginModel;
import com.utopia.social_network.utopia_api.model.UserProfileModel;
import com.utopia.social_network.utopia_api.model.UserRegisterModel;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.mail.internet.MimeMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

/**
 *
 * @author trita dsadasdasdasdasdas
 */
@Service
public class UserService implements IUserService {

    @Autowired
    public UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final String SECRET_KEY = generateSecretKey(32);
    
    @Override
    public String sendMail(Email email) {
        User getUser = userRepo.findUserByEmail(email.getEmail());
        if (getUser == null) {
            throw new ResourceNotFoundException("Kiểm tra lại gmail của bạn");
        }
        String fullName = getUser.getFullName();

        // Tạo token có thời hạn 5 phút
        String token = generateResetToken(email.getEmail(), 5);

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom("huaduc710@gmail.com");
            helper.setTo(email.getEmail());
            helper.setSubject("Quên mật khẩu Utopia");
            helper.setText("Hi " + fullName + " nhấn vào link để thay đổi mật khẩu của bạn: " + generateResetLink(token));
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Gửi thành công";
    }

    public static String generateSecretKey(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[length];
        secureRandom.nextBytes(keyBytes);
        return bytesToHex(keyBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    private String generateResetToken(String userEmail, int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expirationDate = calendar.getTime();

        // Tạo token (có thể sử dụng thư viện JWT)
        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private Claims validateToken(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new MyBadRequestException("Mã token đã hết hạn");
        } catch (MalformedJwtException e) {
            throw new MyBadRequestException("Mã token không đúng định dạng");
        } catch (SignatureException e) {
            throw new MyBadRequestException("Mã token không hợp lệ");
        } catch (Exception e) {
            throw new MyBadRequestException("Lỗi khi xác thực mã token");
        }
    }

    private String generateResetLink(String token) {
        // Tạo đường link với token
        return "http://localhost:3000/change-password?token=" + token;
    }

    public String resetPassword(String token, String password) {
        Claims claims = validateToken(token);

        String userEmail = claims.getSubject();
        User user = userRepo.findUserByEmail(userEmail);

        if (user != null) {
            // Thực hiện đổi mật khẩu cho người dùng
            user.setPassword(password);
            userRepo.save(user);
        } else {
            throw new MyBadRequestException("Người dùng không tồn tại");
        }
        return "Đổi mật khẩu thành công";
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        User u = userRepo.findUserById(id);

        if (u == null) {
            throw new ResourceNotFoundException("Khong tim thay User! Kiem tra lai ID");
        } else {
            return u;
        }
    }

    @Override
    public User login(UserLoginModel uLogin) {
        User u = new User();

        if (uLogin.getPhone() != null) {
            u = userRepo.findUserByPhoneAndPassword(uLogin.getPhone(), uLogin.getPassword());
        } else if (uLogin.getUserName() != null) {
            u = userRepo.findUserByUserNameAndPassword(uLogin.getUserName(), uLogin.getPassword());
        } else if (uLogin.getEmail() != null) {
            u = userRepo.findUserByEmailAndPassword(uLogin.getEmail(), uLogin.getPassword());
        }

        if (u != null) {
            return u;
        } else {
            throw new ResourceNotFoundException("Đăng nhập thất bại");
        }

    }

    @Override
    public User signUp(UserRegisterModel userRegisterModel) {

        //Check điều kiện đăng ký tài khoản mới
        if (userRepo.findUserByUserName(userRegisterModel.getUserName()) != null) {
            throw new MyBadRequestException("UserName đã tồn tại");
        } else {
            if ((userRegisterModel.getEmail() != null && userRegisterModel.getEmail() != "") ? (userRepo.findUserByEmail(userRegisterModel.getEmail()) != null) : false) {
                throw new MyBadRequestException("Email đã tồn tại");
            } else if ((userRegisterModel.getPhone() != null && userRegisterModel.getPhone() != "") ? (userRepo.findUserByPhone(userRegisterModel.getPhone()) != null) : false) {
                throw new MyBadRequestException("Số điện thoại đã tồn tại");
            }
        }

        //Update time
        User user = convertToEntity(userRegisterModel);
        Date date = new Date();
        user.setCreateAt(date);
        user.setUpdateAt(date);
        user.setAvatarPath("unknown.png");
        return userRepo.save(user);
    }

    @Override
    public void updateUserAvatarPath(String path, Long id) {
        User u = userRepo.findUserById(id);

        if (u == null) {
            throw new ResourceNotFoundException("Khong tim thay User! Kiem tra lai ID");
        }
        //Khong can try-cath o day
        userRepo.updateUserSetAvatarPathById(path, id);
    }

    @Override
    public User editProfile(UserProfileModel u, Long id) {
        User uCheck = userRepo.findUserById(id);

        if (uCheck == null) {
            throw new ResourceNotFoundException("Khong tim thay User! Kiem tra lai ID");
        }
        userRepo.updateUserSetAvatarPathById(u.getFullName(), u.getWebsite(), u.getBio(), u.getGender(), id);
        return userRepo.findUserById(id);
    }

    private User convertToEntity(UserRegisterModel u) {
        User user = modelMapper.map(u, User.class);
        return user;
    }

    @Override
    public List<User> getSuggestByUser(Long id) {
        if (userRepo.findUserById(id) == null) {
            throw new ResourceNotFoundException("ID User khong dung");
        }
        List<User> rs = userRepo.getListSuggestFollow(id);

        return rs;
    }

    @Override
    public User getUserByUserName(String name) {
        User rs = userRepo.findUserByUserName(name);
        return rs;
    }

}
