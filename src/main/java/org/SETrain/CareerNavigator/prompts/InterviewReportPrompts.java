package org.SETrain.CareerNavigator.prompts;

public class InterviewReportPrompts {
    public static String getSore="刚刚我们进行了一次面试，请根据面试者的表现进行评分，满分为100分,只返回一个分数就可以，不要返回其他内容，下面是面试对话记录，其中user是被面试者,assistant是面试官，请综合根据面试者的问题回答正确性，回答创造力，语言能力等方面评估。";
    public static String getPreScore="请针对这段面试对话，对面试者的表达能力进行打分，满分100分，只返回一个分数就可以，不要返回其他内容，下面是面试对话记录，其中user是被面试者,assistant是面试官。";


    public static String getAdvice="请针对这段面试对话，给出一些面试能力提升建议，只给出以符号'•'分点的建议即可，不要输出多余的内容，不要markdown格式，其中user是被面试者,assistant是面试官。";
    public static String getSkillsSummary="请针对这段面试对话，给出技术掌握情况分析，以符号'•'分点列项，不要输出多余的内容，不要markdown格式，其中user是被面试者,assistant是面试官。";
    public static String getChangeScore="请针对这段面试对话，对面试者的应变能力进行打分，满分100分，只返回一个分数就可以，不要返回其他内容，下面是面试对话记录，其中user是被面试者,assistant是面试官。";
    public static String getLogicScore="请针对这段面试对话，对面试者的逻辑沟通能力进行打分，满分100分，只返回一个分数就可以，不要返回其他内容，下面是面试对话记录，其中user是被面试者,assistant是面试官。";
}
