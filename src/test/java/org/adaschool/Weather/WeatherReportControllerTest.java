package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WeatherReportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherReportController).build();
    }

    @Test
    public void testGetWeatherReport() throws Exception {
        WeatherReport weatherReport = new WeatherReport();
        weatherReport.setTemperature(25.0);
        weatherReport.setHumidity(80);

        when(weatherReportService.getWeatherReport(37.8267, -122.4233)).thenReturn(weatherReport);

        mockMvc.perform(get("/v1/api/weather-report")
                        .param("latitude", "37.8267")
                        .param("longitude", "-122.4233")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature").value(25.0))
                .andExpect(jsonPath("$.humidity").value(80));
    }
}
