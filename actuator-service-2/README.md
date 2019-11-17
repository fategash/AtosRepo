CURL FOR TESTING CREATE TRANSACTION

curl --header "Content-Type: application/json" --request POST  --data {\"account_iban\":1234567890,\"amount\":4,\"date\":\"2019-07-16T16:55:42.000Z\"} localhost:8080/create

CURL FOR TESTING SEARCHING

curl localhost:8080/search?account=1234567890