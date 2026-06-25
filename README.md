# Simple Tic-Tac-Toe Game with Java Swing, Login, and Statistics

## Student Information
- **Name:** Danindra Dimitri Prabasatya
- **Student ID:** 5026251139
- **Class:** ES234211 - Class E

## Project Description
This project is a simple Tic-Tac-Toe game built using Java Swing GUI. The player competes against the computer. The application includes a login system connected to a MySQL database, game statistics tracking, and a Top 5 scorers leaderboard.

## Features
- Login using MySQL database
- Play Tic-Tac-Toe using Swing GUI (player vs computer)
- Computer AI: tries to win, blocks player, or picks center/random
- Record wins, losses, draws, and score
- Score system: Win = +10, Draw = +3, Lose = +0
- Display personal statistics
- Display Top 5 scorers using JTable

## Database
- **Database used:** MySQL
- **Table:** `players` (one table only)

## How to Run

### 1. Setup Database
```sql
CREATE DATABASE game_project;
USE game_project;
```
Then import `database/schema.sql`.

### 2. Add JDBC Driver
Download `mysql-connector-j-x.x.x.jar` from [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/).

### 3. Configure Database Connection
Edit `src/DatabaseManager.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/game_project";
private static final String USER = "root";       // sesuaikan
private static final String PASSWORD = "";        // sesuaikan
```

### 4. Compile and Run (VS Code / Terminal)
```bash
# Compile semua file (pastikan mysql connector ada di folder lib/)
javac -cp ".;lib/mysql-connector-j-x.x.x.jar" src/*.java -d out/

# Run
java -cp ".;lib/mysql-connector-j-x.x.x.jar;out/" Main
```

### 5. Default Login Credentials
| Username | Password |
|----------|----------|
| student1 | 12345 |
| student2 | 12345 |
| student3 | 12345 |

## Class Explanation
| Class | Responsibility |
|-------|---------------|
| `Main` | Entry point, membuka LoginFrame |
| `DatabaseManager` | Mengelola koneksi JDBC ke MySQL |
| `Player` | Model data pemain (id, username, wins, losses, draws, score) |
| `PlayerService` | Login, update statistik, ambil top 5 dari database |
| `GameLogic` | Validasi gerak, cek menang/seri, logika komputer |
| `LoginFrame` | Window login dengan username dan password |
| `MainMenuFrame` | Menu utama setelah login berhasil |
| `GameFrame` | Window permainan Tic-Tac-Toe 3x3 |
| `StatisticsFrame` | Menampilkan statistik pribadi pemain |
| `TopScorersFrame` | Menampilkan Top 5 pemain menggunakan JTable |

## Screenshots
- Drive: https://drive.google.com/drive/folders/1jUxL3ccTA74VMnNMEU2RKXClWjKcxmAr?usp=drive_link

## Video Link
- YouTube: https://youtu.be/p8aYXbSBSoU

## GitHub Link
- Repository: https://github.com/danindradp/tictactoe-java-swing
