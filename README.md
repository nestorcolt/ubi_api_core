
How to run

```
gradle microBundle
docker-compose up --build
```

Then access


HealthAPI
```
http://localhost:8080/health
```


OpenAPI
```
http://localhost:8080/openapi
```


Swagger UI
```
http://localhost:3000/?url=http://localhost:8080/openapi
```
