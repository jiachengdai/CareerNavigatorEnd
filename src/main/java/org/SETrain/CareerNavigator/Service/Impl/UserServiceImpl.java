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
  public int updateUsername(String oldusername, String username) {
    return userMapper.updateUsername(oldusername, username);
  }

  @Override
  public int updateNickname(Integer id, String nickname) {
    return userMapper.updateNickname(id, nickname);
  }

  @Override
  public int update(User user) {
    return userMapper.update(user);
  }

  @Override
  public int updateSex(Integer id, String sex) {
    return userMapper.updateSex(id, sex);
  }

  @Override
  public int updateAge(Integer id, Integer age) {
    return userMapper.updateAge(id, age);
  }

  @Override
  public int updateEducation(Integer id, String education) {
    return userMapper.updateEducation(id, education);
  }

  @Override
  public int updateMajor(Integer id, String major) {
    return userMapper.updateMajor(id, major);
  }

  @Override
  public int updateTel(Integer id, String tel) {
    return userMapper.updateTel(id, tel);
  }

  @Override
  public int updateEmail(Integer id, String email) {
    return userMapper.updateEmail(id, email);
  }

  @Override
  public int updateExpectedJob(Integer id, String expectedJob) {
    return userMapper.updateExpectedJob(id, expectedJob);
  }

  @Override
  public int updateGraduationTime(Integer id, String graduationTime) {
    return userMapper.updateGraduationTime(id, graduationTime);
  }

  @Override
  public int deleteById(Integer id) {
    return userMapper.deleteById(id);
  }
}