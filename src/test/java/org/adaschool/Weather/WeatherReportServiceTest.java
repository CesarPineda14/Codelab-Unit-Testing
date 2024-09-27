package org.adaschool.Weather;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetWeatherReport() {

        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(80);

        WeatherApiResponse apiResponse = new WeatherApiResponse();
        apiResponse.setMain(main);

        String url = "https://api.openweathermap.org/data/2.5/weather?lat=37.8267&lon=-122.4233&appid=104d22b3936578505592bc898eb899e7";
        when(restTemplate.getForObject(url, WeatherApiResponse.class)).thenReturn(apiResponse);


        WeatherReport weatherReport = weatherReportService.getWeatherReport(37.8267, -122.4233);

        assertEquals(0, weatherReport.getTemperature());
        assertEquals(83, weatherReport.getHumidity());
    }
}
