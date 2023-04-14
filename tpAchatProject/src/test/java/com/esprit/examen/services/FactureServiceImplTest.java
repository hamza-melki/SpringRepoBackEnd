package com.esprit.examen.services;

import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.DetailFactureRepository;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ProduitRepository;

import static org.junit.jupiter.api.Assertions.*;

class FactureServiceImplTest {

    @InjectMocks
    private FactureServiceImpl factureService;

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private DetailFactureRepository detailFactureRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddFacture() {
        // Arrange
        Facture facture = new Facture();
        when(factureRepository.save(any(Facture.class))).thenReturn(facture);

        // Act
        Facture result = factureService.addFacture(facture);

        // Assert
        assertNotNull(result);
        assertEquals(facture, result);
        verify(factureRepository, times(1)).save(any(Facture.class));
    }


    @Test
    void testCancelFacture() {
        // Arrange
        Facture facture = new Facture();
        facture.setIdFacture(1L);
        when(factureRepository.findById(anyLong())).thenReturn(java.util.Optional.of(facture));
        when(factureRepository.save(any(Facture.class))).thenReturn(facture);

        // Act
        factureService.cancelFacture(1L);

        // Assert
        assertTrue(facture.getArchivee());
        verify(factureRepository, times(1)).findById(anyLong());
        verify(factureRepository, times(1)).save(any(Facture.class));
    }

}