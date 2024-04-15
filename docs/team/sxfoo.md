# Foo Shi Xiang - Project Portfolio Page

## Overview

Future Academic Planner (FAP) is a **robust academic management software** designed to help **streamline NUS Computer
Engineering (CEG) student’s academic journey at NUS**. If you can type fast, FAP can get your module planning done
faster than your traditional GUI app.

### Code Contributed: [Reposense](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=sxfoo&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=sxfoo&tabRepo=AY2324S2-CS2113-W14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements implemented

- **Enhancement:** `Parser` and `CommandMetadata` classes (Pull requests [#33](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/33), 
[#73](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/73))
    - What it does: OOP implementation to parse user typed inputs to match a corresponding `Command` for FAP to execute,
    and also help to validate errors in what the user typed.
    - Justification: Allows future developers to add a new command easily within the FAP program


- **New Feature:** `View` command: View user's module plan (Pull requests 
[#41](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/41))
    - What it does: Allows users to see their module plan in a table format
    - Justification: Enhances user experience by allowing users to view what a certain module is about without leaving
      the application
  

- **New Feature:** In-program `help` command (Pull requests [#84](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/84), 
[#157](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/157))
    - What it does: Show users the available commands and the syntax used within the program
    - Justification: A quick way for FAP users to know the available functionalities without referring back to the 
    user guide


- Implemented `User` class
  - What it does: Carries information about the user of FAP, such as `name` and their `current studying semester`.
  - Currently limited in implementation, but could be expanded to include more features and information to be
    stored and used in FAP

### Contributions to the UG (User Guide)
- Set up `Content Navigation` for readers to jump to specified sections
- Written the section for commands regarding `set` command, `view` command, and `help` command

### Contributions to the DG (Developer Guide)
- Wrote `Parser` & `CommandMetadata` logic design and implementation (Pull request 
[#165](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/165))
- Made the sequence diagram for overall architecture

### Contributions to team-based tasks, Review/mentoring contributions
- Implemented key functionalities independently.
- Collaborated with team members to integrate and test different components of the application (Pull requests [#174](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/174))
- Reviewed pull requests and provided feedback on code quality and functionality. (Pull requests [#65](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/65#discussion_r1540418880)
, [#76](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/76#discussion_r1543313164), [#104](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/104#discussion_r1551942585))
- Assisted team members in understanding and implementing different parts of the codebase in group meetings
- Formatted the Developer Guide from `.md` to `.pdf`

### Contributions beyond the project team
- Reported and helped in resolving bugs in other team's program: [T15-4](https://github.com/AY2324S2-CS2113-T15-4/tp/tree/master)
  (Pull requests: [Bugs in PE](https://github.com/AY2324S2-CS2113-T15-4/tp/issues?q=is%3Aissue+is%3Aoriginal%3A+sxfoo))


