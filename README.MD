# Overview

I had a problem to solve - how to secure our application's file system, network sockets, JVM environment, etc from user-provided scripts running within our application.
After some research I found a way to run pieces of Java code under a sandbox mode, where permissions can be restricted by our application.
This repo contains source code on how this can be achieved.

# Installation

This code is not meant to actually execute, but rather showcase an example. However, this code can easily made runnable with a few modifications. Read the comments under the Main.java for more details.