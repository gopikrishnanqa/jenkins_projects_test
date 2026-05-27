FROM jenkins/jenkins:2.516.1-lts-jdk21

USER root

RUN apt-get update && \
    apt-get install -y --no-install-recommends git && \
    rm -rf /var/lib/apt/lists/*


USER jenkins