package com.compra.compra.service;

import com.compra.compra.entity.EstadoValidacionOTN;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteOTNService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String OTN_URL = "https://tu-otn.example.com/validar";

    public EstadoValidacionOTN validarPaquete(String codigoPaquete) {
        try {
    
            String requestBody = String.format("{\"codigoPaquete\": \"%s\"}", codigoPaquete);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            log.info("Enviando validación a OTN para paquete [{}] -> {}", codigoPaquete, OTN_URL);

            ResponseEntity<String> response = restTemplate.exchange(
                    OTN_URL,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String body = response.getBody().trim().toUpperCase();

                switch (body) {
                    case "APROBADO":
                        log.info("Paquete {} aprobado por OTN", codigoPaquete);
                        return EstadoValidacionOTN.APROBADO;
                    case "RECHAZADO":
                        log.info("Paquete {} rechazado por OTN", codigoPaquete);
                        return EstadoValidacionOTN.RECHAZADO;
                    case "ENPROCESO":
                    case "EN_PROCESO":
                        log.info("Paquete {} en proceso de validación por OTN", codigoPaquete);
                        return EstadoValidacionOTN.EN_PROCESO;
                    default:
                        log.warn("Respuesta desconocida de OTN: {}", body);
                        return EstadoValidacionOTN.EN_PROCESO;
                }
            } else {
                log.error("Error HTTP de OTN: {} para paquete {}", response.getStatusCode(), codigoPaquete);
                return EstadoValidacionOTN.EN_PROCESO;
            }

        } catch (RestClientException ex) {
  
            log.error("Error al comunicarse con OTN para paquete {}: {}", codigoPaquete, ex.getMessage());
            return EstadoValidacionOTN.EN_PROCESO;
        }
    }
}
