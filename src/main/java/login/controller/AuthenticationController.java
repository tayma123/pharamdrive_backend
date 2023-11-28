//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package login.controller;



import com.pharamdrive.models.NotificationMessage;
import com.pharamdrive.models.User;
import com.pharamdrive.repository.UserRepository;


import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import login.repository.UserLoginRepository;
import login.request.ChangePwdRequest;
import login.request.LoginRequest;
import login.request.ResetPwdRequest;
import login.request.SignUpRequest;
import login.response.JwtResponse;
import login.response.MessageResponse;
import login.security.jwt.JwtUtils;
import login.services.UserDetailsImpl;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
		origins = {"*"},
		maxAge = 3600L
)
@RestController
@RequestMapping({"/api/auth"})
public class AuthenticationController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserLoginRepository userLoginRepository;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Value("${jwtVerifSecret}")
	private String jwtVerifSecret;
	@Value("${host}")
	private String host;
	@Value("${senderName}")
	private String senderName;




	@Autowired
	UserRepository userRepository;


	public AuthenticationController() {
	}

	@PostMapping({"/signin"})
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) throws SocketException, UnknownHostException {
		String email =loginRequest.getEmail().toLowerCase(Locale.ROOT);
		if (this.userLoginRepository.existsByEmail(email)) {
			User loggedin = this.userLoginRepository.findByEmail(email);
			loginRequest.setUsername(loggedin.getUsername());
			if (loggedin.getEnterPassword() != null && loggedin.getEnterPassword()) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Account! Please register"));
			} else if (loggedin.getConfirmed() != null && !loggedin.getConfirmed()) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Email not confirmed yet !"));
			} else {

				Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
				//s'il est connecté cad passer par l'instruction autentication on va modifier status de tentative par success et on faire l'update
				SecurityContextHolder.getContext().setAuthentication(authentication);
				String jwt = this.jwtUtils.generateJwtToken(authentication);
				UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
				List<String> roles = (List)userDetails.getAuthorities().stream().map((role) -> {
					return role.getAuthority();
				}).collect(Collectors.toList());
				return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
			}
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: No account with entered Email !"));
		}
	}

	@PostMapping({"/signup"})
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		User user = new User();
		String email = signUpRequest.getEmail().toLowerCase(Locale.ROOT);
		if (this.userLoginRepository.existsByEmail(email)) {
			if (this.userLoginRepository.findByEmail(email).getEnterPassword() == null) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
			}

			if (!this.userLoginRepository.findByEmail(email).getEnterPassword()) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
			}

			user = this.userLoginRepository.findByEmail(email);
		}

		user.setUsername(email);
		user.setFirstname(signUpRequest.getFirstname());
		user.setLastname(signUpRequest.getLastname());
		user.setLangue(signUpRequest.getLangue());
		user.setEmail(email);
		user.setVerificationId((new ObjectId()).toString());
		user.setConfirmed(false);
		user.setPassword(this.encoder.encode(signUpRequest.getPassword()));
		this.userLoginRepository.save(user);



		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping({"/confirm"})
	public ResponseEntity<?> confirmUser(@RequestParam("jwt") String jwt) {
		if (jwt != null && this.jwtUtils.validateVerificationToken(jwt)) {
			Claims claims = (Claims) Jwts.parser().setSigningKey(this.jwtVerifSecret).parseClaimsJws(jwt).getBody();
			String verificationId = (String)claims.get("verificationId");
			String email = claims.getSubject().toLowerCase(Locale.ROOT);
			if (this.userLoginRepository.existsByEmail(email)) {
				if (!this.userLoginRepository.findByEmail(email).getVerificationId().equals(verificationId)) {
					return ResponseEntity.badRequest().body(new MessageResponse("Error: Email confirmation refused!"));
				} else {
					User user = this.userLoginRepository.findByEmail(email);
					user.setConfirmed(true);
					this.userLoginRepository.save(user);

					try {

					} catch (Exception e) {

					}

					return ResponseEntity.ok(new MessageResponse("Email confirmed successfully!"));
				}
			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: User no longer registered!"));
			}
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid request!"));
		}
	}


}
