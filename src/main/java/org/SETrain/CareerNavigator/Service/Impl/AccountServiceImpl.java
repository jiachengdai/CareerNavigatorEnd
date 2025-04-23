package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.Account;
import org.SETrain.CareerNavigator.Mapper.AccountMapper;
import org.SETrain.CareerNavigator.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findByUsername(String username) {
        return accountMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password, Integer type) {
        accountMapper.register(username, password, type);
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        accountMapper.updatePassword(username, newPassword);
    }

    @Override
    public void updateUsername(String oldUsername, String newUsername) {
        accountMapper.updateUsername(oldUsername, newUsername);
    }

    @Override
    public boolean checkUsername(String username) {
        return accountMapper.checkUsername(username) > 0;
    }
}
