# RestTestEngine
A framework to test RestApi based projects

## Example

Let's start with a very small example.

For the RestTestEngine test flow, we'll provide a JSON file that contains:

- All API details (endpoint, method, headers, payload, etc.)
- The execution sequence of the APIs
- Any dependencies between APIs (if applicable)

The engine will read the JSON file and execute the APIs in the specified order, making it easy to manage and update test flows without changing the code.
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

Now We can call this api flow using RestTestEngin. Thats it!

```java
 RestEngine.start("apiFlow.json");
```
