# Springboot application with admin server and application that uses EhCache
Admin server is only used to display metrics in a nicer, more human readable way. This app is also a service registry. 
It loads all the necessary dependencies for it through spring-boot-admin dependecy. You can read more about it [here](https://github.com/codecentric/spring-boot-admin).

Demo application is a simple app that has only two endpoints, one that serves cached data and one that don't.

```http://localhost:8081/api/demo``` - not cached

```http://localhost:8081/api/demo/cached``` - cached data

For demo purposes I have used [Fake Response API](http://www.fakeresponse.com/) that has a possibility to set the amount of time you would like the response to be delayed which is perfect for testing caching of your API.

Example: ```http://www.fakeresponse.com/api/?sleep=3```

## Starting the apps

If you don't have maven installed, I recommend you to follow this step-by-step [tutorial](https://www.mkyong.com/maven/how-to-install-maven-in-windows/).

Clone the repo and go to ehcache-spring-metrics folder.
If you use IntelliJ, then just start AdminApplication and DemoApplication. Configurations for IntelliJ are already included.
If you want to start it from your terminal, run:
```mvn install```
Then start admin application by running ```mvn spring-boot:run -pl admin``` and open your browser on [http://localhost:8080](http://localhost:8080).
Open a new terminal go to the root of the project and run ```mvn spring-boot:run -pl application```  and open your browser on [http://localhost:8081](http://localhost:8081). You suppose to get Spring's Whitelabel Error page (404), if you didn't, well you did something wrong, just try again.

## Actual usage

Test the endpoint that I have mentioned above. The not-cached endpoint has sleep for 3 seconds as well as cached one when you hit it for the first time, after that endpoint with cached data will return response in a couple of milliseconds.

## EhCache configuration
All of ehcache configuration can be found in ehcache.xml in resource folder.
Statistics are enabled and cache alias for cached data is defined as ``fakeSlowData``. 
One thing left is the annotation that will say what data to cache. Open DemoService class and look for the method ``getCachedData``. It has ``@Cacheable("fakeSlowData")`` annotation that say that data returned from that function will be cached with alias ``fakeSlowData``.

## Metrics
1. In order to see the metrics, first you have to have cached data, so first fire a http request to ``http://localhost:8081/api/demo/cached``.
2. Open admin app and go to the metrics tab.
3. You will see a block with title ``cache`` which contains hit and miss ratio. Hit and miss ratio will update each time you send a request to ``/cached`` endpoint.

Keep in mind that in ehcache.xml configuration cache is has expiry set for 10 seconds, that means that after 10 seconds cache with alias ``fakeSlowData`` will become empty and http request to FakeResponseAPI will be sent again in order to get new data to cache.


