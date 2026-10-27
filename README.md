# CampusConnect - Campus Management & Student Portal

**CampusConnect** is a native Android application built with Java, Material 3 Design components, and Android ViewBinding. It serves as an integrated student portal providing authentication, course navigation, notifications, account management, and campus resources.

---

## 🚀 Features

- **Branded Splash Screen**:
  - Displays the CampusConnect logo and branding using **ConstraintLayout**.
  - Automatically transitions to the Login screen after ~2 seconds using `Handler` and `postDelayed()`.
  - Removes itself from the back stack via `finish()`.
- **Authentication & Validation**:
  - Simulated local authentication with hardcoded credentials (`user@example.com` / `Password@123`).
  - Clear error indications using `TextInputLayout.setError()` and `EditText.setError()`.
  - Password visibility toggle (`app:endIconMode="password_toggle"`).
  - Clears the navigation back stack upon login (`FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK`).
- **User Registration**:
  - Vertical form layout demonstrating **LinearLayout**.
  - Validates full name, email format (`Patterns.EMAIL_ADDRESS`), password length (minimum 6 characters), and password matching.
  - Success feedback via `Toast` and returns to the Login screen.
- **Self-Service Password Recovery Flow**:
  - **Forgot Password Screen** built using **ConstraintLayout** with email validation.
  - Simulates sending a password recovery link with instant feedback (`Toast`/`Snackbar`).
  - **Reset Password Screen** validating new password strength and matching confirmation.
- **Interactive Dashboard**:
  - Demonstrates a 2-column **GridLayout** containing interactive `MaterialCardView` cards.
  - 6 Campus modules: **Profile**, **Courses**, **Notifications**, **Settings**, **Reports**, and **Help**.
  - Clicking any module displays a feedback `Toast`.
  - Profile card and top toolbar action menu navigate directly to `ProfileActivity`.
- **Student Profile**:
  - Built with **ConstraintLayout** displaying student name and email received via explicit `Intent` extras from `LoginActivity`.
  - **Logout Action**: Cleans up the back stack with `Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK`, returning to `LoginActivity`.
- **Theming & Alternative Resources**:
  - **Dark Mode (Night Mode)**: Supported via dedicated color palettes in `res/values/colors.xml` and `res/values-night/colors.xml`.
  - **Internationalization (i18n)**: English (default) in `res/values/strings.xml` and Spanish in `res/values-es/strings.xml`.
  - **Landscape Orientation**: Alternative layout `res/layout-land/activity_dashboard.xml` providing an optimized 3-column grid in landscape mode.
  - **Zero Hardcoded Strings**: 100% of user-facing text is extracted into `strings.xml`.

---

## 📐 Mandatory Layout Demonstration

The codebase explicitly showcases the three required Android layout systems:

1. **`LinearLayout` (Vertical Form)**:
   - [`activity_login.xml`](app/src/main/res/layout/activity_login.xml): Vertical login form with inputs and action buttons.
   - [`activity_register.xml`](app/src/main/res/layout/activity_register.xml): Vertical registration form with input controls.
2. **`ConstraintLayout`**:
   - [`activity_splash.xml`](app/src/main/res/layout/activity_splash.xml): Responsive centering and vertical chains for logo and title.
   - [`activity_forgot_password.xml`](app/src/main/res/layout/activity_forgot_password.xml): Flexible constraints positioning header, form card, and actions.
   - [`activity_profile.xml`](app/src/main/res/layout/activity_profile.xml): Centered avatar, badge, details card, and bottom-pinned logout button.
3. **`GridLayout` (2 Columns)**:
   - [`activity_dashboard.xml`](app/src/main/res/layout/activity_dashboard.xml): 2-column grid (`columnCount="2"`) displaying module cards with equal column and row weights.

---

## 🔤 Input Controls Specification

- **Person Name**: `inputType="textPersonName"` (Register screen)
- **Email Address**: `inputType="textEmailAddress"` (Login, Register, and Forgot Password screens)
- **Password**: `inputType="textPassword"` with `app:endIconMode="password_toggle"` (Login, Register, and Reset Password screens)

---

## 🗺️ Navigation Flows

All transitions between activities are performed using explicit `Intent`s:

```
1. Main Flow:
   SplashActivity (2s auto-nav) ──> LoginActivity ──> DashboardActivity ──> ProfileActivity ──> Logout (clears back stack to LoginActivity)

2. Registration Flow:
   LoginActivity ──> RegisterActivity ──> Toast + finish() ──> LoginActivity

3. Password Recovery Flow:
   LoginActivity ──> ForgotPasswordActivity ──> Toast + ResetPasswordActivity ──> Toast + finish() ──> LoginActivity
```

---

## 🔑 Demo Credentials

| Field | Value | Notes |
|---|---|---|
| **Email** | `user@example.com` | Hardcoded demo account |
| **Password** | `Password@123` | Case-sensitive password |
| **User Name** | `Abdul Shakoor` | Default student name passed to Dashboard & Profile |

---

## 🛠️ How to Build and Run

### Prerequisites
- JDK 11 or higher (or Android Studio bundled JBR)
- Android SDK installed (`compileSdk = 37`, `minSdk = 24`)

### Build Debug APK
Run the Gradle wrapper from the project root:
```bash
./gradlew assembleDebug
```
On Windows PowerShell:
```powershell
.\gradlew.bat assembleDebug
```
The output APK will be generated at:
`app/build/outputs/apk/debug/app-debug.apk`

### Run Unit Tests
```powershell
.\gradlew.bat test
```

### Install and Launch on Emulator / Physical Device
```powershell
.\gradlew.bat installDebug
```
Or open the project in **Android Studio** and click the **Run ('app')** button (`Shift + F10`).
