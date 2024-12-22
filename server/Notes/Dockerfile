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
COPY ./server/notes ./server
COPY --from=frontend-build /app/client/build ./server/src/main/resources/static

# Убедимся, что файл pom.xml есть в рабочей директории
WORKDIR /app/server
RUN ls -la  # Для проверки файлов

# Собираем бэкенд без профиля prod
RUN mvn clean package -DskipTests

# Этап 3: Финальная сборка и запуск
FROM openjdk:11-jdk-slim
WORKDIR /app

# Копируем финальный JAR-файл
COPY --from=backend-build /app/server/target/*.jar Notes.jar

# Экспонируем порт и запускаем приложение
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Notes.jar"]
