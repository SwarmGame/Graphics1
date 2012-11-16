Swarms
Beta version

To play swarms, one computer must run the server code, located in server/Main. 4 command line arguments must be set:
Player 1 Name, Player 1 Password, Player 2 Name, and Player 2 Password.  To connect to the game, a player must run
the client code, under client/Game.  Again, 4 command line arguments are needed: Username, Password, Hostname, and
Port.  The Hostname and Port are used to connect to the host server, and the Username and Password will connect the
client to a swarm in the game.

The game is played by two users, each controlling a single swarm.  The swarm consists of a large queen and a number
of particles moving around it.  The goal of the game is to attack the opponent's queen with your own particles while
keeping your queen safely out of reach of their particles.  You lose hit points while enemy particles touch your queen
(displayed at the bottom of the screen), and you lose when your hit points reach 0.


To run this code, the following VM options must be set:  -Djava.library.path=lib/natives-win32
To do this, go to Run -> Edit Configurations and paste the line into the VM Options box.

Troubleshooting
1. Failed to initialise the LWJGL display error
Solution: Make sure that you have set “-Dorg.lwjgl.opengl.Display.allowSoftwareOpenGL=true” option in JAVA VM settings.