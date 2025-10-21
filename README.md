# Snake Game 🐍

## Overview 📝

This is a classic **Snake Game** implementation in Java using **Swing** for the graphical interface. Control a snake that grows longer as it eats food while avoiding collisions with walls and its own body. The game features smooth animations, score tracking, and a restart mechanism.

Built with **Java Swing**, the game follows **event-driven programming** principles with keyboard controls and timer-based updates for fluid gameplay.

## Features 🌟

- **Classic Snake Gameplay 🎮**: Navigate the snake to collect food and grow longer
- **Directional Controls ⬆️⬇️⬅️➡️**: Arrow keys for movement in all four directions
- **Dynamic Snake Growth 📈**: Snake grows with each food item consumed
- **Score Tracking 🏆**: Real-time score and snake length display
- **Collision Detection 💥**: Game over on wall collision or self-collision
- **Food Spawning 🍎**: Random food generation within game boundaries
- **Restart Functionality 🔄**: Press Space to restart after game over
- **Visual Feedback 🎨**: Different images for snake head direction and body segments
- **Smooth Animation ⚡**: Timer-based movement with configurable speed

## Technologies 💻

- **Java 8+**: Core programming language
- **Java Swing**: GUI framework for graphics and user interface
- **AWT (Abstract Window Toolkit)**: Graphics rendering and event handling
- **Timer**: Game loop management for periodic updates
- **Random**: Randomized food placement

## Game Mechanics 🎯

### Controls
- **Arrow Right (→)**: Move snake right
- **Arrow Left (←)**: Move snake left
- **Arrow Up (↑)**: Move snake up
- **Arrow Down (↓)**: Move snake down
- **Space**: Restart game after game over

### Rules
1. **Objective**: Collect as much food as possible to grow the snake
2. **Growth**: Snake grows by 1 segment with each food item
3. **Game Over Conditions**:
   - Hitting the wall boundaries
   - Colliding with snake's own body
4. **Movement**: Snake cannot reverse direction (e.g., if moving right, cannot immediately move left)

## Getting Started 🚀

### Prerequisites 💅

- **Java JDK 8 or higher**
- **IDE** (IntelliJ IDEA, Eclipse, NetBeans) or command line

### Installation & Running 💾

**Clone or download the project:**
```sh
git clone https://github.com/your-username/snake-game.git
cd snake-game
```

**Ensure you have the required image files in the `src/` folder:**
```
src/
├── rightmouth.png     # Snake head facing right
├── leftmouth.png      # Snake head facing left
├── upmouth.png        # Snake head facing up
├── downmouth.png      # Snake head facing down
├── snakeimage.png     # Snake body segment
├── snaketitle.jpg     # Game title banner
└── enemy.png          # Food item image
```

**Compile and run:**
```bash
# Using command line
javac Main.java GamePlay.java
java Main

# Or run directly from your IDE
```

## Project Structure 📁

```
snake-game/
├── Main.java              # Entry point, creates JFrame window
├── GamePlay.java          # Game logic and rendering
├── src/
│   ├── rightmouth.png     # Snake head images (4 directions)
│   ├── leftmouth.png
│   ├── upmouth.png
│   ├── downmouth.png
│   ├── snakeimage.png     # Body segment
│   ├── snaketitle.jpg     # Title banner
│   └── enemy.png          # Food item
└── README.md
```

## Code Architecture 🏗️

### Main.java
```java
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GamePlay Example");
        GamePlay gamePlay = new GamePlay();
        frame.add(gamePlay);
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```

**Purpose**: Creates the game window and initializes the GamePlay component.

### GamePlay.java

#### Key Components

**1. Snake Representation**
```java
private int[] snakex = new int[750];   // X coordinates of snake segments
private int[] snakey = new int[750];   // Y coordinates of snake segments
private int lungimeSarpe = 3;          // Initial snake length
```

**2. Direction Management**
```java
private boolean left = false;
private boolean right = false;
private boolean up = false;
private boolean down = false;
```

**3. Game Timer**
```java
private Timer timp;
private int delay = 100;  // Update every 100ms
```

**4. Food System**
```java
private int[] mancarex = {25, 50, 75, ..., 850};  // Valid X positions
private int[] mancarey = {75, 100, 125, ..., 625}; // Valid Y positions
private int xpos = random.nextInt(34);
private int ypos = random.nextInt(23);
```

## Game Flow 🔄

