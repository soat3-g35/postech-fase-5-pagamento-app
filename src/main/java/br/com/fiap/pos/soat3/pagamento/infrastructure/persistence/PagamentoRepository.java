package br.com.fiap.pos.soat3.pagamento.infrastructure.persistence;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PagamentoRepository{
    
    private final DynamoDBMapper dynamoDBMapper;

    public PagamentoRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public String criaPagamento(PagamentoEntity pagamentoEntity){
        dynamoDBMapper.save(pagamentoEntity);
        return pagamentoEntity.getPagamentoId();
    }
    
    public PagamentoEntity getPagamentoById(String pagamentoId){
        return dynamoDBMapper.load(PagamentoEntity.class, pagamentoId);
    }
    
    public PagamentoEntity atualizaPagamento(String pagamentoId, PagamentoEntity pagamentoEntity){
        PagamentoEntity pagamentoEntityLoaded = dynamoDBMapper.load(PagamentoEntity.class, pagamentoId);

        pagamentoEntityLoaded.setPedidoId(pagamentoEntity.getPedidoId());
        pagamentoEntityLoaded.setClienteId(pagamentoEntity.getClienteId());
//        pagamentoEntityLoaded.setDataDeCriacao(pagamentoEntity.getDataDeCriacao());
        pagamentoEntityLoaded.setValor(pagamentoEntity.getValor());
//        pagamentoEntityLoaded.setStatus(pagamentoEntity.getStatus());
        pagamentoEntityLoaded.setWebhook(pagamentoEntity.getWebhook());
        dynamoDBMapper.save(pagamentoEntityLoaded);
        
        return dynamoDBMapper.load(PagamentoEntity.class, pagamentoId);
    }

    public PagamentoEntity atualizaStatus(String pagamentoId, String newStatus){
        PagamentoEntity pagamentoEntityLoaded = dynamoDBMapper.load(PagamentoEntity.class, pagamentoId);
        
        pagamentoEntityLoaded.setStatus(newStatus);
        dynamoDBMapper.save(pagamentoEntityLoaded);

        return dynamoDBMapper.load(PagamentoEntity.class, pagamentoId);
    }
    
}
