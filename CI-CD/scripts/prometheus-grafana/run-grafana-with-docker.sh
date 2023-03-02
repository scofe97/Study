# https://hub.docker.com/r/grafana/grafana

# 기본실행
# 기본 관리 사용자 자격 증명은 admin/admin
docker run -d --name=grafana -p 3000:3000 grafana/grafana

# 프로메테우스 네트워크 연결
docker network create prometheus-network --driver bridge

docker run -d --name=grafana -p 3000:3000 --network prometheus-network grafana/grafana