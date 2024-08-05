package br.com.fiap.pos.soat3.pagamento.infrastructure.integration.messaging.pagamentopendente;

import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PagamentoPendentePublisher {

    private final Logger log = LoggerFactory.getLogger(PagamentoPendentePublisher.class);
    
    @Value("${aws.queueName.pagamentoPendente}")
    private String queueName;
    private final AmazonSQS amazonSQSClient;
    private final ObjectMapper objectMapper;

    public PagamentoPendentePublisher(AmazonSQS amazonSQSClient, ObjectMapper objectMapper) {
        this.amazonSQSClient = amazonSQSClient;
        this.objectMapper = objectMapper;
    }

    public void publishMessage(Pagamento pagamento) {
        try {
            GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(queueName);
            var message = buildMessage(pagamento);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            amazonSQSClient.sendMessage(queueUrl.getQueueUrl(),
                    objectMapper.writeValueAsString(message));
            log.info("Queue Pagamento Pendente publicado: {}", message.getPagamentoId());
        } catch (Exception e) {
            log.error("Queue Exception Message: {}", e.getMessage());
        }
    }

    private PagamentoPendenteMessage buildMessage(Pagamento pagamento){
        PagamentoPendenteMessage message = new PagamentoPendenteMessage();
        message.setPagamentoId(pagamento.getPagamentoId().toString());
        message.setStatus(pagamento.getStatus());
        message.setValor(pagamento.getValor());
        message.setClienteId(pagamento.getClienteId());
        message.setPedidoId(pagamento.getPedidoId());
        return message;
    }
    
}

