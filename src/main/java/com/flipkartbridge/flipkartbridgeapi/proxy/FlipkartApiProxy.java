package com.flipkartbridge.flipkartbridgeapi.proxy;

import com.flipkartbridge.flipkartbridgeapi.config.FlipkartProxyProperties;
import com.flipkartbridge.flipkartbridgeapi.exception.ProcessingException;
import com.flipkartbridge.flipkartbridgeapi.exception.StatusCode;
import com.flipkartbridge.flipkartbridgeapi.model.ShipmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FlipkartApiProxy {
    private final AccessTokenGenerator accessTokenGenerator;
    @Qualifier("restTemplateForShipmentApi")
    private final RestTemplate restTemplate;
    private final FlipkartProxyProperties flipkartProxyProperties;

    Predicate<ResponseEntity> isSuccessPredicate = responseEntity ->
            Optional.of(responseEntity)
                    .map(ResponseEntity::getStatusCode)
                    .filter(HttpStatus::is2xxSuccessful)
                    .isPresent();

    public ShipmentResponse getShipmentsByOrderId(String orderId) {
        try {
            HttpHeaders headers = headersUsingJson();
            String url = flipkartProxyProperties.getHost()
                    + String.format(flipkartProxyProperties.getShipmentByOrderIdPath(), orderId);
            ResponseEntity<ShipmentResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                    new HttpEntity<>(headers),
                    ShipmentResponse.class);
            Optional.of(responseEntity)
                    .filter(isSuccessPredicate)
                    .map(ResponseEntity::getBody)
                    .orElseThrow(() ->  ProcessingException.newBuilder(StatusCode.FAILURE)
                            .setMessage("Failed to call " + url)
                            .setHttpStatus(responseEntity.getStatusCode())
                            .build());

        } catch (Exception e) {
            throw  ProcessingException.newBuilder(StatusCode.FAILURE)
                    .setMessage(e.getMessage())
                    .setCause(e)
                    .build();
        }
        return null;
    }

    private HttpHeaders headersUsingJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String token = accessTokenGenerator.getClientCredentialsAccessToken();
        headers.setBearerAuth(token);
        return headers;
    }
}
