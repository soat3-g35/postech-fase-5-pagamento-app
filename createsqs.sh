#!/bin/bash

export AWS_ACCESS_KEY_ID=localstack
export AWS_SECRET_ACCESS_KEY=localstack

awslocal sqs create-queue --queue-name update-pagamento-status

echo "created table update-pagamento-status"

#awslocal sqs create-queue --queue-name update-pagamento-status
#awslocal sqs list-queues


#awslocal sqs receive-message --queue-url http://localhost:4566/000000000000/update-pagamento-status   
