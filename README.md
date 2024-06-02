# JetGoogleRepositories

JetGoogleRepositories is an Android application designed to search for GitHub repositories using the GitHub API. Users can click on any repository to see detailed information on a new screen. The project is built with Jetpack Compose, utilizes navigation, and implements pagination. All fetched data is stored in a local database using Room to minimize network requests on subsequent accesses. This project follows the MVVM architecture pattern.

This project was created as a Senior Android developer challenge from Monty Mobile.


https://github.com/Mahdi-Rida/JetGoogleRepository/assets/65857215/ae9dc620-9d06-4676-8d7a-0dcba21e1b9b



## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Screens](#screens)
- [Architecture](#architecture)
- [SharedElementTransition](#SharedElementTransition)
- [TestingYourApp](#TestingYourApp)

## Installation

To set up the project locally, follow these steps:

1. **Clone the repository**
    ```sh
    git clone https://github.com/Mahdi-Rida/JetGoogleRepository.git
    ```

2. **Navigate to the project directory**
    ```sh
    cd JetGoogleRepositories
    ```

3. **Open the project in Android Studio**

4. **Build the project and run on an emulator or a physical device**

## Usage

Once the app is installed, users can:

- Search for GitHub repositories.
- Click on any repository to view its details.
- Navigate to Detail screen using intuitive navigation.

## Features

- **Search GitHub Repositories**: Search functionality to find repositories using the GitHub API.
- **Repository Details**: Detailed view of each repository with additional information.
- **Local Database**: Stores fetched data locally using Room to reduce network requests.
- **Pagination**: Efficiently loads data in pages to improve performance and user experience.
- **Jetpack Compose UI**: Modern UI built with Jetpack Compose.
- **MVVM Architecture**: Ensures a clean separation of concerns and testable code.

## Screens

- **Splash Screen**: Initial loading screen displayed when the app is launched.
- **Main Screen**: The primary screen where users can search for repositories.
- **Details Screen**: Displays detailed information about a selected repository.

## Architecture

The project follows the MVVM (Model-View-ViewModel) architecture pattern:

- **Model**: Manages the data layer. Includes the Room database and network operations.
- **View**: The UI layer, built with Jetpack Compose.
- **ViewModel**: Manages UI-related data in a lifecycle-conscious way. Serves as a bridge between the View and Model. Using Events and States.

## SharedElementTransition

The application employs Shared Element Transitions to create a smooth and visually appealing transition between the Main Screen and the Details Screen. When a user clicks on a repository in the Main Screen, the transition animates shared UI elements seamlessly to the Details Screen, enhancing the user experience by maintaining context and continuity.

## TestingYourApp

If you want to test your app without lags, it's recommended to create a release version and install it on your phone. Running the app in debugging mode can cause lag due to additional debugging overhead.


