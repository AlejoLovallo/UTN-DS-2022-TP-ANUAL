# Deployment

### Docker

* Build image from source repository:

````docker build -t dstp-back-i -f deploy/api/Dockerfile .````

* Run container:

```docker run --name dstp-back -p 4567:4567 dstp-back-i```

### Nginx


*************************


