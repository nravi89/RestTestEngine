# RestTestEngine
A framework to test RestApi based projects

## Example

Lets start with very small example so you get the idea about RestTestEngine.
RestTestEngine test flow of apis. We will provide a json file which have all the api information and calling sequence for each api.

```
//Json file name: apiFlow.json
{
   "seq":["api1","api2"],
   "baseUri":"http://localhost:8181",
    "di":{
       "api1":[
             {
              "paramName":"name",
              "jsonPath":"name"
             }
        ]
    },
	"apis":{
		
		"api1":{
		  "basePath":"SpringExamples/get",
		  "method":"GET"
		 },
		 
		"api2":{
		  "basePath":"SpringExamples/get",
		  "method":"GET",
		  "queryParams":{
		        "name":"{{name}}"
		   }
		 }
	}
}	
```

Now call this api flow using RestTestEngin. Thats it!

```java
 RestEngine.start("apiFlow.json");
```
