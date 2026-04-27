# Catan: Settlers — Java Desktop Implementation

A Java desktop implementation of the classic board game **Catan**, redesigned with **custom house rules** to make gameplay more approachable and enjoyable for casual players.

> Built for game nights with friends — simpler turns, friendlier trading, and a smoother learning curve than the original rulebook.

---

## Screenshots

<!-- Screenshot: Player count selection screen — show the dialog asking "How many players?" with options 2, 3, 4 -->
![Player Setup](https://github.com/user-attachments/assets/19d96390-b096-4651-9dca-d342f2786b48)

<!-- Screenshot: Main game board — show the full hexagonal board with numbered tiles, player settlements/roads placed, and the resource/action panel on the side -->
![Game Board](https://github.com/user-attachments/assets/1a6989ac-ede0-45c0-b707-d26a371f79e6)

<!-- Screenshot: Trading dialog — show the trade offer screen between two players, with resource selection dropdowns on both sides -->
![Trading](https://github.com/user-attachments/assets/b960536e-cbd4-4782-ab9d-75c131d93426)

---

## House Rules (What's Different)

This implementation uses **simplified rules tuned for friends who are new to Catan** — less downtime, faster games, friendlier interaction:

| Rule | Original Catan | This Version |
|---|---|---|
| **Robber** | Placed on 7, blocks production | Robber does not exists |
| **Trading** | Bank trades at 4:1 (or port rate) | Players can freely propose any trade ratio |
| **Resource cap** | Discard half on 7 if >7 cards | No discard rule — keep all your resources |
| **Turn order** | Strict clockwise | Flexible — supports catch-up suggestions |

These changes make the game more relaxed and better suited for first-time players, game night onboarding, or quick sessions.

---

## Features

- 2–4 player support on a single device
- Randomized hex board generation each game
- Full resource production on dice roll
- Road, settlement, and city placement with rule validation
- Port trading system
- Development cards (Knights, Progress cards)
- Special achievements: Longest Road, Largest Army
- Victory point tracking with win detection

---

## Tech Stack

| Category | Technology |
|---|---|
| Language | Java 11+ |
| GUI | Java Swing |
| Architecture | MVC |
| Build | Gradle |

---

## Getting Started

### Prerequisites
- Java 11+

### Run the Game

1. **Clone the repository**
   ```bash
   git clone https://github.com/NancyD2017/Game.git
   cd Game
   ```

2. **Build and launch**
   ```bash
   ./gradlew run
   ```

---

## How to Win

Score **10 victory points** first (configurable) by:

| Action | Points |
|---|---|
| Build a settlement | +1 |
| Upgrade to a city | +1 (total 2 per city) |
| Longest Road (5+ roads) | +2 |
| Largest Army (3+ knights) | +2 |
| Victory Point development card | +1 each |

---

## License

Personal project. Original Catan game is a trademark of Catan GmbH — this is a non-commercial fan implementation for educational purposes.
