# Team Name: 404 Team Not Found
## Authors:

# Overview
Our program works by using user input to output a list of tailored locations as well as a map to pinpoint respective locations.

When our program runs, it takes you to the first landing page (the main page) where you enter: 
- current mood/feeling (e.g I am feeling perplexed today).
- current postal code.
The postal code will be given to the Google Maps API and it will return 20 nearby locations. Based on these location reviews and the user's prompt, the Deep Seek API will return a list of 5 locations that the user can go to. 

As a result, there are three landing pages; one for the user to input all information, one to display the top 5 recommendations, and one to display the map.

# Purpose
This program is made to help user find locations tailored to their current emotiional state. 

# Table of Contents
### [Installation Instructions](#installation-instructions)
### [Clean Architecture and SOLID Principles](#clean-architecture-and-solid-principles)
### [External APIs](#external-apis)
### [License](#license)
### [Visual Output](#visual-output)
### [Feedback](#feedback)
### [Contributions](#contributions)


# [Installation Instructions](#installation-instructions)
1. The program source code can be obtained by clicking the green code button. From there, you can either:
- clone the repository using the provided link, or
- download the project direactly as a ZIP file
2. Open the project on a Java IDE equipped with JDK 24.
3. Run the main class to start the program. The main class is located in /src/main/java/app/main
  

# [Clean Architecture and SOLID Principles](#clean-architecture-and-solid-principles)


[# External APIs](#external-apis)
### Our program uses 3 external APIs:
- DeepSeek API (for the recommended location generator).
- Google Map API (for the Google review a location proximity search).
- JXMap API (for the map display with the respective locations).

# License

# Visual Output
### Landing Page 1 (Login Screen): 
<img width="499" height="196" alt="Screenshot 2025-07-30 at 10 02 34 PM" src="https://github.com/user-attachments/assets/8f575995-dc0b-4924-b92f-72c880a7f128" />

### Landing Page 2 (Recommendation Screen): 
<img width="665" height="358" alt="image" src="https://github.com/user-attachments/assets/4c03451e-e9f0-400e-b08c-80ca88c6abe1" />


### Landing Page 3 (Map View Screen): 
<img width="1054" height="508" alt="image" src="https://github.com/user-attachments/assets/a38c41c7-0ccf-4d38-8516-271ae1c7ecbc" />




# Feedback
Rules and guidelines for valid feedback include,


# Contributions
If you or your team would like to contribute to our project, please begin by



