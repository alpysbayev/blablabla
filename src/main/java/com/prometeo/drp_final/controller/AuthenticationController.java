package com.prometeo.drp_final.controller;


import com.prometeo.drp_final.model.frontend.UserDto;
import com.prometeo.drp_final.model.frontend.UserDtoLogin;
import com.prometeo.drp_final.service.UserService;
import com.prometeo.drp_final.utils.MailSenderService;
import com.prometeo.drp_final.utils.authentication.AuthenticationRequest;
import com.prometeo.drp_final.utils.authentication.AuthenticationResponse;
import com.prometeo.drp_final.service.AuthenticationService;
import com.prometeo.drp_final.utils.exception.InventException;
import com.prometeo.drp_final.utils.result.ResponseWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final AuthenticationService service;
  private final UserService userService;

  @Autowired
  private MailSenderService mailSenderService;

  public AuthenticationController(AuthenticationService service, UserService userService) {
    this.service = service;
    this.userService = userService;
  }

  /**
   * user registration
   */

  @PostMapping("/register")
  @ApiOperation(value = "Регистрация пользователя")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto userDto) throws InventException {
    try{
      String confirmationKey = service.register(userDto);
      if (confirmationKey == null){
        return new ResponseWrapper( "User with this email exists", HttpStatus.OK );
      }

      // отправка сообщения со ссылкой на страницу создания пароля по почте
      mailSenderService.sendMessage(userDto.getEmail(),"Email Confirmation","Follow the link to confirm your email : ",confirmationKey);


      return new ResponseWrapper( confirmationKey, HttpStatus.OK );

    }catch (Exception e){
      return new ResponseWrapper( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }

  }

  /**
   * Email confirmation
   */

  @PostMapping(value = "/confirm_email")
  @ApiOperation(value = "Email confirmation")
  public ResponseWrapper<?> confirmEmail (@RequestBody String confirmationKey) throws InventException {
    try {

      Boolean confirmed = userService.toConfirmEmail(confirmationKey);

      if (confirmed)
        return new ResponseWrapper<>( "Email was confirmed!", HttpStatus.OK );
      else
        return new ResponseWrapper<>("Email was not confirmed!",HttpStatus.NOT_MODIFIED);

    } catch ( Exception e ) {
      return new ResponseWrapper( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }
  }

  /**
   * sign in user
   */

  @PostMapping("/authenticate")
  public ResponseWrapper<?> authenticate(@RequestBody UserDtoLogin userDtoLogin)throws InventException{
    try {
      AuthenticationResponse token = service.authenticate(userDtoLogin);
      if (token == null){
        return new ResponseWrapper<String>( "Email is not confirmed!", HttpStatus.NOT_MODIFIED );
      }

      return new ResponseWrapper( token, HttpStatus.OK );

    } catch ( Exception e ) {
      return new ResponseWrapper( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }
  }


}
