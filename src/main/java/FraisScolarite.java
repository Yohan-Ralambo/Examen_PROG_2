package com.universite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FraisScolarite {
    private int id;
    private int etudiantId;
    private double montant;
    private LocalDate dateEcheance;
    private boolean paye;
}


