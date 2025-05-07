package org.SETrain.CareerNavigator.Service;

import org.SETrain.CareerNavigator.Entity.Summary;

public interface SummaryService {
  Summary getSummaryByResumeId(Integer resumeId);
}