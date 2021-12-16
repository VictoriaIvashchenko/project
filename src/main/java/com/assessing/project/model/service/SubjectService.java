package com.assessing.project.model.service;

import com.assessing.project.model.entity.Mark;
import com.assessing.project.model.entity.Subject;
import com.assessing.project.model.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    public String findSubjectNameByMark(Mark mark){return subjectRepository.customFindSubjectByMark(mark);}
    public String findTestTypeByMark(Mark mark){return subjectRepository.customFindTestTypeByMark(mark);}
}
