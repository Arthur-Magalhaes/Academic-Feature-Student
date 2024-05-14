package br.edu.imepac.academic.services;

import br.edu.imepac.academic.dtos.student.TeacherRequestDto;
import br.edu.imepac.academic.model.TeacherModel;
import br.edu.imepac.academic.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {this.teacherRepository = teacherRepository;}

    public TeacherModel add(TeacherRequestDto teacherRequestDto) {
        TeacherModel teacherModel = new TeacherModel();
        teacherModel.setNome(teacherRequestDto.getNome());
        teacherModel.setMateria(teacherRequestDto.getMateria());

        return teacherRepository.save(teacherModel);
    }

    public TeacherModel getById(Long id) { return teacherRepository.findById(id).orElse(null);}
}
