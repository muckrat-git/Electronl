#!/bin/bash

javac --release 8 Electronl/main.java
jar cfme main.jar MANIFEST.MF Electronl.main Electronl/ *