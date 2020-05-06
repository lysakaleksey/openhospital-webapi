#!/bin/sh

# check for java home
JAVA_EXE=$JAVA_HOME/bin/java

if [ -z $JAVA_HOME ]; then
  echo "JAVA_HOME not found. Please set it up properly."
  JAVA_EXE=java
fi

$JAVA_EXE -jar OH-web-2.0.1.jar --spring.config.location=rsc/application.properties
