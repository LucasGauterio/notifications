curl --location --request POST 'http://localhost:8081/notification/publish' \
--header 'Content-Type: application/json' \
--data-raw '{
  "notification" : {
    "sender_id" : "12345",
    "recipient_id" : "654789",
    "context" : {
      "company":"cvc",
      "brand":"cvc",
      "channel":"web",
      "site":"bra"
    },
    "type" : "transactional",
    "template" : "billed",
    "data" : {
      "orderId":"12345678",
      "payer":"Lucas da Silva Gautério",
      "payment": [
        {
          "type":"credit",
          "card_brand":"VISA",
          "installment": {
            "entry": 100,
            "quantity":"2",
            "value":"450"
          },
          "total_value":"1000"
        }
      ]
    },
    "attachments": [{
      "type": "application/pdf",
      "name": "receipt-102011111111",
      "content": "file encoded base64"
    }]
  }
}'
echo ""