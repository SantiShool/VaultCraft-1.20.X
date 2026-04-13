VaultCraft (Minecraft 1.20.X Mod)

VaultCraft is a Minecraft Forge mod for version 1.20.X that introduces new blocks, items, and mechanics inspired by a Fallout-style theme.

Overview

This project adds custom content to Minecraft, including blocks with special behavior, new items, and systems for handling game data. It is designed as a learning project for working with Minecraft modding and Forge.

Features
Custom blocks, including rusting/aging blocks
Custom items and materials
Modified jukebox behavior using a block entity
Data generation for recipes, loot tables, and models
Organized mod structure following Forge conventions
Installation
Requirements
Minecraft 1.20.X
Minecraft Forge
Steps

Clone the repository:

git clone https://github.com/SantiShool/VaultCraft-1.20.X.git

Build the project:

./gradlew build
Locate the generated .jar file in the build/libs/ folder
Move the .jar file into your Minecraft mods folder
Launch the game using Forge
Development

To run the mod in a development environment:

./gradlew runClient
Project Structure
src/main/java/net/squaants/vaultcraft/
├── block/
├── blockentity/
├── item/
├── datagen/
└── VaultCraft.java

Badges

![OpenSSF Scorecard](https://api.securityscorecards.dev/projects/github.com/SantiShool/VaultCraft-1.20.X/badge)

[![OpenSSF Best Practices](https://bestpractices.coreinfrastructure.org/projects/12485/badge)](https://bestpractices.coreinfrastructure.org/projects/12485)

Contributing

Contributions are welcome. Please fork the repository and submit a pull request.

Issues

If you find a bug or have a suggestion, open an issue on GitHub.

License

Add your license information here.
