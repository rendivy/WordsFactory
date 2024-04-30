# WordsFactory

<img src="readme-package/logo.png" height = "120" align="right" hspace="50">

### Expand your lexicon effortlessly

Android app for learning and memorizing new words quickly

## Architecture

WordsFactory is a multi-module project that follow
the [google modularization pattern](https://developer.android.com/topic/modularization/patterns#types-of-modules).

The project is divided into 14 modules:

<img src="readme-package/diagrams/module-diagram.png" width="700" hspace="5" vspace ="10" align="center" hspace="50">

- **Feature modules**
    - A feature is an isolated part of an app's functionality that usually corresponds to a screen or series of closely
      related screens, like a sign up or checkout flow. If your app has a bottom bar navigation, it’s likely that each
      destination is a feature.
- **Data modules**
    - A data module usually contains a repository, data sources and model classes. The three primary responsibilities of
      a data module are:
    - Encapsulate all data and business logic of a certain domain: Each data module should be responsible for handling
      data that represents a certain domain. It can handle many types of data as long as they are related.
- **Common modules**
    - A common module is a module that contains code that is shared across multiple other modules. This can include code
      that is shared across the entire app, or code that is shared across a subset of modules.

## Tech Stack

- **Dependency Injection**
    - Dagger-Hilt
    - Dagger-2
- **Network**
    - Retrofit
- **Data store**
    - Room
    - Data Store
- **UI**
    - Jetpack Compose
- **Asynchronous**
    - Coroutines
- **Testing**
    - JUnit
    - Mockk
- **Presentation-Layer pattern**
    - MVVM

## Screenshots

#### OnBoarding Section

<p>
<img src="readme-package/screenshot/on_boarding_1.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
<img src="readme-package/screenshot/on_boarding_2.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
<img src="readme-package/screenshot/on_boarding_3.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
</p>


#### Auth Section

<p>
<img src="readme-package/screenshot/login.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
<img src="readme-package/screenshot/registration.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
</p>

#### Dictionary Section

<p>
  <img src="readme-package/screenshot/word.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
  <img src="readme-package/screenshot/no_word.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
</p>


#### Training Section

<p>
  <img src="readme-package/screenshot/training.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
  <img src="readme-package/screenshot/timer.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
  <img src="readme-package/screenshot/question-word.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
  <img src="readme-package/screenshot/training_finished.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
</p>


#### Video Section

<p>
  <img src="readme-package/screenshot/video.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
</p>


#### Widget Section
<p>
  <img src="readme-package/screenshot/widget.png" width="200" hspace="5" vspace ="10" align="center" hspace="50">
</p>
