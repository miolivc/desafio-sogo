package com.miolivc.contract.management.api.resource.v1;

import com.miolivc.contract.management.api.resource.v1.value.JwtToken;
import com.miolivc.contract.management.api.security.JwtTokenService;
import com.miolivc.contract.management.api.resource.v1.value.AuthUser;
import com.miolivc.contract.management.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService tokenService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "returns a valid token to access API endpoints")
    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody AuthUser user) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (BadCredentialsException ex) {
            System.out.println(ex);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password not match");
        }

        final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        var token = tokenService.generateToken(userDetails);

        return ResponseEntity.ok(new JwtToken(token));
    }

}
