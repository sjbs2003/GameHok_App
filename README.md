# GameHok - Esports Tournament App

GameHok is an Android application built with Jetpack Compose that allows users to participate in esports tournaments, follow gamers, and learn from gaming experts.

## Features

- **Tournament System**: Browse and join various gaming tournaments with real-time registration status
- **Game Selection**: Multiple popular games including PUBG, Call of Duty, and Counter Strike
- **Learning Platform**: Access educational content from gaming experts
- **Social Features**: Follow and connect with other gamers
- **Premium Features**: Access exclusive content and features with premium membership

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM
- **Navigation**: Jetpack Navigation Compose
- **State Management**: Kotlin Flow and StateFlow
- **Image Loading**: Coil
- **UI Components**: Material3

## Prerequisites

- Android Studio Arctic Fox or later
- JDK 11 or later
- Android SDK API 33 or later
- Kotlin 1.8.0 or later

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/gamehok-app.git
   ```

2. Open Android Studio and select "Open an Existing Project"

3. Navigate to the cloned directory and click "OK"

4. Wait for the project to sync and download dependencies

5. Run the app:
   - Select an Android device or emulator
   - Click the "Run" button (▶️) or press Shift + F10

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/kirab/gamehokapp/
│   │   │   ├── ui/theme/           # Theme configuration
│   │   │   ├── view/               # UI components
│   │   │   │   ├── homeScreen/     # Home screen components
│   │   │   │   ├── gameDetailScreen/ # Game details
│   │   │   │   └── tournamentDetailScreen/ # Tournament details
│   │   │   └── GameHokApp.kt       # Main application file
│   │   └── res/                    # Resources
└── build.gradle                    # Project configuration
```

## Features Implementation

### Home Screen
- Premium membership promotion banner
- Available games showcase
- Active tournaments list
- Learning resources section
- Gamers to follow recommendations

### Tournament Details
- Tournament overview with registration status
- Prize pool distribution
- Tournament schedule and rounds
- Joining instructions
- Player management

### Game Details
- Game statistics
- Active tournaments
- Achievement tracking
- Pro gaming tips

## Screenshots

[Add your app screenshots here]

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


## Acknowledgments

- Design inspiration from modern gaming platforms
- Material Design guidelines
- Jetpack Compose documentation and codelabs

## Contact

Your Name - [@kirab_s](https://twitter.com/kirab_s)

Project Link: [https://github.com/sjbs2003/gamehok-app](https://github.com/sjbs2003/gamehok-app)
