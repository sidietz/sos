package de.oberamsystems.sos.mqtt;

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
public class MeasurementServiceTest {

    @Mock
    private MeasurementRepository repository;

    @InjectMocks
    private MeasurementService service;

    @Test
    public void testSaveMeasurement() {
        Measurement m = new Measurement();
        when(repository.save(m)).thenReturn(m);
        Measurement saved = service.saveMeasurement(m);
        assertEquals(m, saved);
    }

    @Test
    public void testGetAllMeasurements() {
        List<Measurement> list = Arrays.asList(new Measurement(), new Measurement());
        when(repository.findAll()).thenReturn(list);
        assertEquals(2, service.getAllMeasurements().size());
    }

    @Test
    public void testGetMeasurementById() {
        Measurement m = new Measurement();
        when(repository.findById(1)).thenReturn(m);
        assertEquals(m, service.getMeasurementById(1));
    }

    @Test
    public void testDeleteMeasurementById() {
        service.deleteMeasurementById(1);
        verify(repository).deleteById(1);
    }
}
