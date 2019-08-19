# RestTestEngine
A framework to test RestApi based projects

## Example

Lets start with very small example so you get the idea about RestTestEngine.
RestTestEngine test flow of apis. We will provide a json file which have all the api information and calling sequence for each api.

```
//Json file name: apiFlow.json
{
   "seq":["postStudent","getStudent","getAllStudent"],
   "baseUri":"http://localhost:4567",
   "apiHandler":"com.rf.apis.handlers.DefaultApiHandler",
   "properties":{
      "G":"GET"
    },
    
    "di":{
    	"postStudent":[
    		{
    		"paramName":"id",
                "jsonPath":"id"
    		}
    	]
    },
    
    "apis":{

	"postStudent":{
	  "basePath":"/student",
	  "method":"POST",
	  "body":{
		   "name":"Ravi Narayan",
		   "age": 30,
		   "grade":"22th"
	  },
	  "assertion":{
		  "status":"200"
	  }
        },

	"getStudent":{
	   "basePath":"/student",
	   "method":"GET",
	   "queryParams":{
		"id":"{{id}}"
	   }
	 },

         "getAllStudent":{
	   "basePath":"/student",
	   "method":"GET"
	  }
      }
}	
```

Now call this api flow using RestTestEngin. Thats it!

```java
 RestEngine.start("apiFlow.json");
```
