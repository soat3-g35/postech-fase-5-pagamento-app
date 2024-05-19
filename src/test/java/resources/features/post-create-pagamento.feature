Feature: Post Pagamento For Payment Creation 

  Scenario Outline: Client make call to post payment data
    When client post data pagamento
    Then the post client receives status code of 200

  Scenario Outline: Client make call to patch to confirm payment
    When client patch payment confirmation of pagamentoId "b13254f7-661a-4728-9ed0-864f7b7d5239" and of pedidoId "1"
    Then the patch client receives status code of 200
