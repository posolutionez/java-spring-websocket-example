let stompClient = null;

function connect() {
    // 1. Establish the connection transport
    // Note: The '/ws' path must match your Spring WebSocket configuration endpoint
    const socket = new SockJS('http://localhost:8080/ws');

    // 2. Create the STOMP client over the socket
    stompClient = Stomp.over(socket);

    // 3. Connect to the STOMP broker
    stompClient.connect({}, (frame) => {
        console.log('Connected: ' + frame);

        // 4. SUBSCRIBE to the destination where the server sends quotes
        // This is the destination defined by your @SendTo("/topic/quotes")
        stompClient.subscribe('/topic/quotes', (messageOutput) => {
            // This function is called every time a new quote is broadcast
            showQuote(JSON.parse(messageOutput.body));
        });

        // Optional: Send an initial message to trigger the first quote
        sendSubscriptionRequest();

    }, (error) => {
        console.error("STOMP Connection Error:", error);
    });
}

function sendSubscriptionRequest() {
    // 5. SEND a message to the controller's @MessageMapping endpoint
    // This is the destination defined by your @MessageMapping("/subscribe")
    // The server will receive this and send a quote back to the subscribed topic.
    let subscribe_path = '/app/subscribe';
    console.log('sending sendSubscriptionRequest to : ' + subscribe_path);
    stompClient.send(subscribe_path, {}, JSON.stringify({'client': 'web'}));
}

function showQuote(message) {
    // Function to update the UI with the received quote
//    const quoteElement = document.getElementById('quote-display');
//    quoteElement.textContent = message.quote;
    console.log("Received Quote:", message.quote);
}

// Automatically connect when the page loads
window.onload = connect;