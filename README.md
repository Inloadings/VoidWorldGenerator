# Void World Generator Plugin

A Paper plugin with **NO** Dependencies that provides commands for creating, managing, and teleporting to a custom void world in your Minecraft server.
Perfect for a development world to test features.

## Features
- **Void World Management**: Create, reset, and manage a void world with ease.
- **Teleportation**: Quickly teleport to and from the void world.
- **Spawn Management**: Set and update the spawn location within the void world.
- **Simple Commands**: Easy-to-use commands for all void world operations.

## Commands
| Command         | Description                                                                 |
|------------------|-----------------------------------------------------------------------------|
| `/void tp`       | Teleport to the void world.                                                |
| `/void setspawn` | Set the spawn location in the void world.                                  |
| `/void create`   | Create the void world (if it doesn't already exist).                       |
| `/void reset`    | Reset the void world (deletes and recreates it).                           |
| `/void leave`    | Leave the void world and teleport back to the default world.               |

## Installation
1. Download the latest release of the plugin from the [Releases](#) page.
2. Place the plugin JAR file in your server's `plugins` folder.
3. Restart your server to load the plugin.

## Usage
1. Start your server and ensure the plugin is loaded successfully.
2. Use the `/void` commands to create, teleport to, or manage the void world.

## Developer Notes
### Key Features in the Code
- **Tab Completion**: Provides command suggestions for easier usage.
- **Detailed Usage**: Provides a detailed usage message.
