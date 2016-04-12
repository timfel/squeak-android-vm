# VNC Configuration #

To use VNC with Squeak on the Emulator, a few things need to be done.
  * you need to build the actual SqueakVM from source, see the BuildNotes
  * use the image provided in the Download-Section - it's an image already splitted in 12 parts for usage with the SqueakVM on the Emulator
  * configure the correct tcp routing for the Emulator:
    * while running the Emulator do `telnet localhost 5554` to access the android-console
    * to ensure that all packets that are send to a special port are redirected to that port on the Emulator enter: `redir add tcp:5900:5900`
  * Start Squeak on the Emulator, start/ configure the RFB-Server (the RFB-Start screen is shown on squeak startup)
  * try to connect to localhost with a VNC-Client