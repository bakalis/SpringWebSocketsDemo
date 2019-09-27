var stompClient = null;




function connect() {  //Connects to the WebSocket Server / Subscribes to the Message Broker destination / Sets Callbacks for Fetched Data / dropped Connection
    socket = new SockJS('/content');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        $("#rcBtn").hide();
        stompClient.subscribe('/topic/greetings', function (message) {
            applyOrder(message.body);
        });
    });
    socket.onclose = function(){  //If the WebSocket connection Drops this function is called.
        $("#rcBtn").show();
    }
}

function applyOrder(order) {  //Updates the Content of the Website according to the data received
    $("#nameTag").html(JSON.parse(order).name + " says:");
    $("#headerTag").html(JSON.parse(order).header);
}


//Attempts to connect to the WebSocket Server on load / Reconnects when the Reconnect button is pressed
$(function () {
    connect();
    $( "#rcBtn" ).click(function() { connect(); });
});

