package com.universite.model
import lombok.*;

import java.time.LocalDate;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Etudiant extends Groupe {
    private String id;
    private String nom;
    private String prenom;
    private String anneeEtude;
    private Groupe groupe;
    private LocalDate dateEntree;
}
