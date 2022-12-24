# Deployment

### Docker

* Build image from source repository:

````docker build -t ds-front -f deploy/front/Dockerfile .````

* Run container:

```dockerfile
docker run -it -p 8080:80 ds-front
```

*************************


