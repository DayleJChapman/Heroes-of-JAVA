# Heroes-of-JAVA
Culminating for CS grade 12

!!Note: slick2d library required!!
(Add thr library: downloaded from here http://slick.ninjacave.com/)

Contains 2 projects:
*Heroes of Java game (CompSci Culminating)
*Server that goes with the game (TicTacServer)

Heroes of java game description:
Turn based strategy game (based on Fire Emblem). Only works online with included server.
*Runs in windowed mode
*Supports xbox controllers
*Shows gifs
*Provides a nice interface
Flaws:
*Controller support is a hit or miss
*Packet loss, while communicating with the server

Server:
Let's (possibly) infinetely many players play in pairs, altough chance of packet loss increases with the number of players.
*Let's games communicate with their enemies
*Checks for leavers, and notifies the leaver's enemy of it
*Re-pair up people who were left alone and starts automatically a new game between them
*Keeps track of all scores. On request returns top 10 scores. Saves even if the server gets restarted
Flaws:
*Re-pairing people function is still running but client is unable to handle it and simply returns to the main menu
*Packet loss when communicating with clients

Possible solutions:
*Packet loss could be fixed by switching to tcp, instead of udp, but at the same time speed would be sacrificed
*Re-pair functionality could be included in client
