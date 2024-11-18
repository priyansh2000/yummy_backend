package com.priyansh.yummy.auth;
import com.priyansh.yummy.entity.Customer;
import com.priyansh.yummy.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepo customerRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;
    public String login(String email, String password) {
        Customer customer = customerRepo.findByEmail(email);

        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            return jwtUtil.generateToken(email);
        } else {
            return "Invalid email or password.";
        }
    }
}