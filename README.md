# Audiobook Demo App


The Audiobook Demo App is a simple Android app that demonstrates how to fetch and display podcast episodes using the Listen API. The app allows users to browse podcast episodes related to the "star wars" keyword and view the details of each episode. Additionally, the app provides the ability to mark episodes as favorites.


## Features

- Fetches podcast episodes from the Listen API
- Displays a list of episodes with details including title, publisher, thumbnail, and description
- Allows users to mark episodes as favorites
- Implements pagination to load more episodes as the user scrolls


## Libraries

- OkHttp: for making network requests to the Listen API
- Gson: for parsing JSON responses
- ViewModel and LiveData: for managing the app's data and UI state
- SharedPreferences: for storing favourite episode data locally
- Glide for image loading and manipulation 


## Concepts Used

- Network requests: the app uses the OkHttp library to make network requests to the Listen API to fetch podcast episode data.
- Model-View-ViewModel (MVVM) Architecture: Separating app concerns into data models, views, and view models for improved maintainability and testability
- JSON parsing: the app uses the Gson library to parse the JSON response from the Listen API.
- ViewModel and LiveData: the app uses the ViewModel and LiveData classes from the Android Architecture Components to manage the app's data and UI state.
- RecyclerView: the app uses the RecyclerView widget to display the list of podcast episodes.
- Pagination: the app implements pagination to load more episodes as the user scrolls.
- Adaptive layout: the app uses adaptive layout techniques to provide a consistent user experience across different screen sizes and orientations.
- Custom views: the app uses custom views to display podcast episode details in a visually appealing way.


## Getting Started

To run the app, you will need an Android device or emulator running Android 5.0 (API level 21) or higher. Follow these steps:

1. Clone or download the project from the repository
2. Open the project in Android Studio
3. Build and run the app on your device or emulator


## Usage

Upon launching the app, you will see a list of podcast episodes related to the "star wars" keyword. You can scroll through the list to view more episodes, and click on an episode to view more details.

Upon clicking on any Podcast, on the next screen, you can see its description,  publisher details, full title, etc. Also, from here, you can mark that podcast favourite too!

To mark an episode as a favourite, click the star icon in the episode details screen. The star icon will turn yellow to indicate that the episode is now a favourite.
