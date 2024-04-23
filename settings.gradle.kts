pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WordsFactory"
include(":app")
include(":dictionary-api")
include(":database")
include(":dictionary-uikit")
include(":data:auth-data")
include(":core")
include(":feature:splash-feature")
include(":feature:auth-feature")
include(":feature:dictionary-feature")
include(":feature:onboarding-feature")
include(":data:dictionary-data")
include(":feature:video-feature")
include(":feature:widget-feature")
