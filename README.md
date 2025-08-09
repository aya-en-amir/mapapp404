# Project Name : MapApp404



## Authors
Enaya Amir, Haeun Goo, Ira Ara, Isha Ruparelia, Olivia Boyle



# Overview
Our program works by using user input to output a list of tailored locations as well as a map to pinpoint respective locations.

When our program runs, it takes you to the first landing page (the main page) where you enter: 

- current mood/feeling (e.g I am feeling perplexed today).
- current postal code.
  
The postal code will be given to the Google Maps API and it will return 20 nearby locations. Based on these location reviews and the user's prompt, the Deep Seek API will return a list of 5 locations that the user can go to. 

As a result, there are three landing pages; one for the user to input all information, one to display the top 5 recommendations, and one to display the map.



# Purpose

The purpose of our program has been designed to allow users find the top rated locations (e.g. restaurants, cafes, theme parks, stores) based on their current emotional state. 

If the user is feeling stressed, our program will generate nearby locations such as, cafes, spas, parks, etc., and output them onto a map for the user's visualization.



# Table of Contents
### [Installation Instructions](#installation-instructions)
### [Usage Instructions](#usage-instructions)
### [External APIs](#external-apis)
### [License](#license)
### [Features of the Software](#features-of-the-software)
### [Feedback](#feedback)
### [Contributions](#contributions)



# [Installation Instructions](#installation-instructions)
1. The program source code can be obtained by clicking the green code button. From there, you can either:
   
a. clone the repository using the provided link
   
   i. Copy the url.
    
   ii. On IntelliJ IDEA (or any other JAVA IDE) create a new project from version control.
   
<img width="1440" height="900" alt="Screenshot 2025-08-07 at 18 51 53" src="https://github.com/user-attachments/assets/17623096-2611-4aca-8927-6cd3c6d447d8" />

  iii. Paste the project's link into the URL section.
  
   iv. Click clone.



b. download the project directly as a ZIP file

The program runs with JDK24, so ensure your IDE is configure to use JDK24. If your IDE is set to a different JDK version, update the project settings so it uses JDK 24 to avoid compatibility issues by following these steps. 

i. Head to project structure.

<img width="1440" height="900" alt="Screenshot 2025-08-07 at 18 47 36" src="https://github.com/user-attachments/assets/7eac993c-d3a1-4fd0-af0b-ff98130a5966" />

ii. Click such that it's JDK24. 

<img width="1440" height="900" alt="Screenshot 2025-08-07 at 18 48 25" src="https://github.com/user-attachments/assets/2dd849c7-7503-4a2f-b328-146bd3455a11" />

iii. Then, press apply. 


2. The program requires a DeepSeek and Google Maps API Key and a DeepSeek endpoint that can be acquired from these links : 
- DeepSeek API : https://platform.deepseek.com/sign_in

- GoogleMaps Places API : https://developers.google.com/maps/documentation/places/web-service

A .env needs to be created. Right click on this directory as specified in the picture.

<img width="1440" height="900" alt="Screenshot 2025-08-07 at 18 50 55" src="https://github.com/user-attachments/assets/1f6ab43c-3efb-4de6-be95-5ce08c955eb0" />


Click new and create a new file

<img width="1440" height="900" alt="Screenshot 2025-08-07 at 18 29 59" src="https://github.com/user-attachments/assets/78aa8c22-7de9-4b00-acce-c94f78d7f51e" />


Name the file as .env.
Then, type in the DeepSeek and GoogleMaps API. Fill in DEEPSEEK_ENDPOINT with https://openrouter.ai/api/v1/chat/completions

<img width="1440" height="900" alt="Screenshot 2025-08-07 at 18 27 13" src="https://github.com/user-attachments/assets/7b2a5521-c75b-4dd5-9c03-975b02e0036e" />



# [Usage Instructions](#usage-instructions)
1. Run the main function located in /src/main/java/app/main/ by clicking the green play button on the top right of the screen. Then a pop out window will appear.
2. Fill out the information needed.
   Make sure your postal code is written in the correct format. 
3. Click find locations. 


# [External APIs](#external-apis)
### Our program uses 3 external APIs:
- DeepSeek API (for the synonym search).
- Google Map API (for the Google review a location proximity search).
- JXMap API (for the map display with the respective locations).

# [License](#license)

MIT License

  Permissions
   - Commercial use
   - Distribution
   - Modification
   - Private use

# [Features of the Software](#features-of-the-software)
The first feature of this software involves user authentication. User's are required to submit their name, postal code, and current mood in order for our software to identify prime locations (login screen). The program then displays a list of the top nearby locations with respective addresses (recommendation screen). For better viewing, the user can click on the "view map" button, a map of the surrounding area will appear along with the posted locations pinpointed on the map (map view screen).

On the back-end, this program uses DeepSeek in order to find common synonyms for the user's inputted current mood, as well as Google Maps to find nearby locations with reviews that match the user's mood, in some capacity.


### Landing Page 1 (Login Screen):

User fills in their mood and current location postal code. 

<img width="499" height="196" alt="Screenshot 2025-07-30 at 10 02 34 PM" src="https://github.com/user-attachments/assets/8f575995-dc0b-4924-b92f-72c880a7f128" />



### Landing Page 2 (Recommendation Screen):

The top 5 suitable location are displayed. 

<img width="665" height="358" alt="image" src="https://github.com/user-attachments/assets/4c03451e-e9f0-400e-b08c-80ca88c6abe1" />




### Landing Page 3 (Map View Screen):

The map view of the locations. 

<img width="1054" height="508" alt="image" src="https://github.com/user-attachments/assets/a38c41c7-0ccf-4d38-8516-271ae1c7ecbc" />



# [Feedback](#feedback)

Rules and guidelines for valid feedback consist of 3 main points. The points include: 
(1) One thing you enjoyed about our software (front-end or back-end is acceptable). 
(2) One thing you did not enjoy. 
(3) One thing that could've been more improved. 

Please be sure to list out all three points in the following feedback form:

https://docs.google.com/forms/d/e/1FAIpQLSfPoqcIo9gPutUcTL5bcXASE0s6O8cvfxobFAL9fK4LXb4MAw/viewform?usp=header


# [Contributions](#contributions)

If you or your team would like to contribute to our project, please follow the guidlines to ensure efficient collaboration.

### You can Contribute by:
        - Submitting code improvements.
        - Reporting or fixing bugs.
        - Implementing additional features.
        - Assisting with code reviews and testing. 

### Forking onto your GitHub Repository:
        - On the GitHub repository page, click on the "Fork" button to create your own copy of this project.
        - CLone your fork locally.

### Guidlines for a Merge Request:
        - Create a new branch using the following template:  first name + . + last name/initial (i.e. john.smith or john.s).
        - Make all edits in this branch.
        - Ensure your own code passes all test cases with descriptive commit messages.
        - Push your branch.
        - Create a pull request from your branch to the project's main branch.
        - Clearly describe the changes you made and reasoning in the pull request. 

### Protocols for Reviewing Contributions and Merging into the Project:
        - Please review and ensure pull request follows code style and functionality. 
        - Look for bug issues and resolve them before approving pull request.
        - Additional reasoning for code changes may be needed prior to approving pull request.

        - If code looks valid and up to standard, reviewers may merge the pull request into the project's main branch.
        
//


Final Note: We hope you enjoy using our software!

