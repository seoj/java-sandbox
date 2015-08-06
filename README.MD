# Overview

I had a problem to solve - how to secure our application's file system, network sockets, JVM environment, etc from user-provided scripts running within our application.

After some research I found a way to run pieces of Java code under a sandbox mode, where permissions can be restricted by our application.

This repo contains source code on how this can be achieved.

# Installation

This code is not meant to actually execute, but rather showcase an example. However, this code can easily made runnable with a few modifications. Simply clone, and import this project into your IDE as a Maven project.
Read the comments under the Main.java for more details.

# Synopsis

Java applications have the ability to run under a security manager by using JVM arguments
    -Djava.security.manager -Djava.security.policy=path/to/policy/file
This makes the JVM start with a SecurityManager set. You can easily check this by calling
    System.getSecurityManager();
Which should not be null.
The security policy file is a specially formatted file containing permissions granted under this security manager.
You can file more about it in the official Java documentation http://docs.oracle.com/javase/7/docs/technotes/guides/security/PolicyFiles.html
