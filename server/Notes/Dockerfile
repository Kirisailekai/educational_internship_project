# Этап 1: Сборка фронтенда
FROM node:18 AS frontend-build
WORKDIR /app

# Копируем файлы клиента и устанавливаем зависимости
COPY ./client ./client
WORKDIR /app/client
RUN npm install
RUN npm run build

# Этап 2: Сборка бэкенда
FROM maven:3.8.2-jdk-11 AS backend-build
WORKDIR /app

# Копируем серверные файлы и билд фронтенда
COPY ./server/Notes ./server
COPY --from=frontend-build /app/client/build ./server/src/main/resources/static

# Проверяем, что файл pom.xml существует
RUN ls -la /app/server

# Собираем бэкенд без профиля prod
WORKDIR /app/server
RUN mvn clean package -DskipTests

# Этап 3: Финальная сборка и запуск
FROM openjdk:11-jdk-slim
WORKDIR /app

# Копируем финальный JAR-файл
COPY --from=backend-build /app/server/target/*.jar Notes.jar

# Экспонируем порт и запускаем приложение
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Notes.jar"]
