package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;

import java.util.*;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.DetailFacture;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;

@SpringBootTest
public class ProduitServiceTestMockito {

    @Mock
    ProduitRepository prodRepository;
    @InjectMocks
    ProduitServiceImpl prodService;

    @Test
    public void testUpdateProduit()
    {

        Date dateFormat = new Date("dd/MM/yyyy");
        Stock stock = new Stock();
        CategorieProduit categorieProduit = new CategorieProduit();
        Set<DetailFacture> detailFactures = new Set<DetailFacture>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<DetailFacture> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(DetailFacture detailFacture) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends DetailFacture> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        Produit produit = new Produit(null, "code", "libelleProduit", 3, dateFormat, dateFormat, stock, detailFactures, categorieProduit );



        Mockito.when(prodRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produit));
        produit.setLibelleProduit("test update");
        produit.setPrix(200);
        Mockito.when(prodRepository.save(produit)).thenReturn(produit);


    }

}
