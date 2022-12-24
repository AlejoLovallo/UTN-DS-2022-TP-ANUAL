# Deployment

Before: 

``` mvn clean compile assembly:single ```

### Docker

* Build image from source repository:

````docker build -t dstp-back-i -f deploy/api/Dockerfile .````

* Run container:

```docker run --name dstp-back -p 9000:9000 dstp-back-i```

*************************