### 1. Initialization
```java
public GamePlay() {
    // Load images
    dreapta = new ImageIcon("src/rightmouth.png");
    stanga = new ImageIcon("src/leftmouth.png");
    // ... other images
    
    // Setup keyboard listener
    addKeyListener(this);
    setFocusable(true);
    
    // Start game timer
    timp = new Timer(delay, this);
    timp.start();
}
```

### 2. Game Loop (actionPerformed)
```java
@Override
public void actionPerformed(ActionEvent e) {
    // Check food collision
    if (mancarex[xpos] == snakex[0] && mancarey[ypos] == snakey[0]) {
        lungimeSarpe++;
        score++;
        // Generate new food position
    }
    
    // Update snake position based on direction
    if (right) {
        // Move all segments
        // Move head right by 25 pixels
    }
    // Similar for left, up, down
    
    repaint();  // Trigger paintComponent
}
```

### 3. Rendering (paintComponent)
```java
protected void paintComponent(Graphics g) {
    // Draw borders and title
    g.drawRect(24, 10, 851, 55);
    titleImage.paintIcon(this, g, 25, 11);
    
    // Draw game area
    g.fillRect(25, 75, 850, 575);
    
    // Draw score
    g.drawString("Scor: " + score, 740, 30);
    
    // Draw snake
    for (int i = 0; i < lungimeSarpe; i++) {
        if (i == 0) {
            // Draw head with direction-specific image
        } else {
            // Draw body segment
        }
    }
    
    // Draw food
    mancare.paintIcon(this, g, mancarex[xpos], mancarey[ypos]);
    
    // Check collisions
}
```

### 4. Input Handling (keyPressed)
```java
@Override
public void keyPressed(KeyEvent e) {
    if (gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
        // Restart game
        gameOver = false;
        lungimeSarpe = 3;
        score = 0;
    } else {
        // Handle direction changes
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !left) {
            right = true;
            up = false;
            down = false;
        }
        // Similar for other directions
    }
}
```

## Game Parameters 🔧

```java
// Window Dimensions
WINDOW_WIDTH = 900
WINDOW_HEIGHT = 700

// Game Area
GAME_AREA_X = 25
GAME_AREA_Y = 75
GAME_AREA_WIDTH = 850
GAME_AREA_HEIGHT = 575

// Snake Settings
INITIAL_LENGTH = 3
SEGMENT_SIZE = 25  // pixels
MAX_SEGMENTS = 750

// Game Speed
UPDATE_DELAY = 100  // milliseconds (10 updates/second)

// Food Grid
X_POSITIONS = 34    // (25 to 850, step 25)
Y_POSITIONS = 23    // (75 to 625, step 25)
```

## Collision Detection 💥

### Wall Collision
```java
if (snakex[0] >= 870 || snakex[0] <= 15 || 
    snakey[0] < 80 || snakey[0] >= 640) {
    // Game Over
    gameOver = true;
}
```

### Self Collision
```java
for (int i = 1; i < lungimeSarpe; i++) {
    if (snakex[i] == snakex[0] && snakey[i] == snakey[0]) {
        // Game Over
        gameOver = true;
    }
}
```

### Food Collision
```java
if (mancarex[xpos] == snakex[0] && mancarey[ypos] == snakey[0]) {
    lungimeSarpe++;
    score++;
    // Spawn new food
}
```

## Example Gameplay 🫂

### Starting Position
```
Snake Length: 3
Score: 0
Position: [100, 100] (head), [75, 100], [50, 100]
Direction: None (waiting for input)
Food: Random position
```

### After Eating 5 Foods
```
Snake Length: 8
Score: 5
Position: Snake occupies 8 grid positions
Direction: Based on last key press
Food: New random position
```

### Game Over
```
Message: "Game Over!!!"
Instruction: "Space to Restart"
Final Score: Displayed
Snake Length: Final length displayed
```

## Movement Algorithm 🎮

### Right Movement Example
```java
// Step 1: Move body segments (copy positions)
for (int i = lungimeSarpe; i >= 0; i--) {
    if (i == 0) {
        snakex[i] = snakex[i] + 25;  // Move head
    } else {
        snakex[i] = snakex[i-1];      // Follow previous segment
    }
}

// Y coordinates remain same for horizontal movement
for (int i = lungimeSarpe-1; i >= 0; i--) {
    snakey[i+1] = snakey[i];
}
```

**Visual Representation:**
```
Before:  [Head][Seg1][Seg2]
After:   .....[Head][Seg1][Seg2]
         (moves 25 pixels right)
```

## Scoring System 🏆

| Action | Points | Snake Growth |
|--------|--------|--------------|
| Eat Food | +1 | +1 segment |
| Wall Collision | Game Over | - |
| Self Collision | Game Over | - |

