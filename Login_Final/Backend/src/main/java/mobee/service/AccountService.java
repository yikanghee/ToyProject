package mobee.service;

import mobee.entity.Account;
import mobee.dto.AccountRequestDto;
import mobee.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public Account registerAccount(AccountRequestDto requestDto){
        return accountRepository.save(
                Account.builder()
                    .username(requestDto.getUsername())
                    .password(passwordEncoder.encode(requestDto.getPassword()))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build()
        );
    }
}
