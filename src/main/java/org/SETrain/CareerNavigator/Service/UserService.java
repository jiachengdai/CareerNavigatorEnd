package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.User;
import java.util.List;

public interface UserService {
  User findById(Integer id);

  User findByUsername(String username);

  List<User> findAll();

  int insert(User user);

  int updateUsername(String oldUsername, String newUsername);

  int updateNickname(Integer id, String nickname);
  int update(User user);

  int updateSex(Integer id, String sex);

  int updateAge(Integer id, Integer age);

  int updateEducation(Integer id, String education);

  int updateMajor(Integer id, String major);

  int updateTel(Integer id, String tel);

  int updateEmail(Integer id, String email);

  int updateExpectedJob(Integer id, String expectedJob);

  int updateGraduationTime(Integer id, String graduationTime);

  int deleteById(Integer id);
}