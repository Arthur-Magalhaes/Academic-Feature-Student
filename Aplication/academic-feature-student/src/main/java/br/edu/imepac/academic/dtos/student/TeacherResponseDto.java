package br.edu.imepac.academic.dtos.student;

import lombok.Data;

@Data
public class TeacherResponseDto {
    private Long id;
    private String nome;
    private String materia;
}
