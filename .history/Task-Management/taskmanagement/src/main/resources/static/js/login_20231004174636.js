// It's a good practice to wrap your JavaScript code in a function or an IIFE 
// to avoid polluting the global scope.
// IIFE - Immediately Invoked Function Expression
(function() {
    // Wait until the DOM is fully loaded before attaching event handlers.
    document.addEventListener("DOMContentLoaded", function() {
        
        // Attach a submit event listener to the form.
        document.querySelector('form').addEventListener('submit', function(event) {
            
            // Prevent the default form submission behavior.
            event.preventDefault();

            // Authentication endpoint.
            const apiUrl = '/api/v1/user/login/authenticate';

            // User information values.
            const loginData = {
                email: document.getElementById("email").value,
                password: document.getElementById("password").value
            };

            // Use fetch to make a POST request to the backend apiUrl.
            fetch(apiUrl, {
                method: 'POST', // Sending data TO the server.
                headers: {
                    'Content-Type': 'application/json' // Content type is JSON.
                },
                body: JSON.stringify(loginData) // Convert the loginData object to a JSON string.
            })
            .then(response => {
                // Check if response is not OK and throw an error if so.
                if (!response.ok) {
                    throw new Error('Network response was not ok'); // This error will be passed to the catch block.
                }
                return response.json(); // Parsing the response as JSON.
            })
            .then(data => {
                // Handle success - navigate to another page, etc.
                // Assuming that upon successful login, a positive response will be received.
                console.log("Authentication Successful", data);
                window.location.href = '/task';
            })
            .catch(error => {
                // Handle error - show error message to user, etc.
                console.error('Error:', error);
            });
        });
    });
})();
