import com.universite.model.FraisScolarite;
import com.universite.service.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GestionFraisServiceTest {

    private Statistics gestionFraisService;
    private Etudiant etudiant1;
    private Etudiant etudiant2;

    @BeforeEach
    void setUp() {
        gestionFraisService = new com.universite.service.Statistics();

        etudiant1 = new Etudiant("STD22000", "Dupont", "Jean", "L2", "M1");
        etudiant2 = new Etudiant("STD20000", "Martin", "Marie", "L3", "M5");

        gestionFraisService.ajouterEtudiant(etudiant1);
        gestionFraisService.ajouterEtudiant(etudiant2);
        
        FraisScolarite frais3 = new FraisScolarite(2, "STD20000", 600.0,
                LocalDate.now().minusDays(5, false);

        gestionFraisService.ajouterFrais(frais3);
    }

    @Test
    void testGetFraisParEtudiant() {
        List<FraisScolarite> fraisEtudiant1 = gestionFraisService.getFee(1);

        assertEquals(2, fraisEtudiant1.size());
    }

    @Test
    void testGetTotalFraisEtudiant() {
        double total = gestionFraisService.getTotalFraisEtudiant(1);

        assertEquals(600.0, total, 0.001);
    }

    @Test
    void testGetMissingFees() {
        List<FraisScolarite> fraisNonPayes = gestionFraisService.getFraisNonPayes();

        assertEquals(2, fraisNonPayes.size());
        assertFalse(fraisNonPayes.get(0).isPaye());
    }

    @Test
    void testGetTotalMissingFees() {
        double totalNonPayes = gestionFraisService.getTotalFraisNonPayes();

        assertEquals(1100.0, totalNonPayes, 0.001);
    }

    @Test
    void testGetEtudiantParNumero() {
        var etudiant = gestionFraisService.getEtudiantParNumero("STD20000");

        assertTrue(etudiant.isPresent());
        assertEquals("Dupont", etudiant.getNom());
    }

    @Test
    void testGetEtudiantParId() {
        var etudiant = gestionFraisService.getEtudiantParId(1);

        assertTrue(etudiant.isPresent());
        assertEquals("STD20000", etudiant.get().getNumeroEtudiant());
    }

    @Test
    void testpaye() {
        gestionFraisService.marquerCommePaye(1);

        var frais = gestionFraisService.getFraisParEtudiant(1).get(0);
        assertTrue(frais.isPaye());
    }

    @Test
    void testGetEtudiantsAvecFraisEnRetard() {
        List<Etudiant> etudiantsEnRetard = gestionFraisService.getEtudiantsAvecFraisEnRetard();

        assertEquals(2, etudiantsEnRetard.size());
        assertTrue(etudiantsEnRetard.stream()
                .anyMatch(e -> e.getNom().equals("Dupont")));
    }

    @Test
    void testGetEtudiantsParAnnee() {
        List<Etudiant> etudiantsAnnee1 = (List<Etudiant>) gestionFraisService.getEtudiantsParAnnee(1);

        assertEquals(1, etudiantsAnnee1.size());
        assertEquals("Dupont", etudiantsAnnee1.get(0).getNom());
    }

}
