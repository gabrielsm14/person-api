package br.com.empresa.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // criar esse campo do not null (obrigatorio)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String cpf;

    private LocalDate birthDate;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}) // uma pessoa tem muitos telefones. Vai ser criado uma tabela intermediaria onde a pessoa vai se relacionar com uma tabela chamada de person_phone, telefone irá se relacionar com essa tabela intermediaria onde nessa tabela vai ter somente dois dados id do telefone
   // Lazy é utilizado para a perfomace
    // cascade
    private List<Phone> phones;

}
