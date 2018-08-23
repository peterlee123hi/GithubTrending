# Github Trending
Android Clean Architecture tutorial on Caster.io

## Notes
- Layers
    - Data 
        - Model
        - Repository
    - Domain
        - Model
        - UseCase
    - Presentation
        - Model
        - ViewModel
        - View
- UseCases / Features can be found in the interactors folder in the domain package
- Each package (which represents an abstraction/layer in the clean architecture model) has a
build.gradle file that imports the project level dependencies.gradle file
- You can manage versions in the project level dependencies.gradle file
- When using GSON, you don't need the SerializedName annotation if the variable name in the
Java/Kotlin class matches the name of the property in the JSON object