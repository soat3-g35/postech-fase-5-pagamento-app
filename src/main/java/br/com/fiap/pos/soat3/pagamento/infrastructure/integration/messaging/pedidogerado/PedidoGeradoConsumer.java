package br.com.fiap.pos.soat3.pagamento.infrastructure.integration.messaging.pedidogerado;

import br.com.fiap.pos.soat3.pagamento.application.usecases.pagamento.RealizaPagamentoInteractor;
import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoGeradoConsumer {

    private final Logger log = LoggerFactory.getLogger(PedidoGeradoConsumer.class);

    @Value("${aws.queueName.pedidoGerado}")
    private String queueName;

    @Autowired
    private final AmazonSQS amazonSQSClient;

    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    private final RealizaPagamentoInteractor realizaPagamentoInteractor;

    public PedidoGeradoConsumer(AmazonSQS amazonSQSClient, ObjectMapper objectMapper, RealizaPagamentoInteractor realizaPagamentoInteractor) {
        this.amazonSQSClient = amazonSQSClient;
        this.objectMapper = objectMapper;
        this.realizaPagamentoInteractor = realizaPagamentoInteractor;
    }

    @Scheduled(fixedDelay = 500) //
    @Transactional// It runs every 5 seconds.
    public void consumeMessages() {

        try {
            String queueUrl = amazonSQSClient.getQueueUrl(queueName).getQueueUrl();

            ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl);

            if (!receiveMessageResult.getMessages().isEmpty()) {
                log.info("SAGA 3: Consome pedido gerado");
                com.amazonaws.services.sqs.model.Message message = receiveMessageResult.getMessages().get(0);
                log.info("Pagamento: Read Message from queue: {}", message.getBody());
                PedidoGeradoMessage pedidoGeradoMessage = objectMapper.readValue(message.getBody(), PedidoGeradoMessage.class);

                realizaPagamentoInteractor.realizaPagamento(buildPagamento(pedidoGeradoMessage));
                amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
            }

        } catch (Exception e) {
            log.error("Queue Exception Message: {}", e.getMessage());
        }
    }

    private Pagamento buildPagamento(PedidoGeradoMessage message){
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(message.getTotalPedido());
        pagamento.setPedidoId(message.getPedidoId());
        pagamento.setClienteId(message.getClienteId());
        return pagamento;
    }
}
