package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.Account;

public interface AccountService {

    Account findByUsername(String username);

    void register(String username, String password, Integer type);

    void updatePassword(String username, String newPassword);

    void updateUsername(String oldUsername, String newUsername);

    boolean checkUsername(String username);


}
