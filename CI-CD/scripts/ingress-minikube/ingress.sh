# minikube 가상환경에서 실행
minikube start --driver=hyperv

# 테스트용 Deployment 2개 배포
kubectl create deployment in-hname-pod --image=sysnet4admin/echo-hname
kubectl create deployment in-ip-pod --image=sysnet4admin/echo-ip

# NGINX Ingress 컨트롤러 설치
kubectl apply -f ~/_Book_k8sInfra/ch3/3.3.2/ingress-nginx.yaml

