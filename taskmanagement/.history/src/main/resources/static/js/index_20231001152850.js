// your-script.js

// Wait for the DOM to be fully loaded before executing JavaScript
document.addEventListener("DOMContentLoaded", function () {
    // Get references to the navigation links
    const homeLink = document.querySelector("a[href='index.html']");
    const loginLink = document.querySelector("a[href='login.html']");
    const aboutLink = document.querySelector("a[href='about.html']");
    const tasksLink = document.querySelector("a[href='task.html']");

    // Handle click events for navigation links
    homeLink.addEventListener("click", function (e) {
        e.preventDefault(); // Prevent the default link behavior
        // Redirect to the home page (index.html)
        window.location.href = "index.html";
    });

    loginLink.addEventListener("click", function (e) {
        e.preventDefault();
        // Redirect to the login page (login.html)
        window.location.href = "login.html";
    });

    aboutLink.addEventListener("click", function (e) {
        e.preventDefault();
        // Redirect to the about page (about.html)
        window.location.href = "about.html";
    });

    tasksLink.addEventListener("click", function (e) {
        e.preventDefault();
        // Redirect to the tasks page (task.html)
        window.location.href = "task.html";
    });
});
