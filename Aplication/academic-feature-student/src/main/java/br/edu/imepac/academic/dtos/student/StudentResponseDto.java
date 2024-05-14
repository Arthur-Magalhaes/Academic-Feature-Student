package br.edu.imepac.academic.dtos.student;

import lombok.Data;

@Data
public class StudentResponseDto {
    private Long id;
    private String nome;
    private String matricula;

}
