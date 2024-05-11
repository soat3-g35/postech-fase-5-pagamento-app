#!/bin/bash

awslocal dynamodb --endpoint-url=http://localhost:4566 create-table \
    --table-name pagamento-service-table \
    --attribute-definitions \
        AttributeName=id,AttributeType=S \
    --key-schema \
        AttributeName=id,KeyType=HASH \
--provisioned-throughput \
        ReadCapacityUnits=5,WriteCapacityUnits=5 >> output

echo "created table pagamento-service-table"
rm output
