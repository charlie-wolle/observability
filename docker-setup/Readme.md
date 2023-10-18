# Docker Setup Observability Stack


## Preparation
### Build OBS backend
#### Install Dependencies and build JAR
```cd ../observability && ./mvnw package && cd ../docker-setup```
#### Build Docker Image
```docker build -t tribe.ccc.tt/observability ../observability```

### Build nextJS frontend
#### Install node_modules
```cd ../frontend && npm install && cd ../docker-setup```
#### Build Frontend Docker Image
```docker build -t tribe.ccc.tt/nextjs ../frontend```

## Build test env
```docker-compose up```
or detached mode (container in background):
```docker-compose up -d```

## Stop test env
```docker container stop $(docker container ls -q --filter name=docker-setup*)```

## Start test env
```docker container start $(docker container ls -a -q --filter name=docker-setup*)```

## Clean test env
```docker-compose down```

## Grafana UI
```http://localhost:3000/```

## Prometheus UI
```http://localhost:9090/```

## Obs Backend
```http://localhost:8080/users```