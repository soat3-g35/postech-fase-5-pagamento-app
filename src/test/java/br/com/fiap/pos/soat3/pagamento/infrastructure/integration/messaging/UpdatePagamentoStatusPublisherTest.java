package br.com.fiap.pos.soat3.pagamento.infrastructure.integration.messaging;

import br.com.fiap.pos.soat3.pagamento.infrastructure.config.messaging.SQSConfig;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { SQSConfig.class })
@TestPropertySource(properties = { "aws.queueName=update-pagamento-queue", "aws.endpoint=update-pagamento-queue", "aws.region=us-east-1" })
class UpdatePagamentoStatusPublisherTest { 
    
//    @InjectMocks
//    UpdatePagamentoStatusPublisher updatePagamentoStatusPublisher;

    @Mock
    ObjectMapper objectMapper;
    
    @Mock
    AmazonSQS amazonSQSClient;

//    @BeforeEach
//    void initService() {
//        amazonSQSClient = mock(AmazonSQS.class);
//        updatePagamentoStatusPublisher = new UpdatePagamentoStatusPublisher(amazonSQSClient, objectMapper);
//    }
    
//    @Test
//    void checkPublishMessage() throws JsonProcessingException {
//
//        Long pedidoId = 1L;
//        PagamentoStatusMessage message = new PagamentoStatusMessage(String.valueOf(pedidoId), "RECEBIDO");
//
//        GetQueueUrlResult queueUrl =  new GetQueueUrlResult();
//        queueUrl.setQueueUrl("update-pagamento-queue");
//
//        when(amazonSQSClient.getQueueUrl((GetQueueUrlRequest) null)).thenReturn(queueUrl);
//        
//        updatePagamentoStatusPublisher.publishMessage(String.valueOf(pedidoId), "RECEBIDO");       
//
//        verify(amazonSQSClient, times(1)).sendMessage(queueUrl.getQueueUrl(), objectMapper.writeValueAsString(message));
//
//    }

//    @Test(expected = Exception.class)
//    void testIfFaultWhenPublishMessage() {
//        Long pedidoId = 1L;
//        updatePagamentoStatusPublisher.publishMessage(String.valueOf(pedidoId), "RECEBIDO"));
////        assertEquals("Queue Exception Message: {}", exception.getMessage());
//    }
    
}
