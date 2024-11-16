package com.example.yummy.auth;
import com.example.yummy.entity.Customer;
import com.example.yummy.repo.CustomerRepo;
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