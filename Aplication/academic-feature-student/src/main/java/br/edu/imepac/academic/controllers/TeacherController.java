package br.edu.imepac.academic.controllers;

import br.edu.imepac.academic.dtos.student.TeacherRequestDto;
import br.edu.imepac.academic.dtos.student.TeacherResponseDto;
import br.edu.imepac.academic.model.TeacherModel;
import br.edu.imepac.academic.services.TeacherService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import br.edu.imepac.academic.services.TeacherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) { this.teacherService = teacherService;}

    @PostMapping
    public HttpEntity<?> addTeacher(@RequestBody TeacherRequestDto teacherRequestDto) {
        try {
            this.validadeFieldsRequired(teacherRequestDto);

            TeacherModel teacherModel = teacherService.add(teacherRequestDto);

            TeacherResponseDto teacherResponseDto = new TeacherResponseDto();
            teacherResponseDto.setId(teacherModel.getId());
            teacherResponseDto.setNome(teacherModel.getNome());
            teacherResponseDto.setMateria(teacherModel.getMateria());

            return ResponseEntity.status(HttpStatus.CREATED).body(teacherResponseDto);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo de errado não está certo:" + exception.getMessage());
        }
    }

    private void validadeFieldsRequired(TeacherRequestDto teacherRequestDto) throws Exception {
        List<String> fieldsRequired = new ArrayList<>();

        if (teacherRequestDto.getNome().isBlank()) {
            fieldsRequired.add("nome");
        }
        if (teacherRequestDto.getMateria().isBlank()) {
            fieldsRequired.add("materia");
        }

        if (!fieldsRequired.isEmpty()) {
            throw new Exception("Campos obrigatórios: " + String.join(", ", fieldsRequired));
        }
    }

    @GetMapping("{id}")
    public HttpEntity<?> getTeacher(@PathVariable Long id) throws Exception {
        this.validateIdRequired(id);

        TeacherModel teacherModel = teacherService.getById(id);

        if (teacherModel != null) {

            TeacherResponseDto teacherResponseDto = new TeacherResponseDto();
            teacherResponseDto.setId(teacherModel.getId());
            teacherResponseDto.setNome(teacherModel.getNome());
            teacherResponseDto.setMateria(teacherModel.getMateria());

            return ResponseEntity.status(HttpStatus.OK).body(teacherResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado!");
        }
    }

    private void validateIdRequired(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Campo id obrigatórios");
        }
    }
}
