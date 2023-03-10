# oddsHub-backend

# How to run locally
- Run `docker-compose up -d`
- Start application `./mvnw spring-boot:run`

# How to run test
- Using maven script to run the test `./mvnw test -Dspring.profiles.active=test`

# How to create/update database schema
- Add a new file of **DDL** in `resources/db/changelog/ODDS-HUB/DDL`, by order.

# How to migrate data into the table
- Add a new file of **DML** in `resources/db/changelog/ODDS-HUB/DML`, by order.

# Deploy step
- App deployment
    Git push code to main branch (Automatic deploy by github action)
    - Checkout repository
    - Build and push Docker image to github registry
      - Inside build step it will run 2 necessary steps
        - Run `./mvnw test -Dspring.profiles.active=test`
        - Run `./mvnw package -Dmaven.test.skip`
    - Restart docker image through remote at huawei server (43.225.140.223)
      - Database credential can be found in `.env` file

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
- Jeep & Top & Wut & Pop & Airmee

## Note
Currently, email sender using gmail that temporarily created for local/dev env. 

If anyone want to manage this gmail please contact Jeep (jirat.cho).