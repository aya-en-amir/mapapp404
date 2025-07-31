# Team Name: 404 Team Not Found


# Overview:
Our program works by using user input to output a list of top locations as well as a map to pinpoint respective locations.

When our program is run, it takes you to the first landing page (the main page). Here, the user enters their current mood/feeling (e.g I am feeling perplexed today). This prompt is then sent to our DeepSeek API, and the top 15 synonyms of the user’s sentiment are extracted. The platform then matches the closest locations (using the user's postal code to measure proximity) to these keywords to find a list of 5 locations that the user can go to. The matching between synonyms and locations is completed through Google's API which seeks out top locations from Google reviews using the produced synonyms. The ranking system, which is responsible for creating a list of the top 5 locations, is completed using cosine similarity equation. 

As a result, there are three landing pages; one for the user to input all information, one to display the top 5 recommendations, and one to display the map.

# Purpose:


# Clean Architecture and SOLID Principles:


# External APIs:
Our program uses 3 external APIs:
  - DeepSeek API (for the synonym search).
  - Google Map API (for the Google review a location proximity search).
  - JXMap API (for the map display with the respective locations).


# Visual Output:


