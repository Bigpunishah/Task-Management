function authenticateUser() {
    // Prevent the default form submission behavior
    preventDefault();

    // Authentication endpoint
    const apiUrl = '/api/v1/user/login/authenticate';

    // Users information values
    const loginData = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    // Use the fetch function to make a POST request to the backend apiUrl
    fetch(apiUrl, {
        method: 'POST', // Use POST to send data to the server
        headers: {
            'Content-Type': 'application/json' // Request content type JSON
        },
        body: JSON.stringify(loginData) // Convert the loginData object to a JSON string and send it as the request body
    })
    .then(response => {
        // If failure, send error
        if (!response.ok) {
            throw new Error('Network Error'); // Throw a network error if the response is not okay
        }
        return response.json(); // Parse the response as JSON
    })
    .then(data => {
        // If the response is good from the backend, then redirect the user to the "task.html" page upon successful login
        console.log("We made it this far");
        window.location.href = '/task';
    })
    .catch(error => {
        console.error(error); // Log the error to the console
    });
}
