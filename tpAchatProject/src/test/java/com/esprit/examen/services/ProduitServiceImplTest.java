package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.CategorieProduitRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;


public class ProduitServiceImplTest {

    @Mock
    private ProduitRepository produitRepository;


    @InjectMocks
    private ProduitServiceImpl produitService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllProduits() {
        // Given
        Produit produit1 = new Produit();
        Produit produit2 = new Produit();
        List<Produit> produits = new ArrayList<>();
        produits.add(produit1);
        produits.add(produit2);

        when(produitRepository.findAll()).thenReturn(produits);

        // When
        List<Produit> result = produitService.retrieveAllProduits();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testAddProduit() {
        // Given
        Produit produit = new Produit();
        produit.setLibelleProduit("Produit 1");

        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        // When
        Produit result = produitService.addProduit(produit);

        // Then
        assertNotNull(result);
        assertEquals("Produit 1", result.getLibelleProduit());
    }

    @Test
    public void testDeleteProduit() {
        // Given
        Long produitId = 1L;

        // When
        produitService.deleteProduit(produitId);

        // Then
        verify(produitRepository, times(1)).deleteById(produitId);
    }

    @Test
    public void testUpdateProduit() {
        // Given
        Produit produit = new Produit();
        produit.setLibelleProduit("Produit 1");

        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        // When
        Produit result = produitService.updateProduit(produit);

        // Then
        assertNotNull(result);
        assertEquals("Produit 1", result.getLibelleProduit());
    }

}

