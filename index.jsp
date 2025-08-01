<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resume Builder</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>ATS Resume Builder</h1>
        <form action="generateResume" method="post">
            <label>Full Name</label>
            <input type="text" name="name" required>

            <label>Email</label>
            <input type="email" name="email" id="email" required>

            <label>Phone</label>
            <input type="text" name="phone" required>

            <label>LinkedIn</label>
            <input type="text" name="linkedin" id="linkedin">

            <label>GitHub</label>
            <input type="text" name="github" id="github">

            <label> Professional Summary</label>
            <textarea name="summary" rows="4" required></textarea>

            <label>Education</label>
            <textarea name="education" rows="4" required></textarea>

            <label>Experience (Optional)</label>
            <textarea name="experience" rows="4"></textarea>

            <label>Skills</label>
            <textarea name="skills" rows="4"></textarea>

            <label>Projects</label>
            <textarea name="projects" rows="4" required></textarea>

            <label>Certifications</label>
            <textarea name="certifications" rows="4"></textarea>

            <label>Choose Format</label>
            <select name="format">
                <option value="pdf">PDF</option>
                <option value="docx">DOCX</option>
            </select>

            <button type="submit">Generate Resume</button>
        </form>
    </div>

    <script src="js/script.js"></script>
</body>
</html>
