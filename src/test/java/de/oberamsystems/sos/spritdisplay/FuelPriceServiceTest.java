package de.oberamsystems.sos.spritdisplay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FuelPriceServiceTest {

    @Mock
    private FuelPriceRepository repository;

    @InjectMocks
    private FuelPriceService service;

    @Test
    public void testSaveFuelPrice() {
        FuelPrice p = new FuelPrice();
        when(repository.save(p)).thenReturn(p);
        FuelPrice saved = service.saveFuelPrice(p);
        assertEquals(p, saved);
    }

    @Test
    public void testGetAllFuelPrices() {
        List<FuelPrice> list = Arrays.asList(new FuelPrice(), new FuelPrice());
        when(repository.findAll()).thenReturn(list);
        assertEquals(2, service.getAllFuelPrices().size());
    }

    @Test
    public void testGetFuelPriceById() {
        FuelPrice p = new FuelPrice();
        when(repository.findById(1)).thenReturn(p);
        assertEquals(p, service.getFuelPriceById(1));
    }

    @Test
    public void testDeleteFuelPriceById() {
        service.deleteFuelPriceById(1);
        verify(repository).deleteById(1);
    }
}
