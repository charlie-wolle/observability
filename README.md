# Introduction 
The scope of this project is to evaluate an open sourch tech stack to observe applications regarding logs, metrics and traces.
To achieve a real world scenario, a spring boot backend with an nextJS frontend is used as a demo application.

The following tools are used:

- [Prometheus](https://prometheus.io/)
- [Grafana](https://grafana.com/)
- [Promtail](https://grafana.com/docs/loki/latest/clients/promtail/)
- [Loki](https://grafana.com/oss/loki/)
- [Tempo](https://grafana.com/oss/tempo/)

# Getting Started
The whole stack is containerized with docker and runnable out of the box.

# Build and Test
### Build spring boot backend
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