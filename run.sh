# Compile and package the source code
mvnw package

# Extract dependencies
mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Build image
sudo docker build -t aplazotest/springboot .

# Run the container
sudo docker run -p 8081:8080 aplazotest/springboot
