FROM gradle:7-jdk17-alpine
COPY --chown=gradle:gradle . .

WORKDIR .
RUN gradle build -x test --no-daemon
EXPOSE 8000
CMD ["gradle", "bootRun"]
