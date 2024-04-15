# Fung Wen Wu - Project Portfolio Page

## Overview

Future Academic Planner (FAP) is a **robust academic management software** designed to help **streamline NUS Computer
Engineering (CEG) student’s academic journey at NUS**. If you can type fast, FAP can get your module planning done
faster than your traditional GUI app.

### New Feature

- Implemented `grade` Command (Pull request [#40](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/40)).
  - What it does: Allows user to input the grade attained for a module.
  - Justification: Past grades are an important consideration for students planning the modules to take in the future. 
  Implementing grades will also allow features related to grades to be used.
  - Highlights: This command does various validation before allowing grade to be changed. Command will not succeed if 
  module to be graded is in a future semester. Command will also check the module's Grading Basis and validate if the 
  - appropriate grade is inputted.
- Implemented `gpa` Command (Pull request [#40](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/40)).
  - What it does: Allows user to view their current GPA based on modules they have inputted a grade for.
  - Justification: GPA is important for tracking a student's performance.
- Implemented `desiredgpa` Command (Pull request [#86](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/86)).
    - What it does: Shows user if their desired GPA is still attainable, and a combination of grades that they should 
  achieve is their desired GPA is feasible.
    - Justification: As GPA also plays a huge part in finding opportunities outside of NUS, students will be curious to 
  find out if their desired GPA is attainable, and the minimum results they will need if it is.
- Implemented the `UI` Class (Pull request [#24](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/24)).
  - What it does: Handles interactions with the user, both input and output.
  - Highlights: For inputs, blank spaces will be ignored.

### Code Contributed: [Reposense](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=fungg0&breakdown=true)

### Enhancements Implemented

- Thorough JUnit testing for `grade` and `gpa` feature (Pull request [#74](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/74)).
- Obtain Grading Basis of each module from Json file (Pull request [#161](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/161)).

### Documentation

- User Guide:
  - Added implementation documentation for the features `gpa` and `desiredgpa` (Pull request [#177](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/177)).
  - Added design documentation for `Ui` Class (Pull request [#167](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/167)).
- Developer Guide:
  - Added implementation details of `gpa` feature (Pull request [#75](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/75)).
  - Added implementation details of `desiredgpa` feature (Pull request [#167](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/167)).
  - Added manual testing instructions for `desiredgpa` feature (Pull request [#179](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/179)).
  - Added the content page (Pull request [#177](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/177)).
  - Added the user stories (Pull request [#179](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/179)).
  - Added content page with navigation links (Pull request [#177](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/177)).
- About Us:
  - Updated the table format (Pull request [#191](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/191)).

### Contributions to team-based tasks:

- Actively participate in discussions.
- Help out with the activities in the tutorials.
- Replied to issues from PE-D, and assigned the issues to corresponding teammates in charge.