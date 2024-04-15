

# Tan Dexter's Project Portfolio Page

## Project: Future Academic Planner (FAP)

### Overview

**Future Academic Planner (FAP)** is a robust academic management software designed to streamline the academic journey
for NUS Computer Engineering (CEG) students. Optimized for those who can type fast, FAP gets your module planning done
faster than traditional GUI applications.


### Summary of Contributions

Given below are my contributions to the project.

- **New Features**:
  - **Implemented `FAP.java`:** (Pull request [#26](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/26),
    [#43](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/43))
    - **What it does:** Handles the flow of application execution from start to end and integrates all components
      of the software.
    - **Justification:** This feature is crucial as it serves as the backbone of the application, coordinating
      various functionalities.
    - **Highlights:** The implementation was complex as it required understanding the interaction between
      various components including UI, Commands, Parser, and Storage.

  - **Implemented `Storage.java`:** (Pull request [#76](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/76))
    - **What it does:** Manages the storage of user data and module data on the disk.
    - **Justification:** Essential for ensuring data persistence across sessions, enhancing usability.
    - **Highlights:** Involved setting up a robust system to handle potential I/O errors and data corruption issues.

  - **Implemented `init` command (refactored to `set` command):** 
  (Pull request [#44](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/44))
    - **What it does:** Initialise username and user's current semester.
    - **Justification:** Provides a user-friendly way to set up the application for first-time users.
    - **Highlights:** Required understanding of the user's needs and the application's requirements to design.


- **Code contributed**: [RepoSense](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=dextboy&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


- **Project management**:
  - Managed setup of GitHub team organization and repository configuration.
  - Maintained the issue tracker with high activity levels, linking pull requests to issues effectively.
  - Managed version releases from `v1.0` to `v2.1`, including setting up release notes and ensuring systematic
    progression through versions.


- **Enhancements to existing features**:
  - Created extensive JUnit tests for `FAP.java` 
  (Pull request [#71](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/71))
  - Created extensive JUnit tests for `Storage.java` 
  (Pull request [#80](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/80))
  - Setup and maintained automated tests for CLI-based interaction simulations. 
  (Pull request [#80](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/80))
  - Optimized exception handling across the `Storage` class to improve data integrity and error management. 
  (Pull request [#76](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/76))


- **Documentation**:
  - **User Guide**:
    - Added documentation for `add` command and provided comprehensive examples and expected outcomes.
      (Pull request [#89](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/89))
  - **Developer Guide**:
    - Documented the architecture design and implementation details. 
    (Pull request [#164](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/164))
    - Provided class diagrams for `FAP.java` to enhance understanding of the codebase. 
    (Pull request [#64](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/64))
    - Provided class diagrams and sequence diagrams for `Storage.java`, enhancing understanding and
      maintainability of the code. 
    (Pull request [#176](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/176))


- **Community**:
  - Reviewed and provided non-trivial comments on pull requests to improve code quality and functionality. 
  (Pull request [#75](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/75), [#84](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/84))
  - Engaged in active bug reporting during the PED phases, enhancing the application's reliability and user experience.


- **Contributions Beyond the Project Team**:
  - Actively participated in inter-team reviews, providing insightful feedback and suggestions.
  - Reviewed and merged pull requests, ensuring high code quality and consistency with project standards.
  - Contributed to the Developer Guide by including a section for acknowledgments, thanking contributors and tools
    that have facilitated the project's success.


#### Additional Contributions

- **README Page and GitHub Pages**: (Pull request [#183](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/183))
  - Authored and maintained the README page and the GitHub Pages site, which serve as the
    primary information sources for new users and developers.
