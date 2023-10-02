// Function to fetch and display user's tasks
function fetchUserTasks() {
    const userId = 1; // Replace with the desired user's ID


    //Use ` (backticks) to properly use userId
    const apiUrl = `/api/v1/tasks/${userId}`; // Construct the URL

    //Fetch method to backend using apiUrl
    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network Error');
            }
            return response.json();


        })
        .then(data => {
            // Handle JSON data here
            const taskList = document.getElementById('taskList');
            taskList.innerHTML = ''; // Clear any existing data
            data.forEach(task => {
                // Create list items for each task
                const listItem = document.createElement('li');
                //Ensure using correct names from DB JSON format Ex: "taskName": "Hello" (Pay attention to name of whatever input)
                listItem.textContent = task.taskName; // Display the task name. Can customize this
                taskList.appendChild(listItem); // Ensure you add to list (.appendChild()) to keep the forEach method valid & checks for next value.
            });
        })
        
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}
// Call the fetchUserTasks function when the page loads
window.addEventListener('load', fetchUserTasks);


function addUserTask(){
    //!Pickup after website
}


