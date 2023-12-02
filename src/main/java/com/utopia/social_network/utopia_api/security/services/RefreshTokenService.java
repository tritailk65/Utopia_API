/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.security.services;

import com.utopia.social_network.utopia_api.entity.RefreshToken;
import com.utopia.social_network.utopia_api.exception.TokenRefreshException;
import com.utopia.social_network.utopia_api.repository.RefreshTokenRepository;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author trita
 */
@Service
public class RefreshTokenService {
    @Value("${utopia.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
      return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
      RefreshToken refreshToken = new RefreshToken();

      refreshToken.setUser(userRepository.findById(userId).get());
      refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
      refreshToken.setToken(UUID.randomUUID().toString());

      refreshToken = refreshTokenRepository.save(refreshToken);
      return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
      if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
        refreshTokenRepository.delete(token);
        throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
      }

      return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
      return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
