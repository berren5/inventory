## SpringBoot REST Application

### API url

#### http://localhost:8080/command
#### This is the url used for all the services. Request method is POST. Set content-type to application/json while making a request.

### Requests

1. Create inventory item:
	{ "command": "create Books01 10.50 13.79"}

2. Delete inventory item:
	{ "command": "delete Books2" }

3. UpdateBuy inventory item:
	{ "command" : "updateBuy Books3 100" }

4. UpdateSell inventory item:
	{ "command" : "updateSell Books3 50" }

5. Generate report
	{ "command": "report" }

Each request has response that changes the data stored. Data is not stored in any database so whenever application is restarted you have to create the items again. Report command generates the report in json format. I have not used any template. You can also run test case that verifies the output of sample provided in the question.
	
	



