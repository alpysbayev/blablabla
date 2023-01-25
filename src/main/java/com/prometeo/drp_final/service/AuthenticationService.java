package com.prometeo.drp_final.service;

import com.prometeo.drp_final.converter.DtoConverter;
import com.prometeo.drp_final.converter.UserDtoConverter;
import com.prometeo.drp_final.model.frontend.UserDto;
import com.prometeo.drp_final.model.frontend.UserDtoLogin;
import com.prometeo.drp_final.utils.authentication.AuthenticationRequest;
import com.prometeo.drp_final.utils.authentication.AuthenticationResponse;
import com.prometeo.drp_final.utils.authentication.RegisterRequest;
import com.prometeo.drp_final.config.JwtService;
import com.prometeo.drp_final.model.entity.User;
import com.prometeo.drp_final.repository.UserRepository;
import com.prometeo.drp_final.utils.exception.InventException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final UserDtoConverter userDtoConverter;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public String register(UserDto userDto) {
    // проверка на существование юзера с таким email, если существует выбрасываем null
    if (!userRepository.findByEmail(userDto.getEmail()).isEmpty())
      return null;

    // создаем юзера по userDto
    User user = new User();
    userDtoConverter.createFromDto(user, userDto);
    String confirm_key = givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect(); //генерация рандомного ключа
    user.setConfirmationKey(confirm_key);    // устанавливаем ключ подтверждения для ссылки отсылаемой по почте
    user.setCreated(new Date());
    user.setUpdated(new Date());
    //сохраняем юзера в БД
    try {

      userRepository.save(user);

    } catch (InventException e) {
      throw new InventException(e.getMessage());
    } catch (Exception e) {
      System.err.println("Error acquired while adding user to User table! " + e);
    }


    return confirm_key;
  }

  public AuthenticationResponse authenticate(UserDtoLogin userDtoLogin) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userDtoLogin.getEmail(),userDtoLogin.getPassword())
    );

    User user = userRepository.findByEmail(userDtoLogin.getEmail()).get();

    if (user == null)            // юзер не найден
      return null;
    if (!user.getEmailConfirmed())  // почта не подтверждена
      return null;

    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public String givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect() {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

    return generatedString;
  }
}
