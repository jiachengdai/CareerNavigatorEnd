package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.Skills;
import java.util.List;

public interface SkillsService {
    List<Skills> getSkillsByResumeId(Integer resumeId);

    void insertSkill(Skills skill);

    void updateSkill(Skills skill);

    void deleteSkill(Integer skillid);
}