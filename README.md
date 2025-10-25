# 📱 Chipster Android

Chipster Android is a cross-platform mobile application built with **React Native**, designed for real-time device communication and control. The project demonstrates integration between Android, iOS, and browser-based clients — allowing connections and data exchange between them seamlessly.

---

## 🚀 Features

- ✅ **Cross-Platform:** Works on Android and iOS.
- 🌐 **Browser Connectivity:** Accepts connections directly from browsers.
- 🔗 **Real-Time Communication:** Supports live data exchange between devices.
- ⚙️ **Modular Codebase:** Well-structured `src/` folder for easy feature expansion.
- 🧩 **TypeScript Support:** Ensures type safety with `tsconfig.json`.
- 🧪 **Testing Ready:** Includes Jest configuration for unit testing.

---

## 📂 Repository Structure

```bash
chipster-android/
├── android/                 # Native Android build code
├── ios/                     # Native iOS build code
├── src/                     # Core application source (JS/TS)
│   ├── components/          # Reusable UI components
│   ├── screens/             # Main screen components
│   ├── utils/               # Helpers, constants, and configs
│   └── services/            # API or connection logic
├── __tests__/               # Jest unit/integration tests
├── .bundle/                 # Metro bundler cache
├── .vs/                     # IDE (Visual Studio / VS Code) configs
├── app.json                 # React Native app configuration
├── babel.config.js          # Babel transpiler setup
├── metro.config.js          # Metro bundler configuration
├── package.json             # NPM dependencies and scripts
├── package-lock.json        # Locked dependency versions
├── tsconfig.json            # TypeScript compiler configuration
├── .eslintrc.js             # ESLint configuration
├── .prettierrc.js           # Prettier formatting rules
├── .watchmanconfig          # File watcher configuration
├── Gemfile                  # Ruby/Gem dependencies (if used)
├── .gitignore               # Files and folders ignored by git
└── README.md                # Project documentation


---

## ⚡ Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/coolb0y/chipster-android.git
cd chipster-android

2. Install dependencies

npm install
# or
yarn install

3. Start Metro bundler

npx react-native start

4. Run on Android

npx react-native run-android

5. Run on iOS

npx react-native run-ios

🧰 Tech Stack

    React Native – Cross-platform mobile framework

    TypeScript – Type-safe JavaScript

    Jest – Unit testing framework

    ESLint & Prettier – Code linting and formatting

    Metro Bundler – React Native build system

🧑‍💻 Development Notes

    To allow browser connections, make sure network permissions and CORS settings are properly configured.

    The connection fix implemented in commit 5c120dc enables accepting requests directly from web clients.

📄 License

This project is open source and available under the MIT License
.
