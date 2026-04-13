# VaultCraft (Minecraft 1.20.X)

VaultCraft is a comprehensive Minecraft Forge mod that merges the sandbox world of Minecraft with the gritty, post-apocalyptic atmosphere of the **Fallout** universe.

## Badges
[![OpenSSF Best Practices](https://www.bestpractices.dev/projects/12485/badge)](https://www.bestpractices.dev/projects/12485)?v=2
![OpenSSF Scorecard](https://api.securityscorecards.dev/projects/github.com/SantiShool/VaultCraft-1.20.X/badge)

## Overview

Developed as a learning project for **Minecraft Forge**, VaultCraft introduces custom mechanics and lore-friendly items. Whether you are scavenging for materials or building a fortified wasteland settlement, this mod provides the foundational blocks and systems needed for a themed experience.

## Key Features

  * **Dynamic Environments:** Custom blocks featuring rusting and aging mechanics.
  * **Wasteland Gear:** New items and materials inspired by retro-futuristic aesthetics.
  * **Enhanced Audio:** Modified jukebox behavior powered by custom block entities.
  * **Automated Data:** Robust data generation for recipes, loot tables, and 3D models.
  * **Clean Architecture:** Organized mod structure following modern Forge conventions.

-----

## Getting Started

### Requirements

  * **Minecraft:** 1.20.1+
  * **Loader:** Minecraft Forge

### Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/SantiShool/VaultCraft-1.20.X.git
    ```
2.  **Build the project:**
    ```bash
    ./gradlew build
    ```
3.  **Install:** Move the `.jar` from `build/libs/` into your Minecraft `mods` folder.

### Development

To launch the mod in a development environment for testing:

```bash
./gradlew runClient
```

-----

## 🛠️ Project Structure

```text
src/main/java/net/squaants/vaultcraft/
├── block/          # Block definitions and logic
├── blockentity/    # Tile entity data and behaviors
├── item/           # Custom items and tools
├── datagen/        # Automated JSON generation
└── VaultCraft.java # Main Mod entry point
```

-----

## Contributing & Feedback

  * **Issues:** Found a bug? [Open an issue](https://www.google.com/search?q=https://github.com/SantiShool/VaultCraft-1.20.X/issues) to let us know.
  * **Pull Requests:** Contributions are welcome\! Please fork the repo and submit your changes for review.

## License

This project is licensed under the **MIT License** (or your preferred license).
