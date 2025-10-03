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


rootProject.name = "Urban Explorer"
include(":app")
include(":domain")
include(":data")
include(":core")
include(":di")
include(":core:designsystem")
include(":testing")
include(":core:ui")
include(":feature:spots")
include(":feature:about")
include(":core:navigation")
include(":feature:home")
