document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    form.addEventListener("submit", function (e) {
        const email = document.getElementById("email").value.trim();
        const github = document.getElementById("github").value.trim();
        const linkedin = document.getElementById("linkedin").value.trim();

        const emailPattern = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
        const githubPattern = /^https:\/\/(www\.)?github\.com\/[A-Za-z0-9_-]+\/?$/;
        const linkedinPattern = /^https:\/\/(www\.)?linkedin\.com\/.*$/;

        if (!emailPattern.test(email)) {
            alert("Please enter a valid email address.");
            e.preventDefault();
            return;
        }

        if (!githubPattern.test(github)) {
            alert("Please enter a valid GitHub profile URL (e.g., https://github.com/username).");
            e.preventDefault();
            return;
        }

        if (!linkedinPattern.test(linkedin)) {
            alert("Please enter a valid LinkedIn profile URL (e.g., https://www.linkedin.com/in/username).");
            e.preventDefault();
            return;
        }

        // If all validations pass
        console.log("Form submitted successfully.");
    });
});