**Display:**
- **Score**: Total food items collected
- **Snake Length**: Current number of segments

## Visual Elements 🎨

### Game Layout
```
┌─────────────────────────────────────────────┐
│           SNAKE GAME TITLE                  │
│  Score: 15          Snake Length: 18        │
├─────────────────────────────────────────────┤
│                                             │
│    [Snake moving through game area]         │
│                                             │
│              [Food Item]                    │
│                                             │
│                                             │
└─────────────────────────────────────────────┘
```

### Colors
- **Background**: Black (`Color.BLACK`)
- **Borders**: White (`Color.WHITE`)
- **Text**: White, Arial font

### Images Required
1. **rightmouth.png**: Snake head facing →
2. **leftmouth.png**: Snake head facing ←
3. **upmouth.png**: Snake head facing ↑
4. **downmouth.png**: Snake head facing ↓
5. **snakeimage.png**: Body segment
6. **snaketitle.jpg**: Game banner
7. **enemy.png**: Food item

## Advanced Features 🔍

### Direction Lock
Prevents snake from reversing into itself:
```java
if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
    if (!left) {  // Can only move right if not moving left
        right = true;
        up = false;
        down = false;
    }
}
```

### Restricted Food Spawning
```java
int isFoodInRestrictedArea(int foodX, int foodY) {
    if (foodX < 200 && foodX > 800 && 
        foodY > 350 && foodY < 550) {
        return 0;  // Invalid position
    }
    return 1;  // Valid position
}
```

### Smooth Animation
- **Timer-based updates**: Consistent frame rate
- **Grid-based movement**: 25-pixel increments
- **Immediate key response**: No input lag

## Known Issues & Notes 📝

⚠️ **Image Paths**: Ensure all images are in `src/` folder with exact filenames
⚠️ **Grid System**: Movement is locked to 25-pixel grid
⚠️ **Window Size**: Fixed at 900×700, not resizable
⚠️ **Maximum Length**: Array size limits snake to 750 segments
⚠️ **Food Spawn**: May occasionally spawn on snake body (rare)

## Future Improvements 🚧

- [ ] Add difficulty levels (speed variations)
- [ ] Implement high score persistence (save/load)
- [ ] Add sound effects and background music
- [ ] Multiple food types with different point values
- [ ] Power-ups (speed boost, invincibility, etc.)
- [ ] Obstacles and maze levels
- [ ] Multiplayer mode (2-player snake)
- [ ] Animated snake movement (smoother transitions)
- [ ] Pause functionality (P key)
- [ ] Settings menu (customize colors, speed)
- [ ] Touch controls for mobile adaptation
- [ ] Leaderboard system

## Customization Tips 💡

### Change Game Speed
```java
private int delay = 100;  // Default (100ms = 10 FPS)
// Faster: delay = 50;     // 20 FPS
// Slower: delay = 200;    // 5 FPS
```

### Modify Initial Snake Length
```java
private int lungimeSarpe = 3;  // Default
// Longer start: lungimeSarpe = 5;
```

### Adjust Game Area Size
```java
// In paintComponent():
g.fillRect(25, 75, 850, 575);  // Current size
// Larger: g.fillRect(25, 75, 1000, 700);
```

### Change Colors
```java
g.setColor(Color.BLACK);  // Background
// Try: Color.DARK_GRAY, Color.BLUE, etc.

g.setColor(Color.WHITE);  // Borders/Text
// Try: Color.GREEN, Color.CYAN, etc.
```

## Troubleshooting 🔧

### Images Not Loading
```java
// Check file paths are correct:
dreapta = new ImageIcon("src/rightmouth.png");
// If not working, try absolute path or move images
```

### Snake Not Moving
- Check if Timer is started: `timp.start()`
- Verify `actionPerformed()` is being called
- Ensure window has focus (click on it)

### Game Freezes
- Check for infinite loops in movement logic
- Verify collision detection isn't blocking updates

## Contributing 🤝

Contributions welcome! Areas for improvement:
- Enhanced graphics and animations
- Additional game modes
- Mobile-friendly version
- AI opponent (computer-controlled snake)
- Level editor

## License 📜

This project is available for educational purposes.

---

**Game Type**: Classic Arcade  
**Platform**: Desktop (Java Swing)  
**Controls**: Keyboard (Arrow Keys + Space)  
**Difficulty**: Progressive (grows harder as snake lengthens)  
**Target Audience**: All ages  
**Language**: Java 8+  
**Last Updated**: 2025
