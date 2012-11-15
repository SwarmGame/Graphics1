


To run this code, the following VM options must be set:  -Djava.library.path=lib/natives-win32
To do this, go to Run -> Edit Configurations and paste the line into the VM Options box.

Troubleshooting
1. Failed to initialise the LWJGL display error
Solution: Make sure that you have set “-Dorg.lwjgl.opengl.Display.allowSoftwareOpenGL=true” option in JAVA VM settings.