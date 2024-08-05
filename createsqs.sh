#!/bin/bash

export AWS_ACCESS_KEY_ID=localstack
export AWS_SECRET_ACCESS_KEY=localstack

awslocal sqs create-queue --queue-name pedido-gerado

echo "created table pedido-gerado"

awslocal sqs create-queue --queue-name pagamento-pendente

echo "created table pagamento-pendente"

awslocal sqs create-queue --queue-name pagamento-confirmado

echo "created table pagamento-confirmado"

#awslocal sqs create-queue --queue-name pedido-gerado
#awslocal sqs list-queues


#awslocal sqs receive-message --queue-url http://localhost:4566/000000000000/pedido-gerado
