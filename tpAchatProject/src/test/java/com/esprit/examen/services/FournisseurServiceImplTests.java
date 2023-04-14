package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;

import static org.junit.Assert.*;



import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 * Unit tests for FournisseurServiceImpl.
 */

public class FournisseurServiceImplTests {

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @Mock
    private FournisseurRepository fournisseurRepository;
    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;
    @Mock
    private ProduitRepository produitRepository;
    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    private Fournisseur fournisseur;
    private SecteurActivite secteurActivite;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        // Create a Fournisseur
        fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);


        // Create a SecteurActivite
        secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(1L);
        secteurActivite.setLibelleSecteurActivite("Secteur 1");
    }

    @Test
    public void testRetrieveAllFournisseurs() {
        // Mock the repository
        List<Fournisseur> fournisseurs = new ArrayList<>();
        fournisseurs.add(fournisseur);
        doReturn(fournisseurs).when(fournisseurRepository).findAll();

        // Call the service method
        List<Fournisseur> retrievedFournisseurs = fournisseurService.retrieveAllFournisseurs();

        // Verify the repository method was called
        verify(fournisseurRepository).findAll();

        // Assert the retrieved list is not null and contains the expected Fournisseur
        assertThat(retrievedFournisseurs).isNotNull();
        assertThat(retrievedFournisseurs).contains(fournisseur);
    }
    @Test
    public void testAddFournisseur() {
        // Given
        Fournisseur fournisseur = new Fournisseur();
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        fournisseur.setDetailFournisseur(detailFournisseur);
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(fournisseur);

        // When
        Fournisseur result = fournisseurService.addFournisseur(fournisseur);

        // Then
        assertEquals(fournisseur, result);
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }

    @Test
    public void testUpdateFournisseur() {
        // Given
        Fournisseur fournisseur = new Fournisseur();
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        fournisseur.setDetailFournisseur(detailFournisseur);
        when(detailFournisseurRepository.save(any(DetailFournisseur.class))).thenReturn(detailFournisseur);
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(fournisseur);

        // When
        Fournisseur result = fournisseurService.updateFournisseur(fournisseur);

        // Then
        assertEquals(fournisseur, result);
        verify(detailFournisseurRepository, times(1)).save(detailFournisseur);
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }
}

