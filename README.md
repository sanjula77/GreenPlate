# 🌱 GreenPlate - Sustainable Food & Education Mobile App

GreenPlate is a mobile application built with **Jetpack Compose** and **Firebase** that bridges the gap between farmers and buyers, promotes food donations, and supports educational content on sustainable farming and nutrition. This project aligns with **United Nations Sustainable Development Goals (SDGs)**:

- 🎯 **SDG 2: Zero Hunger**
- 🎯 **SDG 4: Quality Education**

---

## 📱 Features

- **User Authentication**: Email/Password & Google Sign-In (Firebase), email verification, profile updates with Cloudinary image upload
- **Marketplace**: Farmers manage product listings, buyers browse fresh produce, optimized image rendering (Coil + Cloudinary)
- **Food Donation**: Donate food for hunger relief, real-time CRUD with Firestore
- **Educational Platform**: Interactive modules on food safety, nutrition, and sustainable farming
- **Optimized Media**: Image uploads via Cloudinary, efficient loading with Coil (HTTPS, resizing, caching)
- **Swipeable Cards UI**: Informational carousel using Accompanist HorizontalPager

---

## 🛠️ Tech Stack

| Tech               | Description                               |
| ------------------ | ----------------------------------------- |
| 💚 Jetpack Compose | Declarative UI Framework for Android      |
| 🔥 Firebase        | Auth, Firestore NoSQL DB, Real-time Sync  |
| ☁️ Cloudinary      | Image hosting and transformation          |
| 🌀 Coil            | Fast and efficient image loading          |
| 🎨 Accompanist     | Jetpack Compose Pager for swipeable cards |
| 🔧 Kotlin          | Primary programming language              |

---

## 📦 Project Structure

```
📁 greenplate/
├── auth/           # Login, Register, Verify Email
├── homeScreen/     # Dashboard, Marketplace, Swiper UI
├── profileSection/ # Profile view & update logic
├── education/      # Education modules (CRUD)
├── donation/       # Food donation logic
```

---

## 🚀 How to Run

1. **Clone the repo**
   ```bash
   git clone https://github.com/sanjula77/greenplate.git
   ```
2. **Open with Android Studio**
3. **Add your `google-services.json`** (Firebase project) to the `app/` directory
4. **Set up Cloudinary credentials** in the `uploadToCloudinary()` method
5. **Run on Emulator or Physical Device** (API 24+)

---

## 🖼️ Screenshots

| Home Page             | Marketplace             | Education            | Profile                  |
| --------------------- | ----------------------- | -------------------- | ------------------------ |
| ![](screens/home.png) | ![](screens/market.png) | ![](screens/edu.png) | ![](screens/profile.png) |

---

## 👨‍💻 Developed By

**Gihan Sanjula**  
Final Year Undergraduate, Horizon Campus, Dept. of IT  
[LinkedIn](https://www.linkedin.com/in/gihan-sanjula) • [GitHub](https://github.com/sanjula77)

---

## 📜 License

This project is released under the MIT License. See [LICENSE](LICENSE) for details.

> **Note:** This project is for academic and educational purposes. Contributions are welcome—feel free to fork, star, and submit pull requests with credit. 🔄
