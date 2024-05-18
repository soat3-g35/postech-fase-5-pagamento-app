package br.com.fiap.pos.soat3.pagamento.infrastructure.integration.messaging;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UpdatePagamentoStatusPublisher {

    private final Logger log = LoggerFactory.getLogger(UpdatePagamentoStatusPublisher.class);
    
    @Value("${aws.queueName}")
    private String queueName;

    private final AmazonSQS amazonSQSClient;
    private final ObjectMapper objectMapper;

    public UpdatePagamentoStatusPublisher(AmazonSQS amazonSQSClient, ObjectMapper objectMapper) {
        this.amazonSQSClient = amazonSQSClient;
        this.objectMapper = objectMapper;
    }

    public void publishMessage(String pedidoId, String status) {
        try {
            GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(queueName);
            var message = new PagamentoStatusMessage(pedidoId, status);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            amazonSQSClient.sendMessage(queueUrl.getQueueUrl(),
                    objectMapper.writeValueAsString(message));
        } catch (Exception e) {
            log.error("Queue Exception Message: {}", e.getMessage());
        }

    }
}

