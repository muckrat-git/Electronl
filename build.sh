find . -name "*.class" -type f -delete
javac --release 8 Electronl/main.java
jar cfme main.jar MANIFEST.MF Electronl.main Electronl/ *