package cn.file.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cn.file.bean.vo.ProblemFeedbackVo;
import cn.file.dao.IFileDao;
	
import cn.file.dao.mapper.FileMapper;

@Repository
public class IFileDaoImpl implements IFileDao{
	@Autowired
	@Qualifier("fileMapper")
	private FileMapper fileMapper;

	@Override
	public int saveProblemFeedback(ProblemFeedbackVo problemFeedbackVo) {
		
		return fileMapper.saveProblemFeedback(problemFeedbackVo);
	}
}