# Team Name: 404 Team Not Found
## Authors:

# Overview
Our program works by using user input to output a list of top locations as well as a map to pinpoint respective locations.

When our program is run, it takes you to the first landing page (the main page). Here, the user enters their current mood/feeling (e.g I am feeling perplexed today). This prompt is then sent to our DeepSeek API, and the top 15 synonyms of the user’s sentiment are extracted. The platform then matches the closest locations (using the user's postal code to measure proximity) to these keywords to find a list of 5 locations that the user can go to. The matching between synonyms and locations is completed through Google's API which seeks out top locations from Google reviews using the produced synonyms. The ranking system, which is responsible for creating a list of the top 5 locations, is completed using cosine similarity equation.

As a result, there are three landing pages; one for the user to input all information, one to display the top 5 recommendations, and one to display the map.


# Purpose

[] Says what this project does

 Says why the project was made

 Gives the user a general sense of what problem this project solves and
whether it is a useful project for them


# Table of Contents
### [Installation Instructions](#installation-instructions)
### [Clean Architecture and SOLID Principles](#clean-architecture-and-solid-principles)
### [External APIs](#external-apis)
### [License](#license)
### [Features of the Software)](#features-of-the-software)
### [Feedback](#feedback)
### [Contributions](#contributions)


# [Installation Instructions](#installation-instructions)

Clear, accurate descriptions of how to install the project from start to finish

 Mentions all packages and software which must be downloaded for the
project to work

 Provides links to any other packages or software needed

 Includes all technical requirements and information needed to download
each part of the project

 Includes required versions for all packages or software needed

 Explains if the software is only meant to be installed on a certain OS,
hardware system, or otherwise (for example, Windows only or Mac only)

 Mentions common issues in the installation process and how they can be
overcome

 Examples or tutorials (videos, screenshots, or code snippets) are used to
clarify the steps for software installation

 Examples or tutorials (videos, screenshots, or code snippets) are used to
clarify the steps for overcoming common issues


# [Clean Architecture and SOLID Principles](#clean-architecture-and-solid-principles)


# [External APIs](#external-apis)
### Our program uses 3 external APIs:
- DeepSeek API (for the synonym search).
- Google Map API (for the Google review a location proximity search).
- JXMap API (for the map display with the respective locations).

# [License](#license)

 License is clearly displayed or visible in the GitHub project

 License is legally valid

 License is consistent with the details of the project (for example, it would not
be consistent if the README claims the project is “in the public domain”
while the project has an MIT License)

# [Features of the Software](#features-of-the-software)
[] All major features of the software are described so that a programmer or
user can determine whether the software is right for them

 Descriptions are clear and only detailed when needed

 Examples or tutorials (videos, screenshots, or code snippets) are used to
clarify the software’s features

### Landing Page 1 (Login Screen):
<img width="499" height="196" alt="Screenshot 2025-07-30 at 10 02 34 PM" src="https://github.com/user-attachments/assets/8f575995-dc0b-4924-b92f-72c880a7f128" />

### Landing Page 2 (Recommendation Screen):
<img width="665" height="358" alt="image" src="https://github.com/user-attachments/assets/4c03451e-e9f0-400e-b08c-80ca88c6abe1" />


### Landing Page 3 (Map View Screen):
<img width="1054" height="508" alt="image" src="https://github.com/user-attachments/assets/a38c41c7-0ccf-4d38-8516-271ae1c7ecbc" />




# [Feedback](#feedback)

Rules and guidelines for valid feedback include,

 Clearly says how users can give feedback on the software (via Google Forms,
a discussion board, etc.)

 Clearly provides any necessary links (for example, to a Google Form)

 Has rules for what counts as valid feedback

 Has guidelines for what to expect when submitting feedback



# [Contributions](#contributions)
If you or your team would like to contribute to our project, please begin by

 Clearly says how users can contribute to the project

 Clearly describes instructions for making a fork of the project on GitHub

 Gives guidelines for creating a good merge request

 Describes protocols for reviewing contributions and merging them into the
project

