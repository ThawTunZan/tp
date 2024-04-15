# Dave Gunawan - Project Portfolio Page

## Overview

Future Academic Planner (FAP) is a **robust academic management software** designed to help **streamline NUS Computer
Engineering (CEG) student’s academic journey at NUS**. If you can type fast, FAP can get your module planning done
faster than your traditional GUI app.

- **New Feature:** View modules needed to graduate
    - **What it does:** Allows users to view specific CEG Modules they have to take in order to fullfil graduation
      requirements.
    - **Justification:** Improves product by helping users keep track of what core modules they need to complete.
    - **Highlight:** Requires understanding of the user's viewpoint to maintain feasible implementation without
      compromising the user's experience.
- **New Feature:** View specific module information
    - **What it does:** Allows users to view a specific NUS AY23/24 module's title, MCs and description within the CLI.
    - **Justification:** Enhances user experience by allowing users to view what a certain module is about without
      leaving
      the application.
    - **Highlight:** Requires reworking the `CommandMetadata` to allow matching of optional regex.
- **New Feature:** Remove modules
    - **What it does:** Allows users to remove a module from the current list.
    - **Justification:** Necessary in the case of a mistaken input or a failed module.
    - **Highlight:** Involves robust integration and exception handling to ensure it works without fail.


- #### Code Contributed: [Reposense](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=jensonjenkins&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=jensonjenkins&tabRepo=AY2324S2-CS2113-W14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

- **Enhancements to existing features:**

    - Implemented `CommandMetadata` to support optional user arguments (Pull
      request [#104](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/104)).
    - Implemented support for equivalent modules in `CEGModules` (Pull
      request [#85](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/85)).
    - Thorough JUnit tests for `ViewGraduateCommand` (Pull
      request [#147](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/147/files#diff-87fdfa2f8184225c382d809a9d22c48adff277781c7d5dedc3e71bf811063153)).
    - Thorough JUnit tests for `RemoveCommand` (Pull request [#66](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/66))

- **Documentation:**
    - User Guide:
        - Added documentation for `graduate`, `remove`, `view c/COURSE_CODE`
          (Pull
          requests [#87](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/87), [#147](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/147)).
    - Developer Guide:
        - Design details and class diagram of `Command` class (Pull
          request [#160](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/160)).
        - Implementation details and sequence diagrams of the `view` commands (Pull
          request [#160](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/160)).
        - Appendix: Instructions for manual testing (Pull
          request [#162](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/162)).

- **Contributions to team-based tasks:**
    - Submission of final deliverables and issued the v2.1 release.
    - Helped team with the initial setup of the team and individual repositories.
    - Provided non-trivial PR review and approvals (Pull
      request [#150](https://github.com/AY2324S2-CS2113-W14-3/tp/pull/150)).

- **Community:**
    - Provided non-trivial Developer Guide reviews of fellow teams (example:
      [1](https://github.com/nus-cs2113-AY2324S2/tp/pull/5/files/3d1a054570b167dd86c9e9791722a65713febb74#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b))
    - Reported bugs and suggestions for other teams in the class (examples:
      [1](https://github.com/jensonjenkins/ped/issues/1), [2](https://github.com/jensonjenkins/ped/issues/3),
      [3](https://github.com/AY2324S2-CS2113-T12-3/tp/issues/172))
    


