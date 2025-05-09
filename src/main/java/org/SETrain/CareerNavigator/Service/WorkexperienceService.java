package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.Workexperience;
import java.util.List;

public interface WorkexperienceService {
    List<Workexperience> getWorkexperienceByResumeId(Integer resumeId);

    void insertWorkexperience(Workexperience workexperience);

    void updateWorkexperience(Workexperience workexperience);

    void deleteWorkexperience(Integer workid);
}
