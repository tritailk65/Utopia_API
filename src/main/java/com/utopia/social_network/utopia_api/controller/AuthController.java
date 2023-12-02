/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;

import com.utopia.social_network.utopia_api.entity.RefreshToken;
import com.utopia.social_network.utopia_api.entity.Role;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.TokenRefreshException;
import com.utopia.social_network.utopia_api.model.ERole;
import com.utopia.social_network.utopia_api.model.TokenRefreshRequest;
import com.utopia.social_network.utopia_api.model.TokenRefreshResponse;
import com.utopia.social_network.utopia_api.model.UserLoginModel;
import com.utopia.social_network.utopia_api.model.UserProfileModel;
import com.utopia.social_network.utopia_api.model.UserRegisterModel;
import com.utopia.social_network.utopia_api.repository.RoleRepository;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import com.utopia.social_network.utopia_api.security.jwt.JwtUtils;
import com.utopia.social_network.utopia_api.security.services.RefreshTokenService;
import com.utopia.social_network.utopia_api.security.services.UserDetailsImpl;
import com.utopia.social_network.utopia_api.utils.APIResult;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author trita
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    
    @Autowired
    private APIResult rs;
    
    @Autowired
    RefreshTokenService refreshTokenService;
    
    @PostMapping("/signin")
    public ResponseEntity<APIResult> authenticateUser(@Valid @RequestBody UserLoginModel loginRequest) {

        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
            .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        UserProfileModel u = new UserProfileModel(userDetails.getId(),
                                             userDetails.getUsername(),
                                             userDetails.getEmail(),
                                             roles,
        jwt,refreshToken.getToken(),"Bearer");

        return ResponseEntity.ok()
                  .body(new APIResult(200, "OK", null, u));
    }
    
    @PostMapping("/signup")
    public APIResult registerUser(@Valid @RequestBody UserRegisterModel signUpRequest) {
      if (userRepository.existsByUserName(signUpRequest.getUserName())) {
        return new APIResult(400, "Error: Username is already taken!", "BAD_REQUEST", null);
      }

      if (userRepository.existsByEmail(signUpRequest.getEmail())) {
           return new APIResult(400, "Error: Email is already in use!", "BAD_REQUEST", null);
      }

      // Create new user's account
      User user = new User(signUpRequest.getUserName(), 
                 encoder.encode(signUpRequest.getPassword()),
                 signUpRequest.getEmail());

      Set<String> strRoles = signUpRequest.getRole();
      Set<Role> roles = new HashSet<>();

      if (strRoles == null) {
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
      } else {
          strRoles.forEach(role -> {
            switch (role) {
            case "admin":
              Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(adminRole);

              break;
  //          case "mod":
  //            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
  //                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
  //            roles.add(modRole);
  //
  //            break;
            default:
              Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
              roles.add(userRole);
            }
          });
      }

      user.setRoles(roles);
      userRepository.save(user);

      return rs.MessageSuccess("User registered successfully!",user);

    }
    
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
      String requestRefreshToken = request.getRefreshToken();

      return refreshTokenService.findByToken(requestRefreshToken)
          .map(refreshTokenService::verifyExpiration)
          .map(RefreshToken::getUser)
          .map(user -> {
            String token = jwtUtils.generateTokenFromUsername(user.getUserName());
            return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
          })
          .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
              "Refresh token is not in database!"));
    }
}
