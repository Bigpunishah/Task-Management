//Function for logging in
function authenticateUser(){
    // authentication endpoint
    const apiUrl = '/api/v1/user/login/authenticate';
    //todo: update backend authentication process

    // Users information values
    const credentials = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };
    

    // Use the fetch function to make POST request to the backend apiUrl
    fetch(apiUrl, {
        method: 'POST', // Use the POST to send data to the server
        headers: {
            'Content-Type': 'application/json' // request content JSON
        },
        body: JSON.stringify(credentials) // Convert the credentials object to a JSON string and send it as the request body
    })
    .then(response => {
        // if failure send error
        if (!response.ok) {
            throw new Error('Network Error'); // Throw a network error if the response is not okay
        }
        return response.json(); // Parse the response as JSON
    })
    .then(data => {
        // if the response is good from backend then..
        // Redirect user to the "task.html" page upon successful login
        console.log("We made it this far" + data)
        window.location.href = '/task';
    })
    .catch(error => {
        console.error(error); // Log the error to the console
    });

}