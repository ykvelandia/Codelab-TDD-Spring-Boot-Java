package org.adaschool.tdd.controller.weather;

import org.adaschool.tdd.repository.document.GeoLocation;
import org.adaschool.tdd.repository.document.WeatherReport;
import org.adaschool.tdd.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class WeatherReportControllerTest {
    private MockMvc mockMvc;

    @Mock
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new WeatherReportController(weatherService)).build();
    }


    @Test
    void testFindById() throws Exception {
        String id = "123";
        GeoLocation geoLocation = new GeoLocation(40.7128, -74.0060);
        when(weatherService.findById(id)).thenReturn(new WeatherReport(geoLocation,25.5,60.0,"yeni",new Date()));

        mockMvc.perform(get("/v1/weather/{id}", id))
                .andExpect(status().isOk());

        verify(weatherService).findById(id);
    }




}