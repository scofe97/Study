# https://hub.docker.com/r/bitnami/prometheus

# 1단계 : 네트워크 생성
docker network create prometheus-network --driver bridge

# 2단계 : 네트워크 내에서 Prometheus 컨테이너 실행
docker run -d --name prometheus-node1 --network prometheus-network bitnami/prometheus:latest