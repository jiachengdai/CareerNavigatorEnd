package org.SETrain.CareerNavigator.Service.Impl;

import org.SETrain.CareerNavigator.Entity.User;
import org.SETrain.CareerNavigator.Mapper.UserMapper;
import org.SETrain.CareerNavigator.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserMapper userMapper;

  @Override
  public User findById(Integer id) {
    return userMapper.findById(id);
  }

  @Override
  public User findByUsername(String username) {
    return userMapper.findByUsername(username);
  }

  @Override
  public List<User> findAll() {
    return userMapper.findAll();
  }

  @Override
  public int insert(User user) {
    return userMapper.insert(user);
  }

  @Override
  public int update(User user) {
    return userMapper.update(user);
  }

  @Override
  public int deleteById(Integer id) {
    return userMapper.deleteById(id);
  }
}