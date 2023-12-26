# This Dockerfile describes a container that is able to run / test and build this
# application. It can be used to develop the application without having to install
# the required dependencies on the host system.

# Use an official OpenJDK runtime as a parent image
FROM ubuntu:24.04

# Set the maintainer
LABEL maintainer="shakelang <contact@shakelang.com>"

# Install JDK 16
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Install the browsers (Firefox, Chrome, Safari) and the required drivers
RUN apt-get update && apt-get install -y firefox chromium-browser

# Install the required tools (Maven, Git, Gradle, Gpg, GitHub CLI)
RUN apt-get update && apt-get install -y maven git gradle gnupg gh

# Set the working directory in the container
WORKDIR /usr/src/app

# Start the container with a bash
CMD ["/bin/bash"]