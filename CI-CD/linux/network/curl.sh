# 파일 다운로드
curl -O http://apache.mirror.cdnetworks.com/oozie/4.3.1/oozie-4.3.1.tar.gz
# 파일 이름을 지정하여 다운로드
curl -o oozie.tar.gz http://apache.mirror.cdnetworks.com/oozie/4.3.1/oozie-4.3.1.tar.gz

# -s 옵션 사용하면 전송 속도와 같은 부가 정보 안나타남
curl -so oozie.tar.gz http://apache.mirror.cdnetworks.com/oozie/4.3.1/oozie-4.3.1.tar.gz

curl -O http://3.36.96.3:8091/api/machine/1/sensor
