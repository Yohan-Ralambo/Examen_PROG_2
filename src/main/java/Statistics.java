package com.universite.service;

import com.universite.model.Etudiant;
import com.universite.model.FraisScolarite;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Statistics extends Etudiant{
    private List<Etudiant> etudiants;
    private List<FraisScolarite> fraisScolarites;

    public Statistics() {
        this.etudiants = new ArrayList<>();
        this.fraisScolarites = new ArrayList<>();
    }

    public List<FraisScolarite> getFee(int etudiantId) {
        return fraisScolarites.stream()
                .filter(frais -> frais.getEtudiantId().equals(etudiantId))
                .collect(Collectors.toList());
    }

    public double getTotalFraisEtudiant(int etudiantId) {
        return fraisScolarites.stream()
                .filter(frais -> frais.getEtudiantId().equals(etudiantId))
                .mapToDouble(FraisScolarite::getMontant)
                .sum();
    }


    public List<FraisScolarite> getFraisNonPayes() {
        return fraisScolarites.stream()
                .filter(frais -> !frais.isPaye())
                .collect(Collectors.toList());

    }

    public double getTotalFraisNonPayes() {
        return fraisScolarites.stream()
                .filter(frais -> !frais.isPaye())
                .mapToDouble(FraisScolarite::getMontant)
                .sum();
    }

    public void marquerCommePaye(int fraisId) {
        fraisScolarites.stream()
                .filter(frais -> frais.getId().equals(fraisId))
                .findFirst()
                .ifPresent(frais -> frais.setPaye(true));
    }

    public List<Etudiant> getEtudiantsAvecFraisEnRetard() {
        return etudiants.stream()
                .filter(etudiant -> fraisScolarites.stream()
                        .anyMatch(frais -> frais.getEtudiantId().equals(etudiant.getId()) &&
                                !frais.isPaye()))
                .collect(Collectors.toList());
    }

    public List<Etudiant> getEtudiantsParAnnee(int annee) {
        return etudiants.stream()
                .filter(etudiant -> etudiant.getAnneeEtude() == annee)
                .collect(Collectors.toList());
    }

    public void ajouterFrais(FraisScolarite frais) {
        fraisScolarites.add(frais);
    }

    public void ajouterEtudiant(Etudiant etudiant1, Etudiant etudiant2) {
        etudiants.add(etudiant1);
        etudiants.add(etudiant2);
    }
}
