# 🌱 GreenPlate - Sustainable Food & Education Mobile App

GreenPlate is a mobile application built with **Jetpack Compose** and **Firebase** that bridges the gap between farmers and buyers, promotes food donations, and supports educational content on sustainable farming and nutrition. This project aligns with **United Nations Sustainable Development Goals (SDGs)**:  
- 🎯 **SDG 2: Zero Hunger**  
- 🎯 **SDG 4: Quality Education**

## 📱 Features

### 👥 User Authentication
- Email/Password & Google Sign-In (Firebase Authentication)
- Email verification system
- User profile updates with profile picture upload (via Cloudinary)

### 🛒 Marketplace
- Farmers can add, update, and delete product listings
- Buyers can browse available fresh produce
- Efficient image rendering using Coil with advanced caching and Cloudinary optimization

### 🍱 Food Donation
- Users can donate food for hunger relief
- Real-time CRUD with Firestore

### 📚 Educational Platform
- Interactive learning modules on food safety, nutrition, and sustainable farming
- Examples: 
  - *HACCP Food Safety for Restaurants*
  - *Introduction to Nutrition and Healthy Eating*
  - *Sustainable Agriculture 101*

### 📸 Optimized Media
- Image uploads via **Cloudinary**
- Smooth and optimized image loading using **Coil**, with:
  - HTTPS conversion
  - Image resizing via Cloudinary transformations
  - Memory/disk/network caching

### 📖 Swipeable Cards UI
- Informational carousel using Accompanist HorizontalPager
- Highlights emergency aid, nutrition programs, and food education

---

## 🛠️ Tech Stack

| Tech           | Description                                     |
|----------------|-------------------------------------------------|
| 💚 Jetpack Compose | Declarative UI Framework for Android            |
| 🔥 Firebase         | Auth, Firestore NoSQL DB, Real-time Sync       |
| ☁️ Cloudinary      | Image hosting and transformation               |
| 🌀 Coil             | Fast and efficient image loading               |
| 🎨 Accompanist     | Jetpack Compose Pager for swipeable cards      |
| 🔧 Kotlin           | Primary programming language                   |

---

## 📦 Project Structure (Main Packages)

📁 greenplate/
├── auth/ # Login, Register, Verify Email
├── homeScreen/ # Dashboard, Marketplace, Swiper UI
├── profileSection/ # Profile view & update logic
├── education/ # Education modules (CRUD)
├── donation/ # Food donation logic

---

## 🚀 How to Run

1. Clone the repo  
   ```bash
   git clone https://github.com/sanjula77/greenplate.git

   Open with Android Studio

Add your google-services.json (Firebase project)

Set up Cloudinary credentials in the uploadToCloudinary() method

Run on Emulator or Physical Device (API 24+)

| Home Page             | Marketplace             | Education            | Profile                  |
| --------------------- | ----------------------- | -------------------- | ------------------------ |
| ![](screens/home.png) | ![](screens/market.png) | ![](screens/edu.png) | ![](screens/profile.png) |

🎓 Developed By
👨‍💻 Gihan Sanjula
Final Year Undergraduate
Horizon Campus
Dept. of IT
🔗 LinkedIn
🔗 GitHub

📜 License
This project is for academic and educational purposes.
Feel free to fork and contribute with credits. 🔄


---

Would you like this as a downloadable file or added to your GitHub repository? I can also help you generate a project banner to include in this README.

