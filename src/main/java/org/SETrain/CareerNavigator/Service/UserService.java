package org.SETrain.CareerNavigator.Service;

import com.github.pagehelper.PageInfo;
import org.SETrain.CareerNavigator.Entity.User;
import java.util.List;
import java.util.Map;

public interface UserService {
  User findById(Integer id);

  User findByUsername(String username);

  List<User> findAll();

  PageInfo<User> findPage(int pageNum, int pageSize);

  int insert(User user);

  int update(User user);

  int deleteById(Integer id);
}