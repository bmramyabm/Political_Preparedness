****Political Preparedness****

PolitcalPreparedness is an example application built to demonstrate core Android Development skills as presented in the Udacity Android Developers Kotlin curriculum.

This app demonstrates the following views and techniques:

Retrofit to make api calls to an HTTP web service.
Moshi which handles the deserialization of the returned JSON to Kotlin data objects.
Glide to load and cache images by URL.
Room for local database storage.

****ViewModel****
LiveData
Data Binding with binding adapters
Navigation with the SafeArgs plugin for parameter passing between fragments
Setting up the Repository
To get started with this project, simply pull the repository and import the project into Android Studio. From there, deploy the project to an emulator or device.

NOTE: 
In order for this project to pull data, you will need to add your API Key to the project as a value in the CivicsHttpClient.
You can generate an API Key from the Google Developers Console

Getting Started
For the most part, the TODOs in the project will guide you through getting the project completed. There is a general package architecture and most files are present.
Hints are provided for tricky parts of the application that may extend beyond basic Android development skills.
As databinding is integral to the project architecture, it is important to be familiar with the IDE features such s cleaning and rebuilding the project as well as invalidating caches.
Suggested Workflow
It is recommend you save all beautification until the end of the project. Ensure functionality first, then clean up UI. While UI is a component of the application, it is best to deliver a functional product.
Start by getting all screens in the application to navigate to each other, even with dummy data. If needed, comment out stub code to get the application to compile. You will need to create actions in nav_graph.xml and UI elements to trigger the navigation.
Create an API key and begin work on the Elections Fragment and associated ViewModel.
Use the elections endpoint in the Civics API and requires no parameters.
You will need to create a file to complete the step.
This will require edits to the Manifest.
Link the election to the Voter Info Fragment.
Begin work on the Voter Info Fragment and associated ViewModel.
Begin work on the Representative Fragment and associated ViewModel.
This will require edits to Gradle.
You will need to create a file to complete the step.
![Screenshot_1648733286](https://user-images.githubusercontent.com/79679552/161072677-e198797e-698a-4a05-aa0c-1a5e9f17b49e.png)
![Screenshot_1648733309](https://user-images.githubusercontent.com/79679552/161072694-68f27731-3efc-4c29-a354-74b4a4a9c031.png)
![Screenshot_1648733317](https://user-images.githubusercontent.com/79679552/161072702-4e0e68a6-d3fc-45c3-a80b-e48990924fa5.png)![Screenshot_1648733325](https:/![Screenshot_1648733360](https://user-images.githubusercontent.com/79679552/161072730-8c577fda-12c4-4fb8-abd2-98e71a10f517.png)
/user-images.githubusercontent.com/79679552/161072713-8a68b371-6fdc-45aa-b286-ac64fda356e1.png)
![Screenshot_1648733392](https://user-images.githubusercontent.com/79679552/161072745-a11882e9-6649-44a4-a304-f2295c80df51.png)


