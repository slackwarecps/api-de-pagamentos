package br.com.fabioalvaro.pagamento.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.fabioalvaro.pagamento.dominio.Pagamento;

@Service
public class NotificationService {

    private final RestTemplate restTemplate;

    public NotificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public ResponseEntity<String> sendNotification(String url_recebida, Pagamento pagamento)
            throws JsonProcessingException {

        String urlx = "https://open-bingo.wiremockapi.cloud/pagbank/notificacao";

        // Criar o payload
        Map<String, Object> payload = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("transacaoId", pagamento.getTransacaoId());
        data.put("status", pagamento.getStatus());

        payload.put("data", data);

        // Criar os headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Criar a entidade HTTP

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, headers);

        // Fazer a chamada POST
        ResponseEntity<String> response = restTemplate.exchange(url_recebida,
                HttpMethod.POST, requestEntity, String.class);

        return response;
    }
}