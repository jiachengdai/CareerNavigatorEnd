package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.User;
import java.util.List;

public interface UserService {
  User findById(Integer id);

  User findByUsername(String username);

  List<User> findAll();

  int insert(User user);

  int update(User user);

  int deleteById(Integer id);
}