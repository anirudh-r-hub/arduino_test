# arduino_test
Smart Home BE Project

WIFI 8266 as a Hotspot connected to a mobile host 

1. Basic LED ON and OFF
  1.1 Android Application -
    Send HTTP request to the ESP chip
    1.1.1 Problems encountered - 
    1. Permission denied - permission denied for sending the request
       solved by https://stackoverflow.com/questions/14377832/permission-denied-in-while-making-http-request
       

  1.2 ESP Arduino application - 
    Based on index on the URL '\on' or '\off' the action of LED on or off is taken

2. Voice commands by Speech recognition
