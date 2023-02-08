# oddsHub-backend

# How to run locally
- Run `docker-compose up -d`
- Start application `./mvnw spring-boot:run`

# Deploy step
- App deployment
    Git push code to main branch (Automatic deploy by github action)

- Database deployment
    - ssh to huawei server (43.225.140.223)
    - make directory /cco
    - Copy docker-compose.yml to huawei server (43.225.140.223)
    - Change POSTGRES_USER & POSTGRES_PASSWORD
    - Run command
    ```
        docker-compose up -d
    ```

## contributor
- Jeep & Top
