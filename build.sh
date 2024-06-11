# Đặt tên cho Docker image của bạn
IMAGE_NAME="spring-transaction:v1.0"

# Kiểm tra xem image cũ có tồn tại không
if [[ "$(docker images -q $IMAGE_NAME 2> /dev/null)" != "" ]]; then
  echo "Deleting old image: $IMAGE_NAME"
  docker rmi $IMAGE_NAME
fi

# Xây dựng image mới
echo "Building new image: $IMAGE_NAME"
docker build -t $IMAGE_NAME .

# Xem lại danh sách các image để kiểm tra
docker images