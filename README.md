
## 2D RPG Game

This project is a simple 2D Role-Playing Game (RPG) developed in Java. The game features a tile-based world, player movement, NPCs, monsters, collectible items, and basic sound effects. It is designed as a learning project for game development fundamentals, with a focus on clean code structure and extensibility.

### Gameplay Overview
You control a hero exploring a pixel-art world filled with NPCs, monsters, and treasures. The main objectives are:
- Explore different maps and environments
- Collect items to progress (keys, coins, boots, hearts, chests)
- Interact with NPCs for hints or story elements
- Defeat monsters to clear your path
- Find and open chests for rewards

The game is designed to be easily extended with new maps, items, and characters.

### Features
- Tile-based map system
- Player character with movement and attack animations
- Non-Playable Characters (NPCs) with simple interactions
- Monsters with basic AI
- Collectible items (coins, keys, boots, hearts, chests)
- Sound effects and background music
- Simple UI for health and inventory

### Project Structure
- `src/` - Java source code
  - `Entity/` - Player, NPC, and entity classes
  - `Main/` - Main game logic, UI, and utilities
  - `monster/` - Monster classes
  - `Object/` - Game object classes
  - `Tile/` - Tile and map management
- `res/` - Game resources
  - `Font/` - Fonts
  - `maps/` - Map files
  - `monster/` - Monster sprites
  - `Npc/` - NPC sprites
  - `Objects/` - Item sprites
  - `Player/` - Player sprites
  - `Sounds/` - Sound effects and music
  - `Tiles/` - Tile images

#### Key Files
- `GamePanel.java`: Main game loop and rendering
- `Player.java`: Player logic and controls
- `TileManager.java`: Loads and draws maps
- `Sound.java`: Handles music and sound effects

### Requirements
- Java JDK 8 or higher
- An IDE such as IntelliJ IDEA or Eclipse (recommended)

### Setup
1. Clone or download this repository.
2. Open the project in your preferred Java IDE.
3. Ensure the `res/` folder is in the project root (at the same level as `src/`).
4. Compile and run the `Main.java` file located in `src/Main/`.
5. If you encounter resource loading issues, check your IDE's working directory settings.


### Controls
- Arrow keys: Move player
- Space: Attack
- Enter: Interact

### Customization
- **Add new maps:** Place new `.txt` files in `res/maps/` and update the map loading logic in `TileManager.java`.
- **Add new items or monsters:** Create new classes in `src/Object/` or `src/monster/` and add their sprites to the appropriate `res/` subfolder.
- **Change player or NPC sprites:** Replace or add PNG files in `res/Player/` or `res/Npc/`.
- **Modify sounds:** Add or replace `.wav` files in `res/Sounds/`.

### Troubleshooting
- **Black screen or missing graphics:** Ensure the `res/` folder is in the correct location and all resource files are present.
- **No sound:** Check your system volume and verify the sound files exist in `res/Sounds/`.
- **Resource not found errors:** Adjust your IDE's working directory to the project root.

### Development Notes
- The code is organized for clarity and learning. Each entity, object, and system is in its own class.
- The game loop uses a fixed time step for smooth movement and animation.
- Maps are loaded from text files, making it easy to design new levels.

### Credits
- Sprites and sound effects are for educational purposes only.
- Inspired by classic 2D RPGs.
