package cn.file.dao.mapper;

import org.springframework.stereotype.Repository;

import cn.file.bean.vo.ProblemFeedbackVo;

@Repository
public interface FileMapper {

	public int saveProblemFeedback(ProblemFeedbackVo problemFeedbackVo);

}
